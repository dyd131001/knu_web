openjdk 17 환경에서 동작가능합니다.

## mac 기준 설정 방법

sh파일을 만들려했으나 mac 기준 bash와 zsh 중 어떤걸 쓰는지 모르고 디렉토리 위치가 다를 수 있어 작성하지 못했습니다.
<br>
zsh과 jdk-17.jdk의 위치가 /Library/Java/JavaVirtualMachines 일때의 기준으로 설치 방법입니다.
<br>
### find / -type d -name "jdk-17.jdk" 명령어를 통해 jdk의 위치를 알 수 있습니다.
<br>

1. brew install openjdk@17
2. JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home"
3. echo "JAVA_HOME을 설정하고 PATH에 추가합니다."
4. echo "export JAVA_HOME=$JAVA_HOME" >> ~/.zshrc
5. echo 'export PATH=$PATH:$JAVA_HOME/bin' >> ~/.zshrc
6. source ~/.zshrc

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

