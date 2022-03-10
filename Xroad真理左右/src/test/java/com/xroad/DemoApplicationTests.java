package com.xroad;

import cn.hutool.core.date.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.swing.text.html.HTML;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Test

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    void contextLoads() {
//        DateUtil.parse("2000-12-12");
//        CharSequence str ="2000-12-12";
//        LocalDateTimeUtil.parse(str);
        final LocalDateTime localDateTime = LocalDateTimeUtil.parse("2020-01-23 22:12:12", DatePattern.NORM_DATETIME_PATTERN);
//        Assert.assertEquals("2020-01-23T00:00", localDateTime.toString());
//        System.out.println(   LocalDateTime.parse("2020-04-03T14:57:41",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println( localDateTime);
    }

@Test
    void textfortime(){
        final LocalDateTime localDateTime = LocalDateTimeUtil.parse("2020-01-23 22:12:12", DatePattern.NORM_DATETIME_PATTERN);

        Map<String,Object> map=new HashMap<String, Object>();
        map.put("start",localDateTime);
//    System.out.println(map.get("start").toString());
    LocalDateTime localDateTime2  = (LocalDateTime) map.get("start");



//         System.out.println(  DateUtil.parse( map.get("start").toString().split("T")[0],"yyyy-mm-dd"));
//         System.out.println(  DateUtil.date((Date) map.get("start")));
         System.out.println( localDateTime2.toLocalDate()) ;
    }



    @Test
    void textfortime2(){
        String dateStr1 = "2017-03-01T22:33:23";
        LocalDateTime date1 = LocalDateTimeUtil.parse(dateStr1);

        String dateStr2 = "2017-04-01T23:53:33";
        LocalDateTime date2 = LocalDateTimeUtil.parse(dateStr2);

//相差一个月，31天
//        long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);
//        System.out.println(betweenDay);
//
//        Duration between = LocalDateTimeUtil.between(date1, date2);
////        LocalDateTimeUtil.between()
        Duration time= LocalDateTimeUtil.between(date1, date2);

//        System.out.println(dateTime);
    }

    @Test
    void t(){
        Integer a = new Integer(5);
        Integer b = new Integer(5);
        System.out.println(a == b); // false
        System.out.println(a .equals(b) ); // false
    }



//    @Test
//    @Scheduled(cron = "0/2 * * * * ? ")
//    void helloScheduled(){
//        System.out.println("hello.....");
//    }

//
    @Autowired
    JavaMailSenderImpl mailSender;




    @Test
    void contextLoads2() throws MessagingException {

//        try {
//

//
////            SimpleMailMessage message = new SimpleMailMessage();
//            mimeMessageHelper.setSubject("通知-明天来狂神这听课");
//            String content="<html>\n"+"<body>\n"
//                    + "<h3>hello world!测试发送html格式邮件</h3>\n"
//                    +"</body>\n"+"</html>";
//
//            mimeMessage.setText(content,"utf-8");
//            mimeMessageHelper.setTo("2821580012@qq.com");
//            mimeMessageHelper.setFrom("2821580012@qq.com");
//            mailSender.send(mimeMessage);
//
//
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }

        //邮件设置1：一个简单的邮件
//        SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setSubject("通知-明天来狂神这听课");

