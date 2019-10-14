## Spring Cloud Ribbon
### 简介
    
    
### 原理
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
	
### 客户端负载均衡策略
#### RandomRule（随机）
    每次随机从可用的服务实例清单中获取一个服务实例
#### RoundRobinRule（线性轮询）
    线性轮询的从可用的服务实例清单中获取一个服务实例
#### RetryRule（具备重试机制的实例选择）
    内部定义了一个RoundRobinRule（线性轮询）的对象，和默认500毫秒的maxRetryMillis；
    在时间间隔内循环调用roundRobinRule的choose方法，直至选择出来的服务实例可以正常调用；
#### ZoneAvoidanceRule（多区域环境下选择出最佳区域的实例选择）