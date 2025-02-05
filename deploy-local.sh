#!/bin/bash

echo "배포 스크립트 시작..."

# 빌드
echo "1. Gradle 빌드 시작"
./gradlew clean build

if [ $? -eq 0 ]; then
    echo "2. 빌드 성공"
    
    # 임시 디렉토리 생성
    echo "3. EC2 임시 디렉토리 생성"
    ssh -i /Users/cheolminhan/Downloads/sangji_mac.pem ubuntu@54.180.200.124 "mkdir -p ~/app/temp"
    
    # JAR 파일 압축
    echo "4. JAR 파일 압축"
    cd build/libs
    gzip -c sangji-0.0.1-SNAPSHOT.jar > sangji.jar.gz
    
    # 파일 분할
    echo "5. 파일 분할"
    split -b 5m sangji.jar.gz "sangji.gz.part."
    
    # 파일 전송
    echo "6. 파일 전송 시작"
    for f in sangji.gz.part.*; do
        echo "전송 중: $f"
        scp -i /Users/cheolminhan/Downloads/sangji_mac.pem "$f" ubuntu@54.180.200.124:~/app/temp/
    done
    
    # 서버에서 파일 재조합 및 재시작
    echo "7. 서버에서 파일 재조합 및 애플리케이션 재시작"
    ssh -i /Users/cheolminhan/Downloads/sangji_mac.pem ubuntu@54.180.200.124 "cd ~/app/temp && \
        cat sangji.gz.part.* > sangji.jar.gz && \
        gzip -d sangji.jar.gz && \
        mv sangji.jar ../sangji-0.0.1-SNAPSHOT.jar && \
        cd .. && \
        rm -rf temp && \
        pid=\$(ps aux | grep sangji-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{ print \$2 }') && \
        if [ ! -z \"\$pid\" ]; then kill -9 \$pid; fi && \
        nohup java -jar sangji-0.0.1-SNAPSHOT.jar > app.log 2>&1 &"
    
    echo "8. 배포 완료!"
else
    echo "빌드 실패"
    exit 1
fi
