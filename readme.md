# Oauth2 客户端、认证服务、资源服务整合实例

## 集成协议

1. password
1. client_credentials
1. authorization_code

## 创建数据库

- 本实例使用的数据库是postgresql
- 配置数据库
```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/oauth2
    driver-class-name: org.postgresql.Driver
    username: postgres
    password: 123456
```
- 导入数据，使用脚本文件 Oauth24Springboot/oauth2.sql、Oauth24Springboot/security.sql
- 启动程序
1. com.bestaone.springboot.oauth2.client.Application
1. com.bestaone.springboot.oauth2.aurhserver.Application
1. com.bestaone.springboot.oauth2.resource.Application

## 模块介绍

- clien模块
验证authorization_code的客户端代码演示

- auth-server 
提供认证的功能

- resource-server
资源服务，需要携带授权凭证才可访问

## 验证

### authorization_code 认证流程

- 将三个项目都启动
- 访问 http://localhost:8080
- 这里实现了用户名/密码登录，手机号码/短信验证码登录，均需要填写图形验证码
- 首页是client项目的，登录认证是在auth server，登录后获取的Profile数据是从resource server中获取的
> 注意：如果授权环节不出现，可以清除下数据库的数据

### password 认证流程

- post 访问 http://localhost:8081/oauth/token?username=user&password=123&grant_type=password&client_id=client&client_secret=123456
- 正常访问后返回json token
- 验证：使用 http://localhost:8082/api/me，在请求头中添加凭证 Authorization Bearer ----------
- 无权限拦截的测试url http://localhost:8081/test/a

### client_credentials 认证流程

- post 访问 http://localhost:8081/oauth/token?grant_type=client_credentials&client_id=client&client_secret=123456&scope=read
- 正常访问后返回json token
- 验证：使用 http://localhost:8082/api/me，在请求头中添加凭证 Authorization Bearer ----------
- 无权限拦截的测试url http://localhost:8082/test/a

### authorization_code 认证流程(废弃，暂时不删除，有可能有参考价值)

- 使用测试类（com.bestaone.springboot.oauth2.authserver.Test）生成访问token的凭证 A （必须）
- 浏览器访问 http://localhost:8081/oauth/authorize?client_id=client&scope=read&response_type=code&redirect_uri=http://localhost:8080
提示输入用户名密码：user 123
- 浏览器提示选择授权与否，选择授权。此时浏览器跳转后会添加code=yB9rWE，拿到code
- 通过code获取token，此时需要code和凭证A。访问 http://localhost:8081/oauth/token?grant_type=authorization_code&redirect_uri=http://localhost:8080&code=yB9rWE，在请求头中添加第一步获取的凭证 A (必须)
- 正确访问后，会返回json token，复制access_token
- 验证。访问 http://localhost:8082/api/me，在请求头中添加凭证Authorization Bearer ----------（或正在url中添加参数access_token）
- 无权限拦截的测试url http://localhost:8082/test/a

### 验证scope

- post 访问 http://localhost:8081/oauth/token?username=user&password=123&grant_type=password&client_id=client&client_secret=123456&scope=read
- 返回的json token的权限范围是read
- 访问 http://localhost:8081/api/post，被拒绝（这个接口设置了需要write权限）

## 截图

- 客户端首页<br>
    ![image](https://github.com/bestaone/Oauth24Springboot/blob/master/doc/client_index.png)
<br>

- 跳转到认证服务器登录<br>
    ![image](https://github.com/bestaone/Oauth24Springboot/blob/master/doc/client_login.png)
<br>

- 登录验证后询问授权<br>
    ![image](https://github.com/bestaone/Oauth24Springboot/blob/master/doc/approval.png)
<br>

- 认证服务器认证授权成功后重定向到客户端<br>
    ![image](https://github.com/bestaone/Oauth24Springboot/blob/master/doc/client_login_success.png)
<br>

- password认证协议<br>
    ![image](https://github.com/bestaone/Oauth24Springboot/blob/master/doc/password_grant_type.png)
<br>

- client_credentials认证协议<br>
    ![image](https://github.com/bestaone/Oauth24Springboot/blob/master/doc/client_credentials_grant_type.png)
<br>

- 使用token访问api<br>
    ![image](https://github.com/bestaone/Oauth24Springboot/blob/master/doc/test_api.png)
<br>


## 问题

- 我将spring-boot升级到了2.0.0.M3版本，当升级到2.0.0.M7版本时出现问题，目前暂时没有排查 (已修复)
- 将认证服务和资源服务合并在一起的时候，验证authorization_code功能时，登录时报错“不支持 POST”,初步定为是 auth server 的SecurityConfig的HttpSecurity http未设置好

## 参考

> http://rensanning.iteye.com/blog/2386766