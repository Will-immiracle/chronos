package com.will.chronos.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.will.api.dto.chronos.NotifyHTTPParam;
import com.will.api.dto.chronos.TimerDTO;
import com.will.chronos.utils.JSONUtil;
import com.will.common.model.BaseModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Timer 信息
 * @TableName chronos
 */
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

    /**
     * 包装类转对象
     *
     * @param timerDTO
     * @return
     */
    public static TimerModel voToObj(TimerDTO timerDTO) {
        if (timerDTO == null) {
            return null;
        }
        TimerModel timerModel = new TimerModel();
        timerModel.setApp(timerDTO.getApp());
        timerModel.setTimerId(timerDTO.getTimerId());
        timerModel.setName(timerDTO.getName());
        timerModel.setStatus(timerDTO.getStatus());
        timerModel.setCron(timerDTO.getCron());
        timerModel.setNotifyHttpParam(JSONUtil.toJsonString(timerDTO.getNotifyHTTPParam()));
        return timerModel;
    }

    /**
     * 对象转包装类
     *
     * @param timerModel
     * @return
     */
    public static TimerDTO objToVo(TimerModel timerModel) {
        if (timerModel == null) {
            return null;
        }
        TimerDTO timerDTO = new TimerDTO();
        timerDTO.setApp(timerModel.getApp());
        timerDTO.setTimerId(timerModel.getTimerId());
        timerDTO.setName(timerModel.getName());
        timerDTO.setStatus(timerModel.getStatus());
        timerDTO.setCron(timerModel.getCron());

        NotifyHTTPParam httpParam = JSONUtil.parseObject(timerModel.getNotifyHttpParam(), NotifyHTTPParam.class);
        timerDTO.setNotifyHTTPParam(httpParam);

        BeanUtils.copyProperties(timerModel, timerDTO);
        return timerDTO;
    }

    public Long getTimerId() {
        return timerId;
    }

    public void setTimerId(Long timerId) {
        this.timerId = timerId;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getNotifyHttpParam() {
        return notifyHttpParam;
    }

    public void setNotifyHttpParam(String notifyHTTPParam) {
        this.notifyHttpParam = notifyHTTPParam;
    }

}