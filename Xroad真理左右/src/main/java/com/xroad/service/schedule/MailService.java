//package com.xroad.service.schedule;
//
//import com.xroad.entity.Day;
//import com.xroad.model.MailInfo;
//import com.xroad.model.schedule.TriggerTaskSupport;
//import lombok.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j(topic = "自定义的定时任务1")
//public class MailService extends TriggerTaskSupport {
//    @Autowired
//    JavaMailSenderImpl mailSender;
//
//    Day day;
//
//    @Getter
//    @Builder.Default
//    private CronTrigger trigger = new CronTrigger("0 0 0/6 * * ?");
//
//    @Override
//    public String setType(String type) {
//
//    }
//
//    @Override
//    public CronTrigger setTrigger(String expression) {
//        String old = trigger.getExpression();
//        this.trigger = new CronTrigger(expression);
//        log.info("update cron success, old: {} , new: {}", old, trigger.getExpression());
//
//        return this.trigger;
//    }
//
//
//
//
//
//    @Getter
//    @Builder.Default
//    private Runnable task = new Runnable() {
//        @Override
//        public void run() {
//            System.out.println("\n");
//            log.info("================start runnig================");
//            // service.run(); // 该service 是另外一个类的对象，这样才能 使得事务起作用
//
//
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setSubject(day.getText());
//            message.setText("标签"+day.getThing().getName()+"</br> 时间："+day.getStart()+"~~~~"+day.getEnd()+"</br>感受："+day.getFeel());
//
//            message.setTo("2821580012@qq.com");
//            message.setFrom("2821580012@qq.com");
//            mailSender.send(message);
//
//
//
//            // 也可以 用当前的Bean 对象 作为 target 调用,才能被AOP 拦截，进而达到事务管理的目的
//            // ContextLoader.getCurrentWebApplicationContext().getBean(CustomService.class).run1();
//            log.info("================ end  runnig================");
//        }
//    };
//
//
//
//
//
//}
