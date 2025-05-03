package com.will.chronos.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.will.common.model.BaseModel;
import lombok.Data;

/**
 * Timer 信息
 * @TableName chronos
 */
@TableName(value ="chronos")
@Data
public class TimerModel extends BaseModel implements Serializable {
    /**
     * TimerId
     */
    @TableId(type = IdType.AUTO)
    private Long timerId;


    /**
     * app
     */
    private String app;

    /**
     * name
     */
    private String name;

    /**
     * 0新建，1激活，2未激活
     */
    private Integer status;

    /**
     * cron表达式
     */
    private String cron;

    /**
     * 回调上下文
     */
    private String notifyHttpParam;
}