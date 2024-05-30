# knu_web

서버 관련 세팅

mysql 설치 및 src/main/resources/application.properties에서 DB 접속 정보를 설정해야합니다.
db설정은 아래의 명령어를 통해 진행할 수 있습니다.
chmod +x setup_mysql.sh
./setup_mysql.sh

setup_mysql.sh 동작과정
1. mysql 설치
2. root 계정설정
3. knu_meeting 스키마 생성 및 root 권한 설정

이후 src/main/resources/application.properties 에서
spring.datasource.password="password"
에 root계정의 password를 입력하면 됩니다.

아래의 명령어를 통해 서버를 실행할 수 있습니다.
chmod +x start_server.sh
./start_server.sh

9000번 포트를 통해 api 명세서를 확인할 수 있습니다.
