#2021-07-14 NAM 03:00 (24시간 기준) (완성 못함)

사용 언어 JAVA 


1. HTTPS -> HTTP REST API, GET 방식으로 제품 리스트 JSON 받기 완료 (SSL 인증 문제로 변경)
2. JSON 파싱 완료
3. SQLITE 생성 및 INSERT 완료
4. 재 실행시 DB 파일이 있는 경우 1~3번 과정 생략 완료(최초 한번만 적용)
5. 상단 상태바 미 출력 설정 완료 (FULL SCEEN SETTING)
6. 플래그먼트와 탭바를 이용한 메뉴 구성 진행중 (tab bar는 카테고리 구분 db에서 select 문으로 중복 제거 보이게 설정)
7. 메뉴 리스트 보여지도록 arraylist 구현 완료 (db에서 select 문으로 노출)

현시간 진행 단계
8. RECYCLER VIEW로  GRID VIEW 4개로 보이도록 구현 진행 중. 수직 스크롤 동작 설정 (21-07-14 02:54 현재)
9. 버튼 누른경우 색상 변경 애니메이션 적용 완료 

진행 해야 하는 상황
10. FRAGMENT 방식 개발의 미숙으로 인한 제품 리스트 보여주는 도록 하는 것 진행 중 (프래그먼트 구현 미숙)
11. RECYCLER VIEW 제품 사진 클릭 시 장바구니 기능 미 구현 (UI 디자인은 되어 있음)
12. 상품 리스트 비율 변경은 미 구현 장바구니 버튼 선택 시, RECYCLER VIEW 비율 변경 
13. 주문완료 메시지 박스, 미 구현



문의 사항
1. 슬라이딩 레이아웃(카테고리 슬라이딩 기능이란? 어떤 부분에 들어가는 것을 이야기 하는것인지) 
2. PDF에 있는 회원 가입 로그인 기능은 구현 해야하는가? 
    (플로우 내용에 없는 내용이며, REST API POST 방식으로 보낼 링크 주소 없음)
    
