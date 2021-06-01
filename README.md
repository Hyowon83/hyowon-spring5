####20210601(화) 작업예정.
- 4장 패키지와 예외처리 실습..

####20210531(월) 작업내역
- sql쿼리를 3가지로 분류:
- 초기DDL문: Data Definition Language. 데이터 정의언어 Create table문
- 유지DCL문: Data Control Language. 데이터 제어언어 Commit, rollback
- 개발DML문: Data Manufacture Language.데이터 조작언어 CRUD 쿼리.
- 토드(SQL디벨로퍼와 비슷한 상용 프로그램): 버튼하나로도 포워드엔지니어링이 가능.
- 무료인 SQL디벨로퍼에서는 버튼X, DDL문을 실행해서 포워드엔지니어링을 처리.
- 참고로, Mysql용 워크벤치는 무료지만 버튼으로 포워드 엔지니어링이 가능.
- ERD그림 만든것을 물리테이블로 만드는것: Forward 엔지니어링
- 데이터 딕셔너리를 모델과 동기화: 자료사전을 DB테이블과 동기화
- 데이터 딕셔너리는 메타 데이터와 동일합니다.: 콘텐츠X, 구조, 구성정보만 존재
- 물리테이블을 ERD그림으로 만드는것: Reverse 엔지니어링(기존 DB분석시 시용)
- 스프링시큐리티 2단계(구현예정임): 1.로그인인증(ENABLED) - AUTHENTICATION 2. 권한체크(LEVELS) - AUTHORITY (관리자는 admin URL접근가능, 일반회원은 사용자 홈페이지에서 공지사항수정X 겔러리만 사용가능, 비회원은 글쓰기X adminX 관리자X)
- 1:N 관계? 게시물1개에 댓글 여러개. 카테고리 하나에 게시물 여러개.
- E-R다이어그램은 그림일 뿐, 물리DB(테이블)은 ERD기준으로 생성가능.
- PK를 AI(Auto Increment 자동증가)로 만들기: 오라클(시퀸스객체로 기능구현),Mysql(AI라는 필드속성으로 처리)
- ERD에서 Relation생성: 게시판타입테이블(부)과 게시물관리테이블(자)의 관계를 생성
- 부자의 관계는 부모의 PK를 기준, 자녀에게는 PK가 FK(Foreign Key)로 관계를 맺음.
- 게시판타입: notice, gallery 2가지가 존재한다면,
- Relation관계가 필요한 이유: 공지사항이나 겔러리 게시판이 아니면, 개발자가 예외처리를 하지 않아도 다른 게시판으로 등록되는 상황이 발생되지 않게함.
- 데이터 무결성을 유지하는 역할.(외래키관계)
- 필드 데이터형3: timestamp(시간도장) 년월일시분초밀리초까지, Date 년월일까지
- 필드 데이터형2: CLOB(CHAR LONG BYTE) 글 내용이 많을 때 사용하는 데이터 형태
- 필드 데이터형: VARCHAR2(2바이트를 1글자=한글), VARCHAR(1바이트 1글자=영어)
- 테이블구성: 필드(컬럼,열) = 테이블의 멤버변수(자바vo클래스의 멤버변수)
- 필드 구성시, PK(PrimaryKey): 주키,기본키,고유키 = 테이블 영역에서 고유한 ID를 말함.(중복되지 않는 값.)
- PK(개인을 식별할 수 있는 값): 로그인id, 주민번호, 개인이메일 등 
- RDBMS: RelationDataManagementSystem 관계형 데이터베이스 관리 시스템
- 오라클: 테이블스페이스(TableSpace) = 스키마(Scheme:Mysql) = 데이터베이스(DB:MS-SQL)
- 지난주 금요일, 오라클 웹용 관리 프로그램에서 XE라는 테이블스페이스를 XE사용자로 추가했음
- EntityRelationDiagram(ERD-객체관계그램): Entity = 테이블
- 데이터모델: Model Object를 형상화시킨 것을 모델이라고 함. 데이터베이스를 말함.
- MVC 스프링 프로젝트에서 M=Model, 스프링프로젝트 구성 중 DB를 가르킨다. V=View(jsp), C=Controller(클래스)
- 암호는 apmsetup으로 통일
- 4장 패키지 예외처리 실습.
- 스프링프로젝트 ERD 제작 후 세부UI디자인 적용하기.

