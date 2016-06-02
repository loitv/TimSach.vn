package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RemoveItem")
public class RemoveItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveItem() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		if (!(isbn==null)) {
			List<String> orderBooks = (List<String>) request.getSession().getAttribute("orderSession");
			int i = 0;
			for (i  = 0; i < orderBooks.size(); i ++) {
				if (isbn.equals(orderBooks.get(i))) {
					break;
				}
			}
			for (int j = 0 ; j < 4; j ++) {
				orderBooks.remove(i);
			}
			request.getSession().setAttribute("orderSession", orderBooks);
		}
		response.sendRedirect("Order");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i < 5; i ++) {
			numbers.add(i + 1);
		}
		
		for (int i = 0; i < 3; i ++) {
			numbers.remove(1);
		}
		for (int i = 0; i < numbers.size(); i ++) {
			System.out.println(numbers.get(i));
		}
	}

}
