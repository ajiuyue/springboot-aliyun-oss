## SpringBoot +OSS 服务自定义图床

### 前言

疫情之下，每天都是好吃懒做啊！反正在家闲着没有事情，于是乎，花了一天时间写了这个简单项目。其实我本来是准备继续完善一下，然后部署到自己的云服务器上当作自己的相册。后来实在太懒了，也不想写前端了，于是留下了一些代办。

项目虽小，但是五脏俱全，可以直接拿来当作工具使用，代码写的虽然仓促，但是总体上应该没有什么大问题，初学者也可以直接拿来学习。

总的来说，这个项目主要能为你提供这些帮助：

1. 阿里云 oss 作为图床服务非常常用，简单封装和了解一下有助于你以后使用；
2. 技术栈使用 SpringBoot 和 thymeleaf 模板引擎，前端框架用的是国产好评较多的 layui。这个项目可以带你走一遍前后端一起开发的流程。

### 效果展示

> 页面长的比较丑，没有花太多时间在这个上面，将就着看一下。

bilibili 在线观看：https://www.bilibili.com/video/av88090586 

上传页面

![](https://my-blog-to-use.oss-cn-beijing.aliyuncs.com/test/2020-02-11/84da08d6b86d48719c90ba44511f57ed-picture-oss-aliyun1.jpg)

上传成功

![](https://my-blog-to-use.oss-cn-beijing.aliyuncs.com/2019-11/picture-oss-aliyun-success.jpg)

### 技术栈

- 后端： Spring Boot
- 前端： thymeleaf 模板引擎 +layui 框架 

之前 React 和 Vue 都写过，还是第一次用 thymeleaf 加上 layai 来开发前端，总的来说，我还是喜欢 React 多一点。

### 启动项目

**1.克隆到本地**

```shell
git clone git@github.com:Snailclimb/springboot-aliyun-oss.git
```

**2.使用idea或者其他开发工具打开项目**

**3.添加`application.yml`，内容如下：**

> ps：我在用 git 提交的时候将`application.yml` 忽略掉了，所以你需要手动添加到你的本地。

```yaml
spring:
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 10MB
  thymeleaf:
    #关闭thymeleaf的缓存，不然在开发过程中修改页面不会立刻生效需要重启，生产可配置为true
    prefix: classpath:/web/
    cache: false
    suffix: .html
aliyun:
  oss:
    bucket-name: my-blog-to-use
    endpoint: oss-cn-beijing.aliyuncs.com
    access-key-id: 替换为你的key id
    #阿里云主账号AccessKey拥有所有API的访问权限，风险很高。建议创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    access-key-secret: 替换为你的key secret
    file-host: test

```

**4.运行项目即可！**

### 代办

- [x] 单个图片的上传和删除（前后端）
- [x] 查看所有图片（后端）
- [ ] 查看所有图片（前端）
- [ ] 查看所有图片的时候可以对图片进行操作比如删除
- [ ]  ......



