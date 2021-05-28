package kr.or.test;
/**
 * 1. 오브젝트(객체) 생성과 new키워드(예약어) 생성자메소드 사용 연습
 * 2. 클래스 상속(extends)에 대한 연습
 * 3. 추상클래스(Abstract)에 대한 연습
 * @author 장효원
 *
 */
//오브젝트 생성에 필요한 new키워드 생성자메소드 사용하는 실습클래스(아래)
class Circle {
	private int r; //원의 반지름으로 사용할 변수
	//생성자 메소드(아래)
	//public Circle(){} 만들지 않아도 자동으로 만들어집니다.
	public Circle(int radius) {
		r = radius; //Get|Set 중에 Set저장 멤버변수 처리
	}
	//원의 넓이를 반환하는 리턴값. 그 값의 크기가 double형(아래)
	public double getCircle() {
		double result = r*r*3.14;
		return result;
	}
}

//클래스 상속(extends)에 대한 연습(아래)
class Employee { //고용(취업,사원) 부모클래스
	int mSalary; //m멤버변수, 사원 월급 변수
	String mDept; //사원 부서 변수
	public void doJob() {
		System.out.println("환영합니다. 직원 여러분!");
	}
}
class Salesman extends Employee {//extends확장 -> 상속
	public Salesman () { //클래스명과 같은 이름의 메소드가 생성자 함수입니다.
		//원래 자동으로 생성되는데, 개발자가 커스텀하기 위해선 생성자함수를 만들어야한다.
		mDept = "판매 부서"; //부모클래스인 Employee에서 변수를 상속받았기 때문에 mDept를 정의하지 않아도 됨.
	}
	public void doJob() {
		System.out.println("환영합니다. "+mDept+"여러분!");
	}
}
class Development extends Employee {
	public Development() {
		mDept = "개발 부서";
	}
	public void doJob() {
		System.out.println("환영합니다. "+mDept+"여러분!");
	}
}

//추상클래스 구현 코딩
abstract class GraphicObject {
	int x,y;
	abstract void draw(); //구현내용은 없고 메소드명만 존재(추상메소드)
	//추상클래스를 만드는 이유: 메소드가 수십,수백개일 때 개발자가 구현할때 어려움이 존재
	//추상클래스는 위 문제를 해소하기 위한 기능의 클래스로, 수백개의 메소드를 이름만 모아서 유지보수가 편하게하기 위한 방식.
}
//위의 추상클래스를 사용하는 방법(아래)
//스프링에서는 추상클래스보다는 인터페이스를 더 많이 사용하지만, 사용의도는 비슷하다.(유지보수의 용이성)
class Triangle extends GraphicObject { //추상클래스를 상속받기 위해선 구현메소드가 있어야 함.

	@Override //추상적인 부모클래스의 메소드를 재정의 해서 사용하는것을 오버라이드라고 명칭. / 오버로드: 메소드명이 같으나, 로드된 파라미터의 타입,개수가 다른 메소드를 호출하는 것.(Step1.java)
	void draw() {
		// 삼각형을 만듦.
		System.out.println("  *  ");
		System.out.println(" * * ");
		System.out.println("*****");
	} 	
}
class Rectangle extends GraphicObject {

	@Override //오버라이드 했다. = 상속받았다는 말.
	void draw() {
		// 사각형을 만듦.
		System.out.println("*****");
		System.out.println("*****");
		System.out.println("*****");
	}
	
}
public class ClassApp {

	public static void main(String[] args) {
		//추상클래스를 이용해서 오버라이드 메소드 사용
		//GraphicObject graphicObject = new GraphicObject(); 추상클래스는 객체로 만들지 못함.
		GraphicObject triangleObject = new Triangle(); //그래서 상속받은것을 객체로 만들어서 씀.
		GraphicObject rectagleObject = new Rectangle();
		triangleObject.draw();
		rectagleObject.draw();
				
		// 개발자가 입력한  반지름의 원의 넓이를 구하는 오브젝트를 생성(아래)
		Circle circle = new Circle(5); //반지름이 5인 원의 넓이를 구하는 오브젝트 생성.
		System.out.println("원의 넓이는 "+circle.getCircle());
		circle = null; //오브젝트(메모리) 반환
		
		//클래스 상속과 메소드 실행
		Employee employee = new Employee();
		Salesman salesman = new Salesman();
		Development development = new Development();
		employee.doJob();
		salesman.doJob();
		development.doJob();
	}

}
