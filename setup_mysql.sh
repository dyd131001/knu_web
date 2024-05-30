#!/bin/bash

# Homebrew 업데이트 및 MySQL 설치 (이미 설치되어 있는 경우 스킵)
if ! brew ls --versions mysql > /dev/null; then
  echo "Homebrew 및 MySQL 설치 중..."
  brew update
  brew install mysql
else
  echo "MySQL이 이미 설치되어 있습니다."
fi

# MySQL 서비스 시작 (이미 실행 중인 경우 스킵)
if ! pgrep -x "mysqld" > /dev/null; then
  echo "MySQL 서비스를 시작합니다..."
  brew services start mysql
  # MySQL 서비스가 시작될 때까지 잠시 대기
  sleep 5
else
  echo "MySQL 서비스가 이미 실행 중입니다."
fi

echo "MySQL root 계정 설정을 원하시면 mysql_secure_installation 명령어를 실행해주세요"
echo "1. VALIDATE PASSWORD COMPONENT ( 복잡한 비밀번호 여부 ) : n"
echo "2. set the password (비밀번호 설정 & 확인)"
echo "3. Remove anonymous users? (익명 사용자 삭제) : "
echo "y (n일경우는 mysql 만으로 접속 가능하다)"
echo "4. Disallow root login remotely? (원격 접속 허용하지 않을 것인지) :"
echo "y(localhost 외 다른 IP에서의 접속을 허용하지 않게 된다.)"
echo "5. Remove test database and access to it? (test DB 삭제 여부) : "
echo "n(이럴경우 삭제되지 않는다.)"
echo "6. Reload privilege tables now? (변경된 권한을 반영하여 테이블 다시 로드) : y"


# MySQL 접속 및 사용자와 데이터베이스 설정
# 기존 사용자와 데이터베이스가 있는지 확인하고, 없으면 생성
mysql -u root -p <<EOF
-- knu_meeting 데이터베이스가 있는지 확인하고, 없으면 생성
CREATE DATABASE IF NOT EXISTS knu_meeting;
-- 'root 사용자에게 권한 부여'
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
EOF

echo "MySQL 설정이 완료되었습니다. 'knu_meeting' 데이터베이스를 생성했습니다."