//        String context="<div background-color=\"#d1c4e9\"></div>\n" +
//                "<div\n" +
//                "      id=\"app\"\n" +
//                "      style=\"max-width: 600px; font-family: “Microsoft YaHei” !important\"\n" +
//                "    >\n" +
//                "      <table\n" +
//                "        width=\"100%\"\n" +
//                "        border=\"0\"\n" +
//                "        cellspacing=\"0\"\n" +
//                "        cellpadding=\"0\"\n" +
//                "        style=\"font-family: '微软雅黑'\"\n" +
//                "      >\n" +
//                "        <tbody>\n" +
//                "          <tr>\n" +
//                "            <td>\n" +
//                "              <table width=\"100%\" border=\"0\" cellpadding=\"5\" cellspacing=\"0\">\n" +
//                "                <tbody>\n" +
//                "                  <tr>\n" +
//                "                    <td>\n" +
//                "                      <div\n" +
//                "                        class=\"header\"\n" +
//                "                        style=\"\n" +
//                "                          height: 50px;\n" +
//                "                          text-align: right;\n" +
//                "                          width: 95%;\n" +
//                "                          margin: 0 auto;\n" +
//                "                        \"\n" +
//                "                      ><img src=\"\"></img></div>\n" +
//                "                    </td>\n" +
//                "                  </tr>\n" +
//                "                  <tr>\n" +
//                "                    <td>\n" +
//                "                      <table\n" +
//                "                        width=\"100%\"\n" +
//                "                        border=\"0\"\n" +
//                "                        cellpadding=\"5\"\n" +
//                "                        cellspacing=\"0\"\n" +
//                "                        style=\"width: 90%; margin: 0 auto\"\n" +
//                "                      >\n" +
//                "                        <tbody>\n" +
//                "                          <tr>\n" +
//                "                            <td>\n" +
//                "                              <span\n" +
//                "                                style=\"\n" +
//                "                                  font-size: 14px;\n" +
//                "                                  color: #333;\n" +
//                "                                  font-weight: bold;\n" +
//                "                                  margin-top: 20px;\n" +
//                "                                \"\n" +
//                "                              >\n" +
//                "                                您好，<br />Hello,\n" +
//                "                              </span>\n" +
//                "                            </td>\n" +
//                "                          </tr>\n" +
//                "                          <tr>\n" +
//                "                            <td>\n" +
//                "                              <table\n" +
//                "                                width=\"100%\"\n" +
//                "                                border=\"0\"\n" +
//                "                                cellpadding=\"5\"\n" +
//                "                                cellspacing=\"0\"\n" +
//                "                                style=\"\n" +
//                "                                  font-size: 12px;\n" +
//                "                                  color: #818181;\n" +
//                "                                  padding-bottom: 20px;\n" +
//                "                                \"\n" +
//                "                              >\n" +
//                "                                <tbody>\n" +
//                "                                  <tr>\n" +
//                "                                    <td>\n" +
//                "                                      <span>欢迎订阅《XXX》！</span><br />\n" +
//                "                                      <span>\n" +
//                "                                        今后我们将为您带来游戏的最新动态、幕后花絮、独家情报，以及其他更多内容。 </span\n" +
//                "                                      ><br />\n" +
//                "                                      <span>请点击按钮，确认订阅。</span>\n" +
//                "                                    </td>\n" +
//                "                                  </tr>\n" +
//                "                                </tbody>\n" +
//                "                              </table>\n" +
//                "                            </td>\n" +
//                "                          </tr>\n" +
//                "                        </tbody>\n" +
//                "                      </table>\n" +
//                "                    </td>\n" +
//                "                  </tr>\n" +
//                "                </tbody>\n" +
//                "              </table>\n" +
//                "            </td>\n" +
//                "          </tr>\n" +
//                "        </tbody>\n" +
//                "      </table>\n" +
//                "    </div>";


        String context ="<div\n" +
                "\n" +
                "        id=\"app\"\n" +
                "        style=\"  background-color: rgba(150,120,228,0.89) ;max-width: 600px; font-family: “Microsoft YaHei” !important\"\n" +
                ">\n" +
                "    <table\n" +
                "            width=\"100%\"\n" +
                "            border=\"0\"\n" +
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
                "                                  标题\n" +
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
                "                                  font-size: 12px;\n" +
                "                                  color: #ffcfa3;\n" +
                "                                  padding-bottom: 20px;\n" +
                "                                \"\n" +
                "                                        >\n" +
                "                                            <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td style=\"font-size: 20px;\">\n" +
                "                                                    <span >开始： asdasd</span>\n" +
                "\n" +
                "                                                </td>\n" +
                "\n" +
                "                                            </tr>\n" +
                "                                            <tr>\n" +
                "                                                <td style=\"font-size: 20px;\">\n" +
                "                                                    <span>结束： asdsd</span>\n" +
                "                                                    <hr/>\n" +
                "                                                </td>\n" +
                "\n" +
                "                                            </tr>\n" +
                "\n" +
                "                                            <tr>\n" +
                "                                                <td>\n" +
                "                                                    <span>感受:</span>\n" +
                "                                                    <span>内容</span>\n" +
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

//        mimeMessageHelper.setText(
//                "<html>" +
//                        "<body>" +
//                        "<BR>" +
//                        "<div align='center'>" +
//                        "imgxxxx" +
//                        "<BR>" +
//                        "<h4>" +
//                        "返回 bing com：" +
//                        "<a href='http://www.bing.com'>http://www.bing.com</a>" +
//                        "</h4>" +
//                        "</div>" +
//                        "asdasdasd</body>" +
//                        "</html>",true);

        mimeMessageHelper.setTo("2821580012@qq.com");
        mimeMessageHelper.setFrom("2821580012@qq.com");
        mailSender.send(mimeMessage);
    }



    @Test
    void test2() {

        Date d = new Date();
        System.out.println(LocalDateTimeUtil.format(LocalDateTimeUtil.of(d),"ss mm HH dd MM ? yyyy"));


    }
}
