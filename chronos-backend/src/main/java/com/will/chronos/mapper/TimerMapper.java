package com.will.chronos.mapper;

import com.will.chronos.model.TimerModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author zhangzan
* @description 针对表【chronos(Timer 信息)】的数据库操作Mapper
* @createDate 2025-05-03 11:02:45
* @Entity com.will.chronos.domain.TimerModel
*/
@Mapper
public interface TimerMapper {

    /**
     * 保存timerModel
     *
     * @param timerModel
     */
    void save(@Param("timerModel") TimerModel timerModel);

    /**
     * 根据timerId删除TimerModel
     *
     * @param timerId
     */
    void deleteById(@Param("timerId") Long timerId);

    /**
     * 更新TimerModel
     *
     * @param timerModel
     */
    void update(@Param("timerModel") TimerModel timerModel);

    /**
     * 根据timerId查询TimerModel
     *
     * @param timerId
     * @return TimerModel
     */
    TimerModel getTimerById(@Param("timerId") Long timerId);

    /**
     * 根据status查询TimerModel
     *
     * @param status
     * @return TimerModel
     */
    List<TimerModel> getTimersByStatus(@Param("status") int status);

}




