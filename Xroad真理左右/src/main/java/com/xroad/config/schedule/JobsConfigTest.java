//package com.xroad.config.schedule;
//
////import com.stormfeng.test.model.vo.ResultVo;
//import com.xroad.model.schedule.ITriggerTask;
//import lombok.NonNull;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.TaskScheduler;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ScheduledFuture;
//
///**
// * @author stormfeng
// * @date 2020-11-06  10:29
// */
//@EnableScheduling
//@Configuration
//@Slf4j
//public class JobsConfigTest implements SchedulingConfigurer, DisposableBean {
//
//
//
//    // 自定义，参考 TriggerTask，为了统一在实现类中，调用 getTrigger() 和 getTask()
//    public Collection<ITriggerTask> scheduledServices;
//    // 句柄，方便后期获取 future
//    TaskScheduler taskScheduler;
//
//    // spring特性： 初始化该类时，自动获取和装配 项目中 所有的子类 ITriggerTask
//    public JobsConfigTest(Collection<ITriggerTask> scheduledServices) {
//        this.scheduledServices = scheduledServices;
//    }
//
//    /**
//     * Future handles, to cancel the running jobs
//     */
//    private static final Map<String, ScheduledFuture> FUTURE_MAP = new ConcurrentHashMap<>();
//    /**
//     * 获取 定时任务的具体的类，用于后期 重启，更新等操作
//     */
//    private static final Map<String, ITriggerTask> SERVICE_MAP = new ConcurrentHashMap<>();
//
//    /**
//     * 线程池任务调度器
//     * <p>
//     * 支持注解方式，@Scheduled(cron = "0/5 * * * * ?")
//     */
//    @Bean
//    public TaskScheduler taskScheduler() {
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//        scheduler.setPoolSize(Runtime.getRuntime().availableProcessors() / 3 + 1);
//        scheduler.setThreadNamePrefix("TaskScheduler-");
//        scheduler.setRemoveOnCancelPolicy(true);  // 保证能立刻丢弃运行中的任务
//
//        taskScheduler = scheduler; // 获取 句柄，方便后期获取 future
//
//        return scheduler;
//    }
//
//    /**
//     * @see <a href='https://www.codota.com/code/java/methods/org.springframework.scheduling.config.ScheduledTaskRegistrar/addTriggerTask'>codota 代码提示工具</a>
//     */
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.setScheduler(taskScheduler()); // 不用担心，这里的scheduler跟 上面的注解 Bean 是同一个对象，亲自打断点验证
//
//        if (null != scheduledServices && scheduledServices.size() > 0) {
//            for (final ITriggerTask service : scheduledServices) {
//                // old 方式，不推荐，因为无法获取 调度任务的 future 对象。
//                // taskRegistrar.addTriggerTask(scheduledService.getTask(),scheduledService.getTrigger());
//
//                //但是，最近发现用该对象也可以拿到 任务的引用，参考 大神博客 。但是该方法有些鸡肋，并不能作为万能的瑞士军刀，所以放弃 。 https://my.oschina.net/u/2411391/blog/3147701
//                /*Set<ScheduledTask> tasks = taskRegistrar.getScheduledTasks();
//                for (ScheduledTask task : tasks) {
//                    task.cancel();
//                }*/
//
//                ScheduledFuture<?> schedule = taskScheduler.schedule(service.getTask(), service.getTrigger());
//                FUTURE_MAP.put(service.type().toLowerCase(), schedule);
//                SERVICE_MAP.put(service.type().toLowerCase(), service);
//            }
//        }
//    }
//
//    //=============================动态配置 cron 表达式，立刻生效，支持 停止、重启、更新cron==============================================
//
//    public Object get() {
//        final Set<String> names = FUTURE_MAP.keySet();
//        HashMap<String, Object> map = new HashMap<String, Object>();
//
//        map.put("futures", names);
//        map.put("services", new HashMap<Object, Object>() {{
//            for (Map.Entry<String, ITriggerTask> entry : SERVICE_MAP.entrySet()) {
//                put(entry.getKey(), entry.getValue().getTrigger().getExpression());
//            }
//        }});
//
//        return map.toString();
//    }
//
//    /**
//     * 新增
//     */
//    public Object add(@NonNull ITriggerTask task) {
//        String type = task.type(), cron = task.getTrigger().getExpression();
//
//        if (FUTURE_MAP.containsKey(type)) {
//            return "请重新指定 任务的 type 属性";
//        }
//
//        ScheduledFuture<?> future = taskScheduler.schedule(task.getTask(), task.getTrigger());
//        FUTURE_MAP.put(type, future);
//        SERVICE_MAP.put(type, task);
//
//        String format = String.format("添加新任务成功: :[%s],[%s]", type, cron);
//        log.info(format);
//        return format;
//    }
//
//    /**
//     * 更新
//     */
//    public void update(@NonNull final String type, @NonNull final String cron) {
//        if (!FUTURE_MAP.containsKey(type)) {
//            return;
//        }
//        //BUG 修复
//        ScheduledFuture future = FUTURE_MAP.get(type);
//        if (future != null) {
//            future.cancel(true);
//        }
//
//        ITriggerTask service = SERVICE_MAP.get(type);
//        CronTrigger old = service.getTrigger(), newTri = service.setTrigger(cron);
//
////        ScheduledFuture<?> future = taskScheduler.schedule(service.getTask(), newTri);
//        FUTURE_MAP.put(type, future); // 必须更新一下对象，否则下次cencel 会失败
//    }
//
//    /**
//     * 取消
//     */
//    public Object cancel(@NonNull String type) {
//        if (!FUTURE_MAP.containsKey(type)) {
//            return "取消失败，不存在该任务,请检查 type: " + type;
//        }
//
//        ScheduledFuture future = FUTURE_MAP.get(type);
//        if (future != null) {
//            future.cancel(true);
//        }
//
//        FUTURE_MAP.remove(type);
//
//        return "成功取消执行中的任务 : " + type;
//    }
//
//    /**
//     * 重启已经存在的任务
//     */
//    public Object restart(@NonNull String type) {
//        ITriggerTask service = SERVICE_MAP.get(type);
//        if (service == null) {
//            return "无法启动任务，请检查 type: " + type;
//        }
//
//        if (FUTURE_MAP.containsKey(type)) {
//            ScheduledFuture future = FUTURE_MAP.get(type);
//            if (future != null) {
//                future.cancel(true);
//            }
//        }
//
//        ScheduledFuture<?> future = taskScheduler.schedule(service.getTask(), service.getTrigger());
//        FUTURE_MAP.put(type, future); // 必须更新一下对象，否则下次cencel 会失败
//
//        return "成功重启任务 type: " + type + ",cron: " + service.getTrigger().getExpression();
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        for (ScheduledFuture future : FUTURE_MAP.values()) {
//            if (future != null) {
//                future.cancel(true);
//            }
//        }
//        FUTURE_MAP.clear();
//        SERVICE_MAP.clear();
//
//        ((ThreadPoolTaskScheduler) taskScheduler).destroy();
//
//    }
//
//
//}