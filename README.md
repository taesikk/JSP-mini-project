# README

## Description
- eformsign에서 제공하는 서비스를 이용한 eformsign 기능 처리 프로젝트
- eformsign의 openAPI, 임베딩, 웹훅의 코드와 가이드라인을 제시
- [openAPI](https://app.swaggerhub.com/apis-docs/eformsign_api/eformsign_API_2.0/2.0#/) 와 홈페이지에서 제공하는 [API 문서](https://eformsignkr.github.io/developers/help/eformsign_api_overview.html) 의 openAPI, 임베딩, 웹훅 가이드 참조

----------------
## RUN
* 실행 파일 이름 : WebhookApplication.java (./src/main/java/com/forcs/eformsign/webhook)
* jdk version : 11
* tool : IntelliJ
   - eclipse 사용 시 : File - Import... - Gradle - Existing Gradle Project - Next - 프로젝트 선택 - Finish
* Default Tomcat port : 8080 (필요시 resources내 application.properties에서 변경 가능)
*  웹훅 Endpoint URL로 내부 ip 사용시 ngrok.exe 파일 필요
   - [ngrok](https://ngrok.com/download)에서 다운로드 후 실행
   - ngrok http (PORT_NUMBER) 로 실행하여 localhost주소 가져오기(비로그인 시 2시간 유효)
   - Endpoint URL 설정 : (ngrok로 부여받은 address)/main
* 프로젝트 메인 페이지 URL : localhost:8080/home

### 구동원리
* html은 페이지 및 임베딩을 나타내고 form태그의 action을 이용한 url을 전송합니다.
* html로부터 요청받은 url을 controller를 통해 java내에서 처리합니다.
* java에서 처리한 결과 및 데이터를 model에 추가하여 jsp에서 표시해줍니다.

-------------
## Setting
* 사용자가 입력해야할 목록을 설명합니다.
* ./src/main/java/com/forcs/eformsign/webhook/openAPI/data/ 
* ./src/main/resources/templates/

| 이름              | 설명                               | 필수여부    |
|-----------------|----------------------------------|---------|
| tokenInfo.json  | 계정,API Key, 비밀키                  | X       |
| token.json      | RefreshToken,AccessToken         | X(자동생성) |
| data/*.json     | request body                     | O       |
| Document_*.html | 임베딩 시 필요한 html. 내부의 템플릿 옵션 변경 필요 | O       |

* tokenInfo.json의 경우 프로젝트 시작 후 설정 탭에서 지정 가능
* request body의 경우 [swagger](https://app.swaggerhub.com/apis-docs/eformsign_api/eformsign_API_2.0/2.0#/) 에서 제공하는 예시 참조
----------------
## openAPI
* openAPI의 기능 설명 및 위치를 안내합니다.
* 위치 : ./src/main/java/com/forcs/eformsign/webhook/openAPI
* openAPI에 관한 로그는 resources/logback/syslog/APILog.txt 에 기록

| 이름      | 설명                                    |
|---------|---------------------------------------|
| commmon | api url 정보 및 json 파일을 읽어 변수에 저장하는 클래스 |
| data    | api전송에 필요한 json 파일                |
| method  | api의 실질적인 코드                          |
 
<구현된 API 목록>
* 토큰 발급
* 토큰 갱신
* 작성 가능한 템플릿 목록 조회
* 새 문서 작성
* 문서 일괄 작성
* 문서 목록 조회(진행중, 완료, 문서관리)
* 문서 재요청
* 문서 삭제
* 구성원 목록 조회
* 구성원 수정
* 구성원 삭제
* 그룹 목록 조회
* 그룹 추가
* 그룹 삭제
* 그룹 수정

------------
## Webhook
* 문서 처리시 서버로부터 오는 웹훅을 처리합니다.
* endpoint url을 .../main으로 설정하면 controller를 통해 데이터를 받아옵니다.
* 웹훅 요청의 ip주소를 포함한 데이터를 logback을 통해 기록합니다.
* 로그 기록 위치 : ./src/main/resources/logback/syslog/ 에 기록됨.
------------
## 임베딩
* 문서 작성 및 일괄 작성시 실행됩니다.
* html내 옵션값을 설정한 후 실행.
* 위치 : ./src/main/resources/templates

----------------