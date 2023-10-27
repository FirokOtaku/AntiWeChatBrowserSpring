# Anti WeChat Browser Spring

这是一个跟随 [AntiWeChatBrowserAPI](https://github.com/HikariCalyx/AntiWeChatBrowserAPI) 项目的 Java 实现,
在 SpringBoot 环境使用, 用于检测客户端是否为微信浏览器.

### 使用方法

clone repo 并执行 `mvn install`.

`pom.xml` 引入依赖:

```xml
<dependency>
    <groupId>firok.spring</groupId>
    <artifactId>antiwcbs</artifactId>
    <version>1.1.0</version>
</dependency>
```

`application.yml` 配置:

```yaml
firok:
  spring:
    antiwcbs:
      enable-detector: true
```

主类指定组件扫描:

```java
@SpringBootApplication
@ComponentScans(
    @ComponentScan("firok.spring.antiwcbs")
)
public class SpringApplication
```

最后可在 Controller 等处使用:

```java
@Autowired
WeChatBrowserDetector detector;

@RequestMapping("/test")
public String test()
{
    return detector.isWeChatPhone() ? "is wechat mobile webview" : "not wechat mobile webview"";
}

```
