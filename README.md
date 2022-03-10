# TextPlan
该系统是为了给用户每天计划安排表，邮箱动态提醒模块，基本添加任务模块，数据检验模块等
基础模块的CRUD，三层框架，只能创建当天任务，任务时间区间不能相同
2）数据库使用springboot 结合mybaits+druid+redis（redis 使用工具类）
3）安全模块：登录使用token验证,添加到请求头（结合jwt）
4）前端实现跨域axios请求，vue路由传参，组件式开发
5）spring内置调度，改变cron实时创建QQ邮箱提醒。
6）美化框架elementPlus，结合echars实现可视化 ，结合fastjson,富文本wangEditor
7）时间工具: dayjs , hutool中localDateTimeUtils

记录一下吧，设计前一定要设计个高保真模型再做
