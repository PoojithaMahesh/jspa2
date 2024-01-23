package studentwithjspa22.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import studentwithjspa22.dao.StudentDao;
import studentwithjspa22.dto.Student;
@WebServlet("/update")
public class UpdateServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id=Integer.parseInt(req.getParameter("id"));
	StudentDao dao=new StudentDao();
	Student student=dao.getStudentById(id);
	
	HttpSession httpSession=req.getSession();
	
	String name=(String) httpSession.getAttribute("studentwhologgedIn");
	
	
	if(name!=null) {
//		he is not a scammer he is coming from the login page
		req.setAttribute("student", student);
		RequestDispatcher dispatcher=req.getRequestDispatcher("edit.jsp");
		dispatcher.forward(req, resp);
	}else {
//		he is a scammer
		req.setAttribute("message", "Hey Scammer please login first");
		RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
		dispatcher.include(req, resp);
	}
	
	
	
	
	
}
}
