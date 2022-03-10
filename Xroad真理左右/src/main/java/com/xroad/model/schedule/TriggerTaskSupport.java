package com.xroad.model.schedule;

import lombok.extern.slf4j.Slf4j;

/**
 * @author stormfeng
 * @date 2020-11-04  16:49
 */
@Slf4j
public abstract class TriggerTaskSupport implements ITriggerTask {

    @Override
    public String type() {
        return this.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public String toString() {
        return "TriggerTask{" +
                "type=" + type() +
                ", task=" + getTask() +
                "cronTrigger=" + getTrigger().getExpression() +
                '}';
    }
}