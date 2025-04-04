package com.songoda.ultimatemoderation.tasks;

import com.songoda.ultimatemoderation.UltimateModeration;
import com.songoda.ultimatemoderation.database.DataHelper;
import com.songoda.ultimatemoderation.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class DataTask implements Runnable {

    private BukkitTask task;
    private final DataHelper dataHelper;

    public DataTask(DataHelper dataHelper) {
        this.dataHelper = dataHelper;
        this.task = Bukkit.getScheduler().runTaskTimerAsynchronously(UltimateModeration.getPlugin(UltimateModeration.class), this, 0, Settings.DATA_UPDATE_INTERVAL.getInt());
    }

    @Override
    public void run() {
        //Update punisment data
        dataHelper.updateData();
    }

    public void cancel() {
        task.cancel();
    }
}
