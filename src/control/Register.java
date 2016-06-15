package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import function.DatabaseController;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Register() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		boolean ok = QueryDb.queryAcc(id);
		if (ok) {
			DatabaseController.insertAcc(id, pwd);
			out.println("<html><head><title>Register</title>"
					+ "<meta http-equiv=\"Refresh\" content=\"2;url=index.jsp\">"
					+ "</head><body>");
			out.println("<h3>Your account has been created successfull!</h3>"
					+ "<h4>You will be redirected to home page after 2 second</h4>");
			out.println("</body></html>");
		} else {
			out.println("<html><head><title>Login</title>"
					+ "<meta http-equiv=\"Refresh\" content=\"2;url=login.jsp\">"
					+ "</head><body>");
			out.println("<h3>This account has been existed!<br>Please choose another account!</h3>");
			out.println("</body></html>");
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
