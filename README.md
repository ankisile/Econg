## 친환경 제품 크라우드 펀딩 앱 Econg
![제목을 입력해주세요_-001](https://user-images.githubusercontent.com/53250432/235400210-86721d50-0423-4315-b4ae-386532caa3da.png)
> Econg / 제 10회 K-해커톤 대회
- **[안드로이드 깃허브](https://github.com/jejxis/Econg_Final)**
- [서버 깃허브](https://github.com/ankisile/Econg)
- [데모시연영상](https://drive.google.com/file/d/15sDyizRijrf6OM9K-Zg0xlXpAKks5Uak/view?usp=sharing)
- [발표자료](https://docs.google.com/presentation/d/1FV0qLNpZK1FSXkrlF-46xBQvvPXkVE2A/edit?usp=share_link&ouid=100934178736454734095&rtpof=true&sd=true)
- [명세서](https://docs.google.com/spreadsheets/d/1nZ5lGBsN1GYKoJO79iOhLn1kQwKNZGLz/edit?usp=sharing&ouid=100934178736454734095&rtpof=true&sd=true)
- [발표영상](https://www.youtube.com/live/SbdqYePqio8?feature=share&t=7167)

### Description
```
'Econg'은 친환경 제품 제작자와 구매자가 원할하게 온라인 상에서 상품을 판매하고, 이용할 수 있게 도와주는 친환경 제품 크라우드 펀딩앱입니다. 
제로웨이스트 운동이 커져감에 따라 친환경 제품 구매가 활발해지고 있습니다. 따라서, 다양한 친환경 제품을 제공하고 구매할 수 있는 앱을 개발하고자 했습니다.
'Econg'에서 사용자는 구매하고 싶은 제품을 크라우드 펀딩을 이용하여 펀딩합니다. 또한, 친환경 제품 및 크리에이터 리스트를 제공하여 사용자가 접근하기 용이하게 만들었습니다. 
결제 기능은 카카오 페이 API를 이용하여 구현했습니다.
```
### Tech Stack
#### Tech Stack
<img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white">   <img src="https://img.shields.io/badge/JPA-007396?style=for-the-badge&logo=JPA&logoColor=white">  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=yellow">  <img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">   <img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=black">   <img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=aws&logoColor=white">   <img src="https://img.shields.io/badge/kakao-FFCD00?style=for-the-badge&logo=KAKAO&logoColor=black">  

#### ERD 설계
![에콩](https://user-images.githubusercontent.com/53250432/224109371-80203370-141c-4327-84fe-4d57a9fcbee2.png)

#### 카카오페이 api 사용
![프레젠테이션1](https://github.com/ankisile/Econg/assets/53250432/5fd80be5-d441-4b29-a6d8-f4fbf1710aed)
1. 클라이언트에서 서버로 주문관련정보를 POST    
2. 서버는 클라이언트에서 받은 값을 카카오 결제 준비 API(/v1/payment/ready)로 POST      
3. 요청이 성공하면 카카오는 tid(결제코드), 카카오톡 결제 페이지 Redirect URL, 카카오페이 결제 화면으로 이동하는 Android 앱 스킴(Scheme)을 서버에 주고 서버는 클라이언트에 redirect url을 던져준다.      
4. 클라이언트는 next_redirect_app_url 값으로 결제 대기 화면 웹뷰를 띄운다. 카카오톡 결제 화면으로 이동하는 커스텀 앱 스킴(Custom App Scheme)은 자동 호출된다. 사용자는 결제 화면으로 이동해, 결제 수단을 선택하고 비밀번호를 입력해 결제를 진행한다.       
(안드로이드에서 next_redirect_app_url을 인텐트에 넣어서 열었다. 암시적 인텐트라는 것 같다. by 안드로이드 팀원)       
5. 결제가 성공적으로 진행되면 결제 준비 API 요청 시 전달 받은 approval_url에 pg_token 파라미터를 붙여 리다이렉트 된다.      
6. 서버는 카카오 결제 승인 API(/v1/payment/approve)로 POST     
7. 결제 승인 완료!    

### Author
- 기획: 송민영, 김나진
- Android: 김나진  
- Server: 송민영
- 디자인: 김나진

### Award
- 제 10회 K-해커톤 대회 장려상 수상
![KakaoTalk_20230501_200516199](https://user-images.githubusercontent.com/53250432/235453244-7f89717d-ffa1-4400-a97f-e2732c93b570.jpg)
