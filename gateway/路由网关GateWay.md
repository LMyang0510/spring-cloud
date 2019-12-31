## Spring Cloud Gateway
### 简介
    Spring Cloud Gateway是Spring官方基于Spring5.0，Spring Boot2.0和Project Reactor等技术开发的网关，
    使用非阻塞API（支持Websockets），目标是替代Netflix Zuul，旨在为微服务架构提供简单、有效且统一的API路由管理方式。
    Spring Cloud Gateway不仅提供统一的路由方式，还基于Filter链的方式提供了网关基本的功能，例如：安全，监控、埋点，限流等。
    注意：SpringCloudGateway依赖 pringBoot和SpringWebFlux，基于Netty运行。它不能在传统的servlet容器中工作，也不能构建成war包。

### 核心概念
#### Route路由
    路由是网关最基础的部分，路由信息有一个ID、一个目的URL、一组断言和一组Filter组成。  
    当请求到达网关时，由GatewayHandlerMapping通过断言进行路由匹配（Mapping），当断言为真时，匹配到路由。

#### Predicate断言
    Java8中的断言函数。Spring Cloud Gateway中的断言函数输入类型是Spring5.0框架中的ServerWebExchange。  
    Spring Cloud Gateway中的断言函数允许开发者去定义匹配来自于http request中的任何信息，比如请求头和参数等。

#### Filter过滤器
    Filter 是 Gateway 中的过滤器，可以在请求发出前后进行一些业务上的处理。一个标准的Spring webFilter。
    Spring cloud gateway中的filter分别是Gateway Filter和Global Filter。过滤器Filter将会对请求和响应进行修改处理

![工作流程图](https://img-blog.csdnimg.cn/20190703211815129.png)

### 路由断言Factory
* After：  
`After Route Predicate Factory采用一个参数——日期时间。在该日期时间之后发生的请求都将被匹配`
    ```yaml
    spring:
      cloud:
        gateway:
          routes:
          - id: after_route
            uri: http://example.org
            predicates:
            - After=2017-01-20T17:42:47.789-07:00[America/Denver]
    ```

* Before：  
`Before Route Predicate Factory采用一个参数——日期时间。在该日期时间之前发生的请求都将被匹配`
    ```yaml
    spring:
      cloud:
        gateway:
          routes:
          - id: before_route
            uri: http://example.org
            predicates:
            - Before=2017-01-20T17:42:47.789-07:00[America/Denver]
    ```

* Between：  
`Between 路由断言 Factory有两个参数，datetime1和datetime2。在datetime1和datetime2之间的请求将被匹配。datetime2参数的实际时间必须在datetime1之后`
    ```yaml
    spring:
      cloud:
        gateway:
          routes:
          - id: between_route
            uri: http://example.org
            predicates:
            - Between=2017-01-20T17:42:47.789-07:00[America/Denver], 2017-01-21T17:42:47.789-07:00[America/Denver]
    ```

* Cookie：  
`Cookie 路由断言 Factory有两个参数，cookie名称和正则表达式。请求包含次cookie名称且正则表达式为真的将会被匹配`
    ```yaml
    spring:
      cloud:
        gateway:
          routes:
          - id: cookie_route
            uri: http://example.org
            predicates:
            - Cookie=chocolate, ch.p
    ```

* Header：  
`Header 路由断言 Factory有两个参数，header名称和正则表达式。请求包含次header名称且正则表达式为真的将会被匹配`
    ```yaml
    spring:
     cloud:
       gateway:
         routes:
         - id: header_route
           uri: http://example.org
           predicates:
           - Header=X-Request-Id, \d+
    ```

* Host：  
`Host 路由断言 Factory包括一个参数：host name列表。使用Ant路径匹配规则，.作为分隔符`
    ```yaml
    spring:
      cloud:
        gateway:
          routes:
          - id: host_route
            uri: http://example.org
            predicates:
            - Host=**.somehost.org,**.anotherhost.org
    ```

* Method：  
`Method 路由断言 Factory只包含一个参数: 需要匹配的HTTP请求方式`
    ```yaml
    spring:
      cloud:
        gateway:
          routes:
          - id: method_route
            uri: http://example.org
            predicates:
            - Method=GET
    ```

* Path：  
`Path 路由断言 Factory 有2个参数: 一个Spring PathMatcher表达式列表和可选matchOptionalTrailingSeparator标识`
    ```yaml
    spring:
      cloud:
        gateway:
          routes:
          - id: host_route
            uri: http://example.org
            predicates:
            - Path=/api/**
    #        - Path=/foo/{segment},/bar/{segment}
    ```

* Query：  
`Query 路由断言 Factory 有2个参数: 必选项 param 和可选项 regexp`
    ```yaml
    spring:
      cloud:
        gateway:
          routes:
          - id: query_route
            uri: http://example.org
            predicates:
            - Query=foo, ba.(可选项regexp)
    ```

* RemoteAddr：  
`RemoteAddr 路由断言 Factory的参数为 一个CIDR符号（IPv4或IPv6）字符串的列表，最小值为1，例如192.168.0.1/16（其中192.168.0.1是IP地址并且16是子网掩码）`
    ```yaml
    spring:
      cloud:
        gateway:
          routes:
          - id: remoteaddr_route
            uri: http://example.org
            predicates:
            - RemoteAddr=192.168.1.1/24
    ```

### Filter过滤器
* AddRequestHeader  
`采用一对名称和值作为参数，对于所有匹配的请求，这将向下游请求的头中添加 x-request-foo:bar header`
    ```yaml
    spring:
      cloud:
        gateway:
          routes:
          - id: add_request_header_route
            uri: http://example.org
            filters:
            - AddRequestHeader=X-Request-Foo, Bar
    ```

* AddRequestParameter  
`采用一对名称和值作为参数，对于所有匹配的请求，这将向下游请求添加foo=bar查询字符串`
    ```yaml
    spring:
      cloud:
        gateway:
          routes:
          - id: add_request_parameter_route
            uri: http://example.org
            filters:
            - AddRequestParameter=foo, bar
    ```

* AddResponseHeader  
`采用一对名称和值作为参数，对于所有匹配的请求，这会将x-response-foo:bar头添加到下游响应的header中`
    ```yaml
    spring:
      cloud:
        gateway:
          routes:
          - id: add_request_header_route
            uri: http://example.org
            filters:
            - AddResponseHeader=X-Response-Foo, Bar
    ```

* Hystrix  
`Hystrix GatewayFilter允许你向网关路由引入断路器，保护你的服务不受级联故障的影响，并允许你在下游故障时提供fallback响应。`  
`要在项目中启用Hystrix网关过滤器，需要添加对 spring-cloud-starter-netflix-hystrix的依赖`  
`Hystrix GatewayFilter Factory 需要一个name参数，即HystrixCommand的名称。`  
`这将剩余的过滤器包装在命令名为“myCommandName”的HystrixCommand中。`
    ```yaml
    spring:
      cloud:
        gateway:
          routes:
          - id: hystrix_route
            uri: lb://backing-service:8088
            predicates:
            - Path=/consumingserviceendpoint
            filters:
            - name: Hystrix
              args:
                name: fallbackcmd
                fallbackUri: forward:/incaseoffailureusethis
            - RewritePath=/consumingserviceendpoint, /backingserviceendpoint
           - id: ingredients
                   uri: lb://ingredients
                   predicates:
                   - Path=//ingredients/**
                   filters:
                   - name: Hystrix
                     args:
                       name: fetchIngredients
                       fallbackUri: forward:/fallback
                 - id: ingredients-fallback
                   uri: http://localhost:9994
                   predicates:
                   - Path=/fallback
    ```