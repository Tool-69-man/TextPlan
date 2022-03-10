package com.xroad.model.schedule;

import org.springframework.scheduling.support.CronTrigger;

/**
 * TriggerTask 必须实现的方法，为了支持动态配置 cron表达式，所以
 *
 * @author stormfeng
 * @date 2020-11-03  11:21
 */
public interface ITriggerTask {
    /**
     * 获取 类别，区分 不同的Bean 对象
     * @return
     */
    String type();

    /**
     * 获取 run 方法
     * @return
     */
    Runnable getTask();

    /**
     * 获取触发器，一般是 CronTrigger
     * @return
     */
    CronTrigger getTrigger();

    /**
     * 接口 动态修改 定时任务的表达式
     */
    CronTrigger setTrigger(String cron);
}