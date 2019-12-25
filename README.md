# springboot-oauth2
a simple demo for oauth2(mode: authorization_code). 

+ springboot-oauth2
 - oauth2-authorize   (authorize server)
 - oauth2-client      (resource server)



several paths below:
1. get code 
  http://localhost:9000/auth/oauth/authorize?response_type=code&client_id=client&redirect_uri=http://localhost:9001/hi
  
2. get access_token(获取令牌)
  http://localhost:9000/auth/oauth/token?client_id=client&client_secret=secret&grant_type=authorization_code&redirect_uri=http://localhost:9001/hi&code=【code】
  
3. refresh_token 
  http://localhost:9000/auth/oauth/token?grant_type=refresh_token&client_id=client&client_secret=secret&refresh_token=【refresh_token】
  
4. get resource
  http://[resource-url]?access_token=【access_token】
