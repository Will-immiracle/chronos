package com.will.chronos.service;

import com.will.api.dto.chronos.TimerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChronosService {
    Long CreateTimer(TimerDTO timerDTO);

    void DeleteTimer(String app, long id);

    void Update(TimerDTO timerDTO);

    TimerDTO GetTimer(String app, long id);

    void EnableTimer(String app, long id);

    void UnEnableTimer(String app, long id);

    List<TimerDTO> GetAppTimers(String app);
}
