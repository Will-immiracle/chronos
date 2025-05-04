package com.will.chronos.feign;

import com.will.api.dto.chronos.TimerDTO;
import com.will.api.feign.ChronosClient;
import com.will.chronos.service.ChronosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ChronosFeignController implements ChronosClient {

    @Autowired
    ChronosService chronosService;

    @Override
    public Long createTimer(TimerDTO timerDTO) {
        return chronosService.CreateTimer(timerDTO);
    }

    @Override
    public void enableTimer(String app, Long timerId, MultiValueMap<String, String> headers) {
        chronosService.EnableTimer(app, timerId);
    }
}
