# \*ScheduleSkilled 과제

---
---

## 과제 설명에 앞서

설명에 앞서 해당 프로젝트에 결과물은 devMain 브랜치에 담겨있습니다.

과제의 lv 별로 이슈를 생성하였고, 따로 리드미에 lv별 요구사항은 생략했습니다. 

그리고 과제 초반에 이슈를 작성하여 내용이 맞지않는 경우는 따로 수정 진행하지는 않았습니다.

브랜치를 이슈별로 만들어 해당 이슈에 맞게 코드를 작성했습니다. lv 별 과제 현황을 볼 수 있습니다.

중간중간 잘못된 부분을 늦게 알게 된 경우, 다음 이슈 브랜치에 수정하도록 노력했습니다.

---
---

## Lv 0. API 명세 및 ERD 작성 

\* **API 명세**

|기능|method|url|param,body,query|request|rosponse|상태코드|로그인 유무|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|유저 등록|post|/users|body|requestdto|responsedto|201 CREATE|x|
|유저 단건 조회|get|/users/{id}|param|requestdto|responsedto|200 OK, 404 NOT_FOUND|o|
|유저 수정|patch|/users/{id}|param,body|requestdto|responsedto|200 OK, 401 UNAUTHORIZED, 404 NOT_FOUND|o|
|유저 삭제|delete|/users/{id}|param|x|x|204 NO_CONTNENT|o|
|로그인|post|/login|body|requestdto|String|200 OK, 401 UNAUTHORIZED, 404 NOT_FOUND|x|
|스케줄 등록|post|/schedules|body|requestdto|responsedto|201 CREATE|o|
|스케줄 단건 조회|get|/schedules/{id}|param|requestdto|responsedto|200 OK, 404 NOT_FOUND|o|
|스케줄 수정|patch|/schedules/{id}|param,body|requestdto|responsedto|200 OK, 404 NOT_FOUND|o|
|스케줄 삭제|delete|/schedules/{id}|param|x|x|204 NO_CONTNENT|o|
|댓글 등록|post|/schedules/{scheduleId}/comments|body|param(@PathVariable("scheduleId")),requestdto|responsedto|201 CREATE, 404 NOT_FOUND|o|
|댓글 단건 조회|get|schedules/{scheduleId}/comments/{commentId}|param(@PathVariable("commentId"))|requestdto|responsedto|200 OK, 404 NOT_FOUND|o|
|댓글 수정|patch|schedules/{scheduleId}/comments/{commentId}|param(@PathVariable("commentId")),body|requestdto|responsedto|200 OK, 404 NOT_FOUND|o|
|댓글 삭제|delete|schedules/{scheduleId}/comments/{commentId}|param(@PathVariable("commentId"))|x|x|204 NO_CONTNENT, 404 NOT_FOUND|o|

\* **ERD**
![comment](https://github.com/user-attachments/assets/69946abf-7eee-418f-8a9b-48e52e0e7396)

\* **SQL**
schedule.sql - file

---
---

## 회고

과제를 진행하면서 이전 과제를 통해 했으면 했던 것과 해봤지만 제대로 수행하지 못한 것을 더 집중해서 해보았습니다.

해당 과제에선 git commit convention 과 branch를 나누는 것에 더 신경을 썼습니다. 

그리고 이전 과제에서 시간 관계 상 과제의 진도를 많이 못나갔던 점은 생각해, 

해당 과제에선 많은 코딩 경험을 위해 코드에 리펙토링보다는 하는 것에 중점을 두었습니다.

코드를 작성하면서 자잘한 트러블이 있었지만, 대게 이전에 경험한 것들이라 많은 트러블 슈팅을 작성하지는 못했습니다.

코드의 설명이나 코드를 쓰면 느낀점은 코드 본문에 주석 처리하였습니다.





