# README

## Description
- eformsign 서버에서 요청하는 webhook을 받아서 처리하는 프로그램
- eformsign 홈페이지 내에서 테스트 요청처리 및 로그파일로 기록
- [wiki](https://wiki.oz4cs.com/display/eformsignDev/3.+Webhook) 에서 제공하는 eformsign Webhook 가이드 참조

----------------
## RUN
* 실행 파일 이름 : WebhookApplication.java
* jdk version : 11
* tool : IntelliJ
*  내부 ip 사용시 ngrok.exe 파일 필요
   - [ngrok](https://ngrok.com/download)에서 다운로드 후 실행
   - ngrok http (PORT_NUMBER) 로 실행하여 localhost주소 가져오기
* webhookLog.txt 파일 필요(WebhookApplication.java와 동일 경로)

| 이름          | 입력값                      | 필수여부    |
|-------------|--------------------------|---------|
| token.json  | RefreshToken,AccessToken | X(자동생성) |
| tokenInfo.  | 계정,API Key, 비밀키          | O |
| data/*.json | request body    | O |

----------------
## Function
1. Token 발급
    * eformsign_signature, executeTime 발급
    * Access Token 발급
    * Refresh Token 발급
2. Document
    * 문서 생성(최조 작성자가 회사 멤버)
    * 문서 목록 조회
    * 문서 삭제
    * 문서 정보 조회
    * 문서 파일 다운로드(문서 PDF, 감사추적증명서 PDF)
    * 문서 재요청
    * 작성 가능한 템플릿 목록 조회
    * 문서 일괄 작성
3. Member
    * 구성원 목록 조회
    * 구성원 수정
    * 구성원 삭제
4. Group
    * 그룹 목록 조회
    * 그룹 추가
    * 그룹 삭제
    * 그룹 수정
5. Extra
    * 구성원 추가
    * 템플릿 삭제
    * 도장 정보 조회
    * 도장 추가
    * 도장 수정
    * 도장 삭제
    * 도장 목록 조회
    * 구성원 일괄 추가
    * 초안문서를 다음 단계로 전송
    * 회원 정보 수정
    * 일괄 완료 문서 PDF 전송
    * 템플릿 파일 다운로드
----------------