package studentwithjspa22.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentwithjspa22.dao.StudentDao;
import studentwithjspa22.dto.Student;
@WebServlet("/login")
public class LoginServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	
	StudentDao dao=new StudentDao();
	List<Student> students=dao.getAllStudents();
	boolean value=false;
	String studentPassword=null;
	for(Student student:students) {
		if(email.equals(student.getEmail())) {
			value=true;
			studentPassword=student.getPassword();
			break;
		}
	}
	
	if(value) {
//		email is present
		if(password.equals(studentPassword)) {
//			login success
			RequestDispatcher dispatcher=req.getRequestDispatcher("display.jsp");
			dispatcher.forward(req, resp);
		}else {
//			invalid password
			req.setAttribute("message", "Invalid Password");
			RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
			dispatcher.include(req, resp);
		}
	}else {
		req.setAttribute("message", "Invalid Email");
		RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
		dispatcher.include(req, resp);
	}
	
	
	
	
	
	
	
}
}
