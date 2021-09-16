# 概述

此demo是未来保贝提供给合作伙伴调用sdk的demo，jdk版本1.7。

# 使用

* 启动此demo进行测试，在PolicyExample类中启动main()方法。

* 启动前将appId和appSecret联系保贝开发对接人员获取。

* 下面代码块为初始化appId和appSecret，具体参考PolicyExample类

```
  static BaobeiClient baobeiClient = new BaobeiClient("","","");
 ```

* 添加仓库

```
<repositories>
        <repository>
            <id>baobei</id>
            <url>https://maven.wlbb.net/repository/maven-releases</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>
```

* 添加sdk依赖，以下示例为maven，gradle示例请参考文档
  
```
  <dependency>
    <groupId>com.futurebaobei</groupId>
    <artifactId>open-api-sdk</artifactId>
    <version>0.5</version>
  </dependency>
 ```

# 感谢