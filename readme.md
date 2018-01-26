
# oauth2 客户端和服务端实例

## 支持协议

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
- 导出数据，使用脚本文件 Oauth24Springboot/data.sql
- 启动程序 com.bestaone.springboot.oauth2.authserver.Application

## 验证

### authorization_code 认证流程

- 使用测试类（com.bestaone.springboot.oauth2.authserver.Test）生成访问token的凭证 A
- 浏览器访问 http://localhost:8080/oauth/authorize?client_id=client&scope=read&response_type=code&state=rensanning&redirect_uri=http://localhost:8080
提示输入用户名密码：user 123
- 浏览器提示选择授权与否，选择授权。此时浏览器跳转后会添加code=yB9rWE，拿到code
- 通过code获取token，此时需要code和凭证A。访问 http://localhost:8080/oauth/token?grant_type=authorization_code&redirect_uri=http://localhost:8080&code=yB9rWE，在请求头中添加第一步获取的凭证 A
- 正确访问后，会返回json token，复制access_token
- 验证。访问 http://localhost:8080/api/get，在请求头中添加凭证Authorization Bearer ----------（或正在url中添加参数access_token）
- 无权限拦截的测试url http://localhost:8080/test/a

### password 认证流程

- post 访问 http://localhost:8080/oauth/token?username=user&password=123&grant_type=password&client_id=client&client_secret=123456
- 正常访问后返回json token
- 验证：使用 http://localhost:8080/api/get，在请求头中添加凭证 Authorization Bearer ----------
- 无权限拦截的测试url http://localhost:8080/test/a

### client_credentials 认证流程

- post 访问 http://localhost:8080/oauth/token?grant_type=client_credentials&client_id=client&client_secret=123456&scope=read
- 正常访问后返回json token
- 验证：使用 http://localhost:8080/api/get，在请求头中添加凭证 Authorization Bearer ----------
- 无权限拦截的测试url http://localhost:8080/test/a

### 验证scope

- post 访问 http://localhost:8080/oauth/token?username=user&password=123&grant_type=password&client_id=client&client_secret=123456&scope=read
- 返回的json token的权限范围是read
- 访问 http://localhost:8080/api/post，被拒绝（这个接口设置了需要write权限）

## 参考

> http://rensanning.iteye.com/blog/2386766