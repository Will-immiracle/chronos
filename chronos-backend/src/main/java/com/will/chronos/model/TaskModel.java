package com.will.chronos.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.will.common.model.BaseModel;
import lombok.Data;

/**
 * Timer Task任务信息
 * @TableName timer_task
 */
public class TaskModel extends BaseModel implements Serializable {
    /**
     * taskId
     */
    @TableId(type = IdType.AUTO)
    private Long taskId;


    /**
     * TimerId
     */
    private Long timerId;

    /**
     * app
     */
    private String app;

    /**
     * output
     */
    private String output;

    /**
     * 0新建，1激活，2未激活
     */
    private Integer status;

    /**
     * 运行时间
     */
    private Long runTimer;

    /**
     * 执行耗时
     */
    private Long costTime;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public Long getTimerId() {
        return timerId;
    }

    public void setTimerId(Long timerId) {
        this.timerId = timerId;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Long getRunTimer() {
        return runTimer;
    }

    public void setRunTimer(Long runTimer) {
        this.runTimer = runTimer;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}