### 나만의 일정관리 앱 서버 만들기!

## 0. Use Case Diagram, API 명세서, ERD

아래 노션에 보기 편하게 작성되어 있습니다.
   
노션 링크 : <https://robust-tiglon-c3e.notion.site/924eb9f5bd264883b0ccfbbeb06a1516>


### 1. Use Case Diagram
    아래 링크와 위의 노션링크에서 확인 가능
    1. 전체일정보여주기(일정 목록 조회기능),
    2. 일정 작성하기,
    3. 선택한 일정 조회하기,
    4. 일정수정하기,
    5. 일정삭제하기

Use case Diagram 링크 : <https://www.figma.com/file/tZNPnBOE98KF246S8OCfWH/%5B%EC%9C%A0%EC%84%A0%EC%95%84%EA%B0%9C%EC%9D%B8%EA%B3%BC%EC%A0%9C%5D_Use-Case-Diagram?type=whiteboard&node-id=0%3A1&t=VVzINqlxuCwpnHG8-1>

### 2. API 명세서
    1. method : POST  
    1-1. URL : /api/schedules 
    1-2. API 설명 : 일정 추가
    
    2. method : GET
    2-1. URL :/api/schedules/{scheduleid}
    2-2. API 설명 : 선택일정 조회
    
    3. method : GET
    3-1. URL :/api/schedules/
    3-2. API 설명 : 일정 목록 조회

    4.  method : PUT
    4-1. URL :/api/schedules/{scheduleid}
    4-2. API 설명 : 선택 일정 수정
    
    5.  method : DELETE
    5-1. URL :/api/schedules/{scheduleid} 
    5-2.  API 설명 : 선택 일정 삭제
    
### 3. ERD
    
    E: `할일 schedule`
    A : 할일은 `고유번호`,`할일 제목`,`할일 내용`,  `비밀번호`, `작성일` 등의 속성을 가진다.
    `scheduleID`,`title`, `contents`, `password`, `date`
    R : `담당자`는 여러 `할일`을 배정받을 수 있다. 담당자와 할일은 1: N 관계다. -> 하지만 담당자별로 조회가능한 기능은 구현하지 않았다.
  ERD 링크 : <https://www.erdcloud.com/d/4KRjnXDqvAyHi99ym>

### 4. 기능구현
- [x]  일정 작성 기능
    - [x]  `@PostMapping("/schedules")` createSchedule
    - `할일 제목`,`할일 내용`, `담당자`, `비밀번호`, `작성일`을 저장할 수 있습니다.
    - 저장된 게시글의 정보를 반환 받아 확인할 수 있습니다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외 되어있습니다.
- [x]  선택한 일정 조회 기능
    - [x]  `@GetMapping("/schedules/{id}")` getOneSchedules
    - 선택한 일정의 정보를 조회할 수 있습니다.
        - 반환 받은 일정 정보에 `비밀번호`는 제외 되어있습니다.
- [x]  일정 목록 조회 기능
    - [x]  `@GetMapping("/schedules")` getSchedules
    - 등록된 일정 전체를 조회할 수 있습니다.
        - 반환 받은 일정 정보에 `비밀번호`는 제외 되어있습니다.
    - 조회된 일정 목록은 `작성일` 기준 내림차순으로 정렬 되어있습니다.
- [x]  선택한 일정 수정 기능
    - [x]  `@PutMapping("/schedules/{id}")` updateSchedule
    - 선택한 일정의 `할일 제목`,`할일 내용`, `담당자`을 수정할 수 있습니다.
        - [x]  서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달합니다.
        - [x]  선택한 일정의 `비밀번호`와 요청할 때 함께 보낸 `비밀번호`가 일치할 경우에만 수정이 가능합니다.
    - 수정된 일정의 정보를 반환 받아 확인할 수 있습니다.
        - 반환 받은 일정의 정보에 `비밀번호`는 제외 되어있습니다.
- [x]  선택한 일정 삭제 기능
    - [x]  `@DeleteMapping("/schedules/{id}")` deleteSchedule
    - 선택한 일정을 삭제할 수 있습니다.
        - 서버에 일정 삭제를 요청할 때 `비밀번호`를 함께 전달합니다.
        - 선택한 일정의 `비밀번호`와 요청할 때 함께 보낸 `비밀번호`가 일치할 경우에만 삭제가 가능합니다.
