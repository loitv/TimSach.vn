package function;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SavePersonalInfo")
public class SavePersonalInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SavePersonalInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String[] info = new String[7];
	info[0] = request.getParameter("id");
	info[1] = request.getParameter("name");
	info[2] = request.getParameter("gender");
	info[3] = request.getParameter("birthday");
	info[4] = request.getParameter("addr");
	info[5] = request.getParameter("phone");
	info[6] = request.getParameter("email");
	for (String a:info) {
		System.out.println(a);
	}
	DatabaseController.updatePerInfo(info);
	request.getSession().setAttribute("userInfo", info);
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