####20210528(금) 작업내역
- 추상클래스 실습은 오늘, 인터페이스 실습은 스프링에 엄청하시게 됩니다.
- extends관계클래스에서 this.(현재클래스) , super.(부모클래스)
- 다형성? 메서드 @오버라이드(상속), 메서드 오버로드(동일함수명의 파라미터의 개수, 종류틀린메서드) 구현됩니다.
- 오버라이드: 상속받아서 재정의 메서드 @오버라이드 = 다형성구현했다.
- 오라클 11g ExpressEdition 설치예정. OracleXE112_Win64.zip
- SQL디벨로퍼를 다운받아서 압춘풀기 - ERD제작 예정
- SQL(StructuredQueryLanguage): 구조화된 질의 언어(오라클서버) -> 답변
- QueryString: URL에서 전송하는 값(서버에 질의문)을 문장으로 저장한 내용. ?로 시작.
- CommendLineInterface: SQL*Plus 터미널화면으로 SQL쿼리실행 X.
- GrapicUserInter: SQL디벨러퍼 윈도우 화면 에디터에서 SQL쿼리 실행 O.
- SQL Developer 이 프로그램으로 ERD 다이어그램 그림으로 프로젝트 구조 제작.
- EntityRelationDiagram: 테이블관계도형,(아래)
- 첨부파일(자식)->게시판테이블(부모)<-댓글테이블(자식)
- 프로젝트진행: 발주(클라이언트) -> 수주(개발사) -> 디자인UI(Client-Dev) -> ERD(이사나 팀장급) -> 코딩시작(ERD보면서 UI소스에 프로그램 입히기)
- ERD에서 SQL쿼리가 생성 -> 물리테이블을 생성
-----------------------------------------------------------------------
- 자바앱에서는 객체를 생성 후 사용이 끝나면, 메모리에서 삭제하는 명령이 필수.
- 객체를 메모리에서 삭제: Object = null; Object.close();
- 스프링에서는 내장된 가비지 컬렉터(garbage쓰레기수집가)가 자동실행돼서 자동으로 사용하지 않는 객체를 지워줌.
- 위와 같이 개발자가 하던 메모리관리를 스프링의 대신 = IoC(Inversion Of Control)제어의 역전. 스프링 특징 3가지(IoC, AOP, DI)
- 수업시작전, static메서드와 객체의 멤버매서드 비교설명
- Step2클래스에서 MeberService 클래스에 직접접근해서 메서드를 실행하려면 static으로 변경(컴파일시 실행가능한 상태로됨=메모리에 로딩)해야 함. 
대신, memberServie객체을 이용해서 메서드에 접근할때는 (호출시=런타임시 실행이가능한 상태로됨=메모리에 로딩)
- 클래스와 상속에 대해서 이론 및 실습
- https://github.com/miniplugin/spring5-kimilguk/blob/master/src/test/java/kr/or/test/ClassApp.java

####20210527(목) 작업예정
- 3장 객체와 클래스부터 시작
- 스프링에서는 클래스 상속(extends) 보다는 인터페이스(implements)를 더 많이 사용하기 때문에 상속은 보기 힘들다.
- abstract클래스(추상클래스): 구현내용이 없어, 메소드명만 있는 클래스.
- 메소드명만 있기때문에 *객체로 만들 수 없는 클래스*입니다.
- 부모로서 자식에게 상속만 해서 메소드이름만 재사용만 가능하게 됩니다.
- *객체로 만들 수 없다? 실행가능한 클래스로 사용은 못하지만, 프로그램을 구조화시킬 때 필요*하다.
- Step1 aaa = new Step1(); //이렇게 생성자로 객체를 만들지 못함.
- final클래스: 부모가 될 수 없는 클래스(상속해서 재사용이 불가능한 클래스)
- 접근제어자: public(패키지위치에 상관없이 접근 - 제일 많이 사용)
- public 클래스 안에서 멤버변수는 private을 제일 많이 사용.(private보안성 갖춤)
- public 클래스 안에서 멤버메소드는 public을 많이 사용. (외부 다른 클래스의 메소드를 실행가능.) 대신, 변수가 private이기 때문에, 직접 수정 안됨. 실행만 됩니다.
- 인스턴스(클라우드에서 주로사용하는 용어) = 오브젝트 = 객체 = 실행중인 클래스
- 클래스 자료형(int, long, String처럼)은 멤버변수, 멤버메소드, 서브클래스 등등 포함할 수 있다. = C언어 구조체 자료형
- 이클립스에서 커밋 또는 푸시가 안될때: force버튼 체크

