package com.xroad.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.xroad.config.DefaultSchedulingConfigurer;
import com.xroad.entity.Day;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.util.Calendar;

@Service
@Async
public class MailServer   {


    @Autowired
    private DefaultSchedulingConfigurer defaultSchedulingConfigurer;


    @Autowired
    JavaMailSenderImpl mailSender;


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
 
    
    
    
    
    @Async
    public void addMail(Day day){
        String cronStartTime=LocalDateTimeUtil.format(day.getStart(),"ss mm HH dd MM ? ");
        System.out.println(cronStartTime);
//        String[] split = StartTime.split(" ",6);

//        for (String s: split){
//
//        }

        new Thread() {
            public void run()
            {

                try
                {
                    // 等待任务调度初始化完成
                    while (!defaultSchedulingConfigurer.inited())
                    {
                        Thread.sleep(100);
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("任务调度初始化完成，添加任务");
                defaultSchedulingConfigurer.addTriggerTask(day.getStart()+"", new TriggerTask(new Runnable() {

                    @SneakyThrows
                    @Override
                    public void run()
                    {
//

                        MimeMessage mimeMessage = mailSender.createMimeMessage();
                        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

//                        mimeMessageHelper.setText("\n事件开始时间：     "+day.getStart()
//                                +"\n==========经过 "+LocalDateTimeUtil.between(day.getStart(),day.getEnd())+ " 后========="
//                                +"\n结束：     "+day.getEnd().getHour() +" : "+day.getEnd().getMinute()
//                                +"\n我的总结："+day.getFeel());

                        mimeMessageHelper.setSubject("  ==> 计划安排");
                        String context="<div\n" +
                                "\n" +
                                "        id=\"app\"\n" +
                                "        style=\"  background-color: rgba(144,164,174,0.66);color: rgb(232,228,200);max-width: 600px; font-family: “Microsoft YaHei” !important\"\n" +
                                ">\n" +
                                "    <table\n" +
                                "            width=\"100%\"\n" +
                                "            border=\"2\"\n" +
                                "            cellspacing=\"0\"\n" +
                                "            cellpadding=\"0\"\n" +
                                "            style=\"font-family: '微软雅黑'\"\n" +
                                "    >\n" +
                                "        <tbody>\n" +
                                "        <tr>\n" +
                                "            <td>\n" +
                                "                <table width=\"100%\" border=\"0\" cellpadding=\"5\" cellspacing=\"0\">\n" +
                                "                    <tbody>\n" +
                                "                    <tr>\n" +
                                "                        <td>\n" +
                                "                            <div\n" +
                                "                                    class=\"header\"\n" +
                                "                                    style=\"\n" +
                                "                          height: 50px;\n" +
                                "                          text-align: right;\n" +
                                "                          width: 95%;\n" +
                                "                          margin: 0 auto;\n" +
                                "                        \"\n" +
                                "                            ><img src=\"\"></img></div>\n" +
                                "                        </td>\n" +
                                "                    </tr>\n" +
                                "                    <tr>\n" +
                                "                        <td>\n" +
                                "                            <table\n" +
                                "                                    width=\"100%\"\n" +
                                "                                    border=\"0\"\n" +
                                "                                    cellpadding=\"5\"\n" +
                                "                                    cellspacing=\"0\"\n" +
                                "                                    style=\"width: 90%; margin: 0 auto\"\n" +
                                "                            >\n" +
                                "                                <tbody>\n" +
                                "                                <tr>\n" +
                                "                                    <td>\n" +
                                "                              <span\n" +
                                "                                      style=\"\n" +
                                "                                  font-size: 32px;\n" +
                                "                                  color: rgb(232,228,200);\n" +
                                "                                  font-weight: bold;\n" +
                                "                                  margin-top: 20px;\n" +
                                "                                \"\n" +
                                "                              >\n" +
                                "                                 "+day.getText()+"\n" +
                                "                              </span>\n" +
                                "                                        <hr/>\n" +
                                "                                        <hr/>\n" +
                                "                                    </td>\n" +
                                "                                </tr>\n" +
                                "\n" +
                                "                                <tr>\n" +
                                "                                    <td>\n" +
                                "                                        <table\n" +
                                "                                                width=\"100%\"\n" +
                                "                                                border=\"0\"\n" +
                                "                                                cellpadding=\"5\"\n" +
                                "                                                cellspacing=\"0\"\n" +
                                "                                                style=\"\n" +
                                "                                  font-size: 20px;\n" +
                                "                                  /*color: #ffcfa3;*/\n" +
                                "                                  padding-bottom: 20px;\n" +
                                "                                \"\n" +
                                "                                        >\n" +
                                "                                            <tbody>\n" +
                                "                                            <tr>\n" +
                                "                                                <td >\n" +
                                "                                                   <span >开始： "+day.getStart()+"</span>\n" +
                                "\n" +
                                "                                                </td>\n" +
                                "\n" +
                                "                                            </tr>\n" +
                                "                                            <tr>\n" +
                                "                                                <td >\n" +
                                "                                                   <span>结束： "+day.getEnd()+"</span>\n" +
                                "                                                    <hr/>\n" +
                                "                                                </td>\n" +
                                "\n" +
                                "                                            </tr>\n" +
                                "\n" +
                                "                                            <tr>\n" +
                                "                                                <td style=\"line-height: 3em; \">\n" +
                                "                                                    <span>感受:</span>\n" +
                                "                                                    <br/>\n" +
                                "                                                    <span style=\" text-indent: 2em; \" >&nbsp;&nbsp;&nbsp;&nbsp;"+day.getFeel() +"</span>\n" +
                                "                                                </td>\n" +
                                "                                            </tr>\n" +
                                "                                            </tbody>\n" +
                                "                                        </table>\n" +
                                "                                    </td>\n" +
                                "                                </tr>\n" +
                                "                                </tbody>\n" +
                                "                            </table>\n" +
                                "                        </td>\n" +
                                "                    </tr>\n" +
                                "                    </tbody>\n" +
                                "                </table>\n" +
                                "            </td>\n" +
                                "        </tr>\n" +
                                "        </tbody>\n" +
                                "    </table>\n" +
                                "</div>";






                        mimeMessageHelper.setText(context,true);

                        mimeMessageHelper.setTo("2821580012@qq.com");
                        mimeMessageHelper.setFrom("2821580012@qq.com");
                        mailSender.send(mimeMessage);
                    }
                }, new CronTrigger(cronStartTime)));
            };
        }.start();
    }

    @Async
    public void changeMail(Day day){
        String cronStartTime=LocalDateTimeUtil.format( day.getStart(),"ss mm HH dd MM ? ");
        if(!defaultSchedulingConfigurer.hasTask(day.getStart()+"")){
            addMail(day);
            return;
        }

        new Thread() {
            public void run()
            {

                try
                {
                    Thread.sleep(30000);
                }
                catch (Exception e)
                {
                }
                System.out.println("重置任务............");
                defaultSchedulingConfigurer.resetTriggerTask(day.getStart()+"", new TriggerTask(new Runnable() {

                    @SneakyThrows
                    @Override
                    public void run()
                    {

                        MimeMessage mimeMessage = mailSender.createMimeMessage();
                        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

                        mimeMessageHelper.setSubject("  ==> 计划安排");
                        String context="<div\n" +
                                "\n" +
                                "        id=\"app\"\n" +
                                "        style=\"  background-color: rgba(144,164,174,0.66);color: rgb(232,228,200);max-width: 600px; font-family: “Microsoft YaHei” !important\"\n" +
                                ">\n" +
                                "    <table\n" +
                                "            width=\"100%\"\n" +
                                "            border=\"2\"\n" +
                                "            cellspacing=\"0\"\n" +
                                "            cellpadding=\"0\"\n" +
                                "            style=\"font-family: '微软雅黑'\"\n" +
                                "    >\n" +
                                "        <tbody>\n" +
                                "        <tr>\n" +
                                "            <td>\n" +
                                "                <table width=\"100%\" border=\"0\" cellpadding=\"5\" cellspacing=\"0\">\n" +
                                "                    <tbody>\n" +
                                "                    <tr>\n" +
                                "                        <td>\n" +
                                "                            <div\n" +
                                "                                    class=\"header\"\n" +
                                "                                    style=\"\n" +
                                "                          height: 50px;\n" +
                                "                          text-align: right;\n" +
                                "                          width: 95%;\n" +
                                "                          margin: 0 auto;\n" +
                                "                        \"\n" +
                                "                            ><img src=\"\"></img></div>\n" +
                                "                        </td>\n" +
                                "                    </tr>\n" +
                                "                    <tr>\n" +
                                "                        <td>\n" +
                                "                            <table\n" +
                                "                                    width=\"100%\"\n" +
                                "                                    border=\"0\"\n" +
                                "                                    cellpadding=\"5\"\n" +
                                "                                    cellspacing=\"0\"\n" +
                                "                                    style=\"width: 90%; margin: 0 auto\"\n" +
                                "                            >\n" +
                                "                                <tbody>\n" +
                                "                                <tr>\n" +
                                "                                    <td>\n" +
                                "                              <span\n" +
                                "                                      style=\"\n" +
                                "                                  font-size: 32px;\n" +
                                "                                  color: rgb(232,228,200);\n" +
                                "                                  font-weight: bold;\n" +
                                "                                  margin-top: 20px;\n" +
                                "                                \"\n" +
                                "                              >\n" +
                                "                                 "+day.getText()+"\n" +
                                "                              </span>\n" +
                                "                                        <hr/>\n" +
                                "                                        <hr/>\n" +
                                "                                    </td>\n" +
                                "                                </tr>\n" +
                                "\n" +
                                "                                <tr>\n" +
                                "                                    <td>\n" +
                                "                                        <table\n" +
                                "                                                width=\"100%\"\n" +
                                "                                                border=\"0\"\n" +
                                "                                                cellpadding=\"5\"\n" +
                                "                                                cellspacing=\"0\"\n" +
                                "                                                style=\"\n" +
                                "                                  font-size: 20px;\n" +
                                "                                  /*color: #ffcfa3;*/\n" +
                                "                                  padding-bottom: 20px;\n" +
                                "                                \"\n" +
                                "                                        >\n" +
                                "                                            <tbody>\n" +
                                "                                            <tr>\n" +
                                "                                                <td >\n" +
                                "                                                   <span >开始： "+day.getStart()+"</span>\n" +
                                "\n" +
                                "                                                </td>\n" +
                                "\n" +
                                "                                            </tr>\n" +
                                "                                            <tr>\n" +
                                "                                                <td >\n" +
                                "                                                   <span>结束： "+day.getEnd()+"</span>\n" +
                                "                                                    <hr/>\n" +
                                "                                                </td>\n" +
                                "\n" +
                                "                                            </tr>\n" +
                                "\n" +
                                "                                            <tr>\n" +
                                "                                                <td style=\"line-height: 3em; \">\n" +
                                "                                                    <span>感受:</span>\n" +
                                "                                                    <br/>\n" +
                                "                                                    <span style=\" text-indent: 2em; \" >&nbsp;&nbsp;&nbsp;&nbsp;"+day.getFeel() +"</span>\n" +
                                "                                                </td>\n" +
                                "                                            </tr>\n" +
                                "                                            </tbody>\n" +
                                "                                        </table>\n" +
                                "                                    </td>\n" +
                                "                                </tr>\n" +
                                "                                </tbody>\n" +
                                "                            </table>\n" +
                                "                        </td>\n" +
                                "                    </tr>\n" +
                                "                    </tbody>\n" +
                                "                </table>\n" +
                                "            </td>\n" +
                                "        </tr>\n" +
                                "        </tbody>\n" +
                                "    </table>\n" +
                                "</div>";






                        mimeMessageHelper.setText(context,true);

                        mimeMessageHelper.setTo("2821580012@qq.com");
                        mimeMessageHelper.setFrom("2821580012@qq.com");
                        mailSender.send(mimeMessage);

                    }
                }, new CronTrigger(cronStartTime)));
            };
        }.start();

    }

    @Async
    public void deleteMail(Day day){
        String cronStartTime=LocalDateTimeUtil.format( day.getStart(),"ss mm HH dd MM ? ");

        if(!defaultSchedulingConfigurer.hasTask(day.getStart()+"")){
            return;
        }


        new Thread() {
            public void run()
            {

                try
                {
                    Thread.sleep(30000);
                }
                catch (Exception e)
                {
                }
                System.out.println("删除任务............");
                defaultSchedulingConfigurer.cancelTriggerTask(day.getStart()+"");

                
            };
        }.start();

    }


}
