## Spring Cloud Ribbon 客户端负载均衡 demo
### 必要条件
    因为是单机演示，所以需要修改hosts文件，在最后添加 
    127.0.0.1 peer1
    127.0.0.1 peer2
### 项目结构
    |-- hello-service（服务提供者）
    |-- consumer-restTemplate（服务消费者）
    |-- consumer-feign（服务消费者）
#### 启动
- jar包 方式cmd命令行启动: 
```
java -jar 项目名.jar --spring.profiles.active=active1
java -jar 项目名.jar --spring.profiles.active=active2
```
- idea springBoot 方式启动： 
```
在启动时分别在 Active profiles 处填入 active1 和 active2 指定配置文件启动
```
 
 
### hello-service（服务提供者）
    同eureka方式启动，提供/hello接口，返回服务名称和占用端口信息
    
### consumer-restTemplate（服务消费者）
#### 简介
    在hello-service启动后，通过加有@LoadBalanced注解的restTemplate远程调用其/hello接口，
    ribbon会自动选择其中一台服务进行调用，完成接口间的远程调用。
#### 启动
    jar包方式 或 idea springBoot 方式 直接启动
### 演示过程
    在浏览器输入 localhost:9101/restTemplate 并回车，
    然后反复刷新页面，会看到两个hello-service在轮流提供服务 


### consumer-feign（服务消费者）
#### 简介
    在hello-service启动后，通过加有@FeignClient注解的接口远程调用其/hello接口，
    ribbon会自动选择其中一台服务进行调用，完成接口间的远程调用。
#### 启动
    同consumer-restTemplate工程
### 演示过程
    在浏览器输入 localhost:9102/feign 并回车，效果同restTemplate调用方式


## Spring Cloud Ribbon客户端负载均衡原理
	springCloud进行服务调用，只需要知道服务的名称如（hello-service），即可进行调用；
	
	在通过 @LoadBalance注解的 RestTemplate类发起远程调用时，会被 LoadBalancerInterceptor的intercept方法拦截，
	然后执行 RibbonLoadBalancerClient的execute方法，
	通过 interceptor解析出来的serviceId，即服务名称（hello-service），调用getServer方法，
	然后调用 ILoadBalancer的chooseServer方法，这个方法最终会调用到BaseLoadBalancer中的chooseServer方法，
	在BaseLoadBalancer中有一个IRule接口的引用，这个IRule接口是对客户端负载均衡策略的统一定义，
	ribbon中提供了一系列该规则的实现，如RandomRule（随机）、RoundRobinRule（线性轮询）、
	RetryRule（具备重试机制的实例选择）、ZoneAvoidanceRule（多区域环境下选择出最佳区域的实例选择）等
	在chooseServer方法中，就是通过该IRule实现类（ribbon中默认使用ZoneAvoidanceRule规则），
	获取到具体的服务实例，在进行远程调用并返回响应的结果；
	
## 客户端负载均衡策略
### RandomRule（随机）
    每次随机从可用的服务实例清单中获取一个服务实例
### RoundRobinRule（线性轮询）
    线性轮询的从可用的服务实例清单中获取一个服务实例
### RetryRule（具备重试机制的实例选择）
    内部定义了一个RoundRobinRule（线性轮询）的对象，和默认500毫秒的maxRetryMillis；
    在时间间隔内循环调用roundRobinRule的choose方法，直至选择出来的服务实例可以正常调用；
### ZoneAvoidanceRule（多区域环境下选择出最佳区域的实例选择）