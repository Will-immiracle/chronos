package com.will.chronos.manager;


import com.will.chronos.model.TimerModel;

public interface MigratorManager {
    public void migrateTimer(TimerModel timerModel);
}
