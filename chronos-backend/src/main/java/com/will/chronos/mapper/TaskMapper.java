package com.will.chronos.mapper;

import com.will.chronos.model.TaskModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author zhangzan
* @description 针对表【timer_task(Timer Task任务信息)】的数据库操作Mapper
* @createDate 2025-05-03 11:02:45
* @Entity com.will.chronos.domain.TaskModel
*/
@Mapper
public interface TaskMapper {

    /**
     * 批量插入taskModel
     *
     * @param taskList
     */
    void batchSave(@Param("taskList") List<TaskModel> taskList);

    /**
     * 根据timerId删除taskModel
     *
     * @param taskId
     */
    void deleteById(@Param("taskId") Long taskId);

    /**
     * 更新TimerModel
     *
     * @param taskModel
     */
    void update(@Param("taskModel") TaskModel taskModel);

    /**
     * 根据taskId查询Task
     *
     * @param startTime
     * @param endTime
     * @param taskStatus
     * @return TaskModel
     */
    List<TaskModel> getTasksByTimeRange(@Param("startTime") Long startTime, @Param("endTime") Long endTime, @Param("taskStatus") int taskStatus);

    /**
     * 根据taskId查询Task
     *
     * @param timerId
     * @param runTimer
     * @return TaskModel
     */
    TaskModel getTasksByTimerIdUnix(@Param("timerId") Long timerId, @Param("runTimer") Long runTimer);


}