####20210526(수)
- 붕어빵: 붕어빵틀(클래스) -> 붕어빵(오브젝트)
- 클래스변수(저장된 상태) -> 실행가능하게 되었을때, 인스턴스 변수(메모리로드 상태)라고 함.
- 인스턴스라는 말 보다는 오브젝트라는 말을 더 많이 사용함. (클래스를 오브젝트로 만들었다.)
- OOP: 자바는 객체지향(클래스를 실행가능하게 하는)프로그래밍.
- 객체(Object)와 클래스? 클래스형 자료를 실행 가능하게 만든것을 오브젝트(객체)라고 함.
- 객체 = 오브젝트 = 인스턴스 = 실행가능한 클래스
- 추상화(Abstract): 오프라인 업무 -> 대표 업무만 뽑아낸 클래스 = 추상클래스
- 클래스는 <멤버변수 + 멤버메소드(실행) + 서브클래스>가 포함 될 수 있음.
- 변하지 않는 변수=상수=변수명을 대문자(원주율)PI=3.141569...
- 보통 상수변수는 클래스의 제일 상단에 위치.
- 기본형(정수자료형-소문자로 시작): byte < short < int < long, 10L(롱타입변수)
- 기본형(실수자료형-소수점, 소문자로 시작): float < double, 12.34f(실수형 숫자)
- 기본형(문자형-'a'단따옴표로 값을 입력): char 1개문자
- 문자형에서 유니코드는 \u숫자 입니다.
- 기본형(문자형-참,거짓): boolean (0|1)
- 참조형(대문자로 시작): 참조형 변수의 대표적인 변수가 클래스입니다.
- String(문자열 클래스변수): 대문자로 시작.
- 상수변수를 명시적으로 만들때: final int MAX = 100;
- for-each 반복문 전까지 실습

####20210525(화) 작업내역
- 스프링MVC프로젝트: Model View Controller? 약자MVC구조(웹프로그램구조)
- src/test/java폴더: 테스트작업은 이 폴더에서 하라는 약속.
- src/main/java폴더: 진짜 프로그램 작업을 하는 폴더.
- javac HelloWorld.java -encoding UTF-8 (한글 내용도 컴파일됨.)
- 위 javac(자바컴파일러)로 실행한 결과 -> HelloWorld.class 실행파일이 생성됨.
- 주의) 클래스파일은 실행 패키지의 루트(최상위)에서 실행해야 함.
- kr.or.test패키지 root폴더 src/test/java폴더에서 실행을 해야함.
- java kr.or.test.HelloWorld 클래스를 실행하게 됨.
- 이클립스 나오기 전(25년 전), javac 컴파일에서 class프로그램을 만들었습니다.
- 지금은 터미널에서 javac 사용하지 않고, 이클립스에서 바로 실행합니다.
- javac? 자바앱 컴파일러 -> 클래스 실행파일을 만든다.class(바이트코드) (자바환경JVM실행)
- 메이븐(Maven)? 웹프로그램 컴파일러 -> 웹앱 실행파일.war을 만든다.(톰캣에서 실행)
- 메이븐이 컴파일 할 때, 자바 소스만 컴파일 하는게 아니고 외부 라이브러리로 가져와서 바인딩(묶어줌)하게 된다. = 패키징이라고 함. = .war(와르)파일로 패키징 함.
- 메이븐이 관리하는 외부 라이브러리 파일(lib) 목록을 저장하는 파일 = pom.xml
- pom.xml에서 자바버전을 1.6 -> 1.8 변경 후 메이븐 업데이트.
- 외부라이브러리 파일을 참조하는 방식을 영어로 = dependency
- .jar? JavaARchive = jar 자바클래스를 패키징 한 파일

####20210524(월) 작업내역
- 자바기초 실습
- 자바스크립트는 함수(Function)구조의 프로그래밍.
- 자바는 객체지향 프로그래밍. (Object Oriented Program)
- 객체(Object) = 실행 가능한 클래스(Class)
- 아스키, 유니코드(UnicodeTypeFormet-8)
- 아스키코드 IoT에서 데이터 전송시 필수로 확인해야합니다. 0을 전송 -> 48,49값을 받습니다.
- IoT프로그램시 하드웨어 값을 주고받을 때 if(var1 == 48) {구현내용}
- 공유기가 하위로 가질 수 있는 사설 IP는 공유기 본인IP + 255개(여유분) = 256개의 인터넷 가능.
- 영어인코딩은 아스키코드로 다 표현 가능.
- 단, 한글인코딩, 중국어/일본어 인코딩 등등은 아스키코드로 다 표현 못함. 그래서 유니코드가 등장. UTF-8
- Hex(16진수), Dec(16진수), Char(문자) = 127개 = 아스키문자의 크기(컴-사람문자 1:1매칭)
- Oct(8진수), Bin/Bit(2진수)
- 아스키코드-7비트코드(127글자) -> ANSI코드-8비트(256글자) -> UniCode(65536글자)-UTF8
- UTF8mb4(이모지까지 포함: 이모티콘+이미지)
- 자바언어를 한다는 것은 컴파일 후 실행이 된다는 것.(실습예정)
- 자바스크립트는 컴파일 없이 그냥 실행해서 프로그램이 만들어짐.(실습예정)