####20210526(수)
- 변하지 않는 변수=상수=변수명을 대문자(원주율)PI=3.141569...
- 보통 상수변수는 클래스의 제일 상단에 위치.
- 기본형(정수자료형-소문자로 시작): byte < short < int < long, 10L(롱타입변수)
- 기본형(실수자료형-소수점, 소문자로 시작): float < double, 12.34f(실수형 숫자)
- 기본형(문자형-'a'단따옴표로 값을 입력): char 1개문자
- 문자형에서 유니코드는 \u숫자 입니다.
- 기본형(문자형-참,거짓): boolean (0|1)
- 참조형(대문자로 시작): 참조형 변수의 대표적인 변수가 클래스입니다.
- 클래스변수(저장된 상태) -> 실행가능하게 되었을때, 인스턴스 변수(메모리로드 상태)라고 함.
- 인스턴스라는 말 보다는 오브젝트라는 말을 더 많이 사용함. (클래스를 오브젝트로 만들었다.)
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