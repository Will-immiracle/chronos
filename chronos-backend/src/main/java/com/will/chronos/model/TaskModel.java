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
@TableName(value ="timer_task")
@Data
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
}