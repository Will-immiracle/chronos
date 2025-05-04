package com.will.chronos.controller;

import com.will.api.dto.chronos.TimerDTO;
import com.will.common.model.ResponseEntity;
import com.will.chronos.service.ChronosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

/**
 * @program: chronos
 * @description:
 * @author: Mr.Zhang
 * @create: 2025-05-03 11:22
 **/

@RestController
@RequestMapping("chronos")
@Slf4j
public class ChronosWebController {

    @Autowired
    private ChronosService chronosService;

    @PostMapping(value = "/createTimer")
    public ResponseEntity<Long> createTimer(@RequestBody TimerDTO timerDTO){
        Long timerId = chronosService.CreateTimer(timerDTO);
        return ResponseEntity.ok(timerId);
    }

    @GetMapping(value = "/enableTimer")
    public ResponseEntity<String> enableTimer(@RequestParam(value = "app") String app,
                                              @RequestParam(value = "timerId") Long timerId,
                                              @RequestHeader MultiValueMap<String, String> headers){
        chronosService.EnableTimer(app,timerId);
        return ResponseEntity.ok("ok");
    }

}
