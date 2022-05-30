package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;


@WebServlet("/pbc") //주소창에 들어가는 이름 http://localhost:8088/phonebook2/"pbc"
public class PhoneController extends HttpServlet {

	//필드
	private static final long serialVersionUID = 1L;
	
	
    //생성자 (디폴트 사용)

	//메소드 gs

    //메소드 일반
	//get방식으로 요청시 호출 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//post 방식일때 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		
		
		//action 파라미터 꺼내기
		String action = request.getParameter("action");
		System.out.println(action);
		
		
		//사용자의 요청->list 일때 (리스트)
		if("list".equals(action)) {
			//데이터 가져오기
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> phoneList = phoneDao.getPersonList();
			
			//request에 데이터 추가
			request.setAttribute("pList", phoneList);
			
			//데이터 + html -> jsp 시킨다
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request,  response);
		}
		
		
		//사용자의 요청->writeForm 일때 (등록폼)
		else if("writeForm".equals(action)) {
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/writeForm.jsp");
			rd.forward(request, response);
		}
		
		
		//사용자의 요청->write 일때 (등록)
		else if("write".equals(action)) {
			//파라미터에서 값 꺼내기(name, hp, company)
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//vo 만들어서 값 초기화
			PersonVo personVo = new PersonVo(name, hp, company);
			
			//phoneDao.personInsert()를 통해 저장하기
			PhoneDao phoneDao = new PhoneDao();
			int count = phoneDao.personInsert(personVo);
			System.out.println(count);
			
			//리다이렉트 list
			response.sendRedirect("./pbc?action=list");
		}
		
		
		else if("update".equals(action)) {
			//파라미터에서 값 꺼내기(id, name, hp, company)
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//vo 만들어서 값 초기화
			PersonVo personVo = new PersonVo(id, name, hp, company);
			
			//phoneDao.personInsert()를 통해 저장하기
			PhoneDao phoneDao = new PhoneDao();
			int count = phoneDao.personUpdate(personVo);
			System.out.println(count);
			
			//리다이렉트 list
			response.sendRedirect("./pbc?action=list");
			
		}
		
		//사용자의 요청->delete 일때 (삭제)
		else if("delete".equals(action)) {
			//파라미터에서 값 꺼내기
			int id = Integer.parseInt(request.getParameter("id"));
			
			PhoneDao phoneDao = new PhoneDao();
			int count = phoneDao.personDelete(id);
			System.out.println(count);
			
			response.sendRedirect("./pbc?action=list");
			
			
		}
		
		
		else {
			System.out.println("action 파라미터 없음");
		}

	}

	//post방식으로 요청시 호출 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post로 실행");
		doGet(request, response);
	}

}
