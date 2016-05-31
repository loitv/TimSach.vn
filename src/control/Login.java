package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		boolean ok = QueryDb.queryAcc2(id, pwd);
		if (ok) {
			out.println("<html><head><title>Login</title></head><body>");
			out.println("<h3>Login successfull!</h3>");
			out.println("</body></html>");
			request.getSession().setAttribute("user", id);
			response.sendRedirect("index.jsp");
		} else {
			out.println("<html><head><title>Login</title></head><body>");
			out.println("<h3>Login false!</h3>");
			out.println("</body></html>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
