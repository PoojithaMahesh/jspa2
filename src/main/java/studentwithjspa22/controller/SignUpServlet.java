package studentwithjspa22.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentwithjspa22.dao.StudentDao;
import studentwithjspa22.dto.Student;

public class SignUpServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name=req.getParameter("name");
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	String address=req.getParameter("address");
	long phone=Long.parseLong(req.getParameter("phone"));
	
	ServletContext context=getServletContext();
	String fees1=context.getInitParameter("fees");
	
	double fees=Double.parseDouble(fees1);
	
	
//	i need to check whether this email is already present
	
	StudentDao dao=new StudentDao();
	boolean value=true;
	List<Student> students=dao.getAllStudents();
	
	for(Student student:students) {
		if(email.equals(student.getEmail())) {
			value=false;
			break;
		}
	}
	
	Student student=new Student();
	student.setAddress(address);
	student.setEmail(email);
	student.setFees(fees);
	student.setName(name);
	student.setPassword(password);
	student.setPhone(phone);
	
//	value=true; when that email is not present in the database
//	value=false; when that email is already present in the database
	
	if(value) {
		dao.saveStudent(student);
		req.setAttribute("message", "SignedUpSuccessfullyPleaseLogin");
		RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
		dispatcher.forward(req, resp);
	}else {
		req.setAttribute("message", "Sorry Email already exist!!!!");
		RequestDispatcher dispatcher=req.getRequestDispatcher("signup.jsp");
		dispatcher.include(req, resp);		
	}
	
}
}
