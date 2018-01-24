
# oauth2 客户端和服务端实例

## authorization_code 认证流程

---
1.访问
http://localhost:8080/oauth/authorize?client_id=client&scope=read&response_type=code&state=rensanning&redirect_uri=http://default-oauth-callback.com
2.输入用户名密码：user 123
3.点击授权
4.拿到code
5.发post请求，替换code
http://localhost:8080/oauth/token?grant_type=authorization_code&redirect_uri=http://default-oauth-callback.com&code=KN4ptl
使用工具（com.bestaone.springboot.oauth2.authserver.Test）获取加密字符串,添加到请求头 Authorization Bearer a1248720-1937-4dad-9360-6f977fe44618
6.获取access_token
7.使用access_token访问受限网址
http://localhost:8080/api/get
添加 Authorization Bearer a1248720-1937-4dad-9360-6f977fe44618

---


## password 认证流程

---
1.post访问
http://localhost:8080/oauth/token?username=user&password=123&grant_type=password&client_id=client&client_secret=123456
2.获取access_token
3.使用access_token访问受限网址
http://localhost:8080/api/get
添加 Authorization Bearer a1248720-1937-4dad-9360-6f977fe44618

---

## client_credentials 认证流程

http://localhost:8080/oauth/token?grant_type=client_credentials&client_id=client&client_secret=123456&scope=read

## postgresql

## 参考

http://rensanning.iteye.com/blog/2386766