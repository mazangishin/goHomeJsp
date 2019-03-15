package com.tg.mymember;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/mymember/list")
public class MyMemberList extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "jsp";
		String password = "jsp";
		
		String sql = "";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
						
			sql = "SELECT MNO, MNAME, EMAIL, PWD, CRE_DATE, MOD_DATE";
			sql += " FROM MEMBERS";
			sql += " ORDER BY MNO ASC";
			
			pstmt = conn.prepareStatement(sql);
					
			rs = pstmt.executeQuery();
			
			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");
			
			// request에 회원 목록 데이터 보관
			ArrayList<MyMemberDto> myMemberList = new ArrayList<MyMemberDto>();
			
			int mno = 0;
			String mname = "";
			String email = "";
			String pwd = "";
			Date creDate = null;
			Date modDate = null;
			
			// 데이터베이스에서 회원 정보를 가져와 MemberDto에 담는다
			// 그리고 MemberDto객체를 ArrayList에 추가한다
			while(rs.next()) {
				mno = rs.getInt("MNO");
				mname = rs.getString("MNAME");
				email = rs.getString("EMAIL");
				pwd = rs.getString("PWD");
				creDate = rs.getDate("CRE_DATE");
				modDate = rs.getDate("MOD_DATE");
				
				MyMemberDto myMemberDto = 
						new MyMemberDto(mno, mname, email, pwd, creDate, modDate);
				myMemberList.add(myMemberDto);
				
			} // while end
			
			// request에 회원 목록 데이터를 보관한다
			req.setAttribute("myMemberList", myMemberList);
			
			// jsp로 출력을 위임한다
			RequestDispatcher dispatcher = 
					req.getRequestDispatcher("/myMember/myMemberListView.jsp");
			
			dispatcher.include(req, res);
			
		} catch (Exception e) {
			// 예외처리 페이지로 위임
			//e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher dispatcher = 
					req.getRequestDispatcher("/error.jsp");
			
			dispatcher.forward(req, res);
		}
		
		finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // if(rs != null) end
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} // finally end
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		
	}
}
