

## 서버 DB설정
mysql 설치 및 src/main/resources/application.properties에서 DB 접속 정보를 설정해야합니다.
<br>
db설정은 아래의 명령어를 통해 진행할 수 있습니다.
1. chmod +x setup_mysql.sh
2. ./setup_mysql.sh

### setup_mysql.sh 동작과정

1. mysql 설치
2. root 계정설정
3. knu_meeting 스키마 생성 및 root 권한 설정
4. src/main/resources/application.properties 의 password 변경 (root계정의 password)

위의 과정을 모두 실행했다면 아래의 명령어를 통해 서버를 실행할 수 있습니다.

## 서버 실행
서버 실행은 아래의 명령어를 통해 진행할 수 있습니다.
1. chmod +x start_server.sh
2. ./start_server.sh
3. http://localhost:9000/ 접속하여 api 명세서 확인하기

