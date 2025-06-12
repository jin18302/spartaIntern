회원가입
URL: /api/auth/signup
Method: POST
Description: 사용자 정보를 등록하여 회원가입을 수행합니다.
```
request
{
  "email": "example@email.com",
  "password": "yourPassword123!",
  "name": "홍길동",
  "phoneNumber": "01012345678"
}
```
```
responose
{
  "email": "example@email.com",
  "password": "yourPassword123!",
  "name": "홍길동"
}
```

로그인
URL: /api/auth/login
Method: POST
Description: 이메일과 비밀번호를 통해 로그인을 수행하고 토큰을 발급합니다.
```
request
{
  "email": "example@email.com",
  "password": "yourPassword123!"
}
```
```
response
{
"accessToken":"Bearer -- "
}

```

권한체크
URL: /api/auth/check
Method: GET
설명: JWT Access Token의 유효성과 권한을 확인합니다. 인증이 필요한 리소스 접근 전에 호출하여 사용자 상태를 확인할 수 있습니다.
```
request -> header{"accessToken":"Bearer -- "}
```
```
response
{
"권한 체크가 완료되었습니다"
}

```
