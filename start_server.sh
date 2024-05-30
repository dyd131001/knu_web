jar_file="./build/libs/meeting-0.0.1-SNAPSHOT.jar"

if [ -f "$jar_file" ]; then
	echo "기존의 jar파일을 실행합니다."
	java -jar "$jar_file"
else
	echo "jar 파일 생성 후 실행합니다."
	./gradlew build
	java -jar "$jar_file"
fi

