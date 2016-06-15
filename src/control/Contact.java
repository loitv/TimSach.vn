package control;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import function.MailSenderBean;

@WebServlet("/Contact")
public class Contact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Contact() {
        super();
    }
    
    @EJB
    private MailSenderBean mailSender;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		String name = request.getParameter("name");
		String toEmail = request.getParameter("email");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		
//		System.out.println(toEmail);
//		System.out.println(subject);
//		System.out.println(message);
		
		String fromEmail = "loitran1763@gmail.com";
		String userName = "loitran1763";
		String password = "klCF4693";
		
		mailSender.sendEmail(fromEmail, userName, password, toEmail, subject, message);
//		
		response.sendRedirect("contact.jsp");
//		System.out.println("Successful!!");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
