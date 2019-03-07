package lesson.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloWorld implements Servlet {

	ServletConfig config;
	
	@Override
	public void destroy() {
		// 서블릿 컨테이너가 종료되거나 웹 애플리케이션이 멈출 때,
		// 또는 해당 서블릿을 비활성화 시킬 때 호출된다.
		// 따라서 이 메서드에는 서비스 수행을 위해 확보했던 자원을
		// 해제한다거나 데이터를 저장하는 등의 마무리 작업을 작성한다.
		System.out.println("destroy() 호출");
	}

	@Override
	public ServletConfig getServletConfig() {
		// 객체를 반환하는 역할
		System.out.println("getServletConfig() 호출");
		return this.config;
	}

	@Override
	public String getServletInfo() {
		// 서블릿 정보를 추출할 필요가 있을 때
		System.out.println("getServletInfo() 호출");
		return "서블릿 정보 돌려줌";
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// 서블릿을 생성한 후 초기화 작업을 수행하기 위해 호출하는 메서드
		// 서블릿이 클라이언트의 요청을 처리하기 전에 준비할 작ㅈ업이 있다면
		// 이 메서드에 작성해야 한다.
		// ex:) 데이터베이스 연결 등
		System.out.println("init() 호출");
		this.config = config;
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// 클라이언트가 요청할 때마다 호출되는 메서드
		// 실질적으로 서비스 작업을 수행하는 메서드
		System.out.println("service() 호출");
	}

}
