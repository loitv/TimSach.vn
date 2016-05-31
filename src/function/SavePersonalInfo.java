package function;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SavePersonalInfo")
public class SavePersonalInfo extends HttpServlet {
	private ArrayList<String> info;
	private static final long serialVersionUID = 1L;
       
    public SavePersonalInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	info = new ArrayList<String>();
	info.add(request.getParameter("id"));
	info.add(request.getParameter("name"));
	info.add(request.getParameter("gender"));
	info.add(request.getParameter("birthday"));
	info.add(request.getParameter("addr"));
	info.add(request.getParameter("phone"));
	info.add(request.getParameter("email"));
	for (String a:info) {
		System.out.println(a);
	}
	DatabaseController.updatePerInfo(info);
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
