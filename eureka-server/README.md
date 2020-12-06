#### 1 springCloud面试题
##### Eureka篇
##### Eureka一个服务注册中心，提供服务治理功能（服务端服务注册，续约，下线；客户端服务发现，消费）
注册中心注解@EnableEurekaServer

服务端@EnableEurekaClient
配置文件service-url.defaultZone = ip地址:端口+服务
提供接口

消费端@EnableEurekaClient
配置文件service-url.defaultZone = ip地址:端口+服务
注入restTemplate和loadBalance

调用方式：restTemplate.getForObject("http://localhost:8081/user/hello", String.class);
均衡负载：@loadBalanced
声明式调用：@openFeign
##### 服务高可用：@EnableCircuitBreaker
- 线程池隔离：隔离请求线程和代码执行线程，控制请求线程的离开时间，多用于远程调用
  - 优点： 
  - 缺点： 线程切换会消耗系统资源
- 信号量隔离：设置屏障队列，控制调用服务的线程数量，多用于本地代码和快速返回的调用。
###### 反思：请求超时时间


####首先下周一公开简历，继续面试，大概两周。防止身心受到煎熬，找一份工作先做两个月左右。如果不行，就准备明年跑路。
####如果还是没找到，那么就好好准备两个月，用于明年找工作
####计划：复习分布式和微服务，数据库，中间件，基础



@EnableEurekaServer，@EnableEurekaClient，@LoadBalanced注解作用

