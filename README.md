# 概述

此demo是未来保贝提供给合作伙伴调用sdk的demo，jdk版本1.7。

# 使用

* 启动此demo进行测试，在PolicyExample类中启动main()方法。
* 启动前将appId和appSecret联系保贝开发对接人员获取。
    * 下面代码块为初始化appId和appSecret，具体参考PolicyExample类
  ```
  static BaobeiClient baobeiClient = new BaobeiClient(
            "",
            "",
            "");
  ```
* pom文件是需要的依赖，这些依赖目前也需要导入你的项目中。
* lib目录下是保贝提供的sdk

# 感谢