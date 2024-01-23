1. Use Case Diagram
    
    링크 : https://www.figma.com/file/tZNPnBOE98KF246S8OCfWH/%5B%EC%9C%A0%EC%84%A0%EC%95%84%EA%B0%9C%EC%9D%B8%EA%B3%BC%EC%A0%9C%5D_Use-Case-Diagram?type=whiteboard&node-id=0%3A1&t=q1zrbDYZB8NMUvA2-1

2. API 명세서
POST /api/schedules 
GET  /api/schedules/{scheduleid}
GET  /api/schedules 
PUT /api/schedules/{scheduleid}
DELETE /api/schedules/{scheduleid} 
    
   3. ERD
    
    E: `할일 schedule`
    A : 할일은 `고유번호`,`할일 제목`,`할일 내용`,  `비밀번호`, `작성일` 등의 속성을 가진다.
    `scheduleID`,`title`, `contents`, `password`, `date`
    R : `담당자`는 여러 `할일`을 배정받을 수 있다. 담당자와 할일은 1: N 관계다.
   링크 : https://www.erdcloud.com/d/4KRjnXDqvAyHi99ym
