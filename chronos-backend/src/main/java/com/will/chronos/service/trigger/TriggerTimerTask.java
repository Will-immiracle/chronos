package com.will.chronos.service.trigger;

import com.will.chronos.common.conf.TriggerAppConf;
import com.will.chronos.enums.TaskStatus;
import com.will.chronos.mapper.TaskMapper;
import com.will.chronos.model.TaskModel;
import com.will.chronos.redis.TaskCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class TriggerTimerTask extends TimerTask {

    TriggerAppConf triggerAppConf;

    TriggerPoolTask triggerPoolTask;

    TaskCache taskCache;

    TaskMapper taskMapper;

    private CountDownLatch latch ;
    private Long count = 0L;

    private Date startTime;

    private Date endTime;

    private String minuteBucketKey;

    public TriggerTimerTask(TriggerAppConf triggerAppConf,TriggerPoolTask triggerPoolTask,
                            TaskCache taskCache,TaskMapper taskMapper,CountDownLatch latch,
                            Date startTime, Date endTime, String minuteBucketKey) {
        this.triggerAppConf = triggerAppConf;
        this.triggerPoolTask = triggerPoolTask;
        this.taskCache = taskCache;
        this.taskMapper = taskMapper;
        this.latch = latch;
        this.startTime = startTime;
        this.endTime = endTime;
        this.minuteBucketKey = minuteBucketKey;
    }

    @Override
    public void run() {
        Date tStart = new Date(startTime.getTime() + count*triggerAppConf.getZrangeGapSeconds()*1000L);
        // 推出条件：tstart >= endTime时就该退出了，表示执行完成。 latch.countDown();就是告诉阻塞的主线程可以继续运行了。
        if(tStart.compareTo(endTime) > 0){
            latch.countDown();
            return;
        }
        // 处理1秒任务: 【tStart+1秒】这个范围的任务。例如 3秒-4秒
        try{
            handleBatch(tStart, new Date(tStart.getTime() + triggerAppConf.getZrangeGapSeconds()*1000L));
        }catch (Exception e){
            log.error("handleBatch Error. minuteBucketKey"+minuteBucketKey+",tStartTime:"+startTime+",e:",e);
        }
        count++;
    }

    private void handleBatch(Date start, Date end){
        //获取待触发的任务
        List<TaskModel> tasks = getTasksByTime(start,end);
        if (CollectionUtils.isEmpty(tasks)){
            return;
        }
        // 从ZSET捞到的一批触发任务，现在需要遍历挨个执行
        for (TaskModel task :tasks) {
            try {
                if(task == null){
                    continue;
                }
                // 调用【执行模块Executor】，执行任务；
                triggerPoolTask.runExecutor(task);
            }catch (Exception e){
                log.error("executor run task error,task"+task.toString());
            }
        }
    }

    private List<TaskModel> getTasksByTime(Date start, Date end){
        List<TaskModel> tasks = new ArrayList<>();

        // 先走缓存
        try{
            tasks= taskCache.getTasksFromCache(minuteBucketKey,start.getTime(),end.getTime());
        }catch (Exception e){
            log.error("getTasksFromCache error: " ,e);
            // 缓存miss,走数据库
            try{
                tasks = taskMapper.getTasksByTimeRange(start.getTime(),end.getTime()-1, TaskStatus.NotRun.getStatus());
            }catch (Exception e1){
                log.error("getTasksByConditions error: " ,e1);
            }
        }
        return tasks;
    }
}
