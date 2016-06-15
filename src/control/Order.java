package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import function.DatabaseController;

@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Order() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String confirm = request.getParameter("s");
		List<String> orderBooks = (List<String>) request.getSession().getAttribute("orderSession");
//		response.setContentType("text/jsp");
//		PrintWriter out = response.getWriter();
		if (confirm == null || orderBooks.size() == 0) {
			response.sendRedirect("Cart");
		} else {
//				List<String> orderBooks = (List<String>) request.getSession().getAttribute("orderSession");
				String[] date = (String[]) request.getSession().getAttribute("date");
				String userID = (String) request.getSession().getAttribute("user");
				String libID = orderBooks.get(3);
				Double deposit = (double) 0;
				
				for (int i = 0; i < orderBooks.size(); i+=4) {
					deposit += Double.parseDouble(orderBooks.get(i + 2));
				}
				String[] orderArray = new String[5];
				orderArray[0] = userID;
				orderArray[1] = libID;
				orderArray[2] = date[0];
				orderArray[3] = date[1];
				orderArray[4] = Double.toString(deposit*0.5);

				DatabaseController.updatePhieu(orderArray, orderBooks);
				String IDPM = DatabaseController.getIDPM();
//				out.println("<html><head><title>Login</title></head><body>");
//				out.println("<%@ include file=\"header.jsp\" %><br><br>");
//				out.println("<h3>Your order has been sent successfully!</h3>");
//				out.println("</body></html>");
				String[] orderBookArray = new String[orderBooks.size()];
				for(int i = 0; i < orderBooks.size(); i ++) {
					orderBookArray[i] = orderBooks.get(i);
				}
				request.getSession().removeAttribute("orderSession");
				
				request.setAttribute("orderArray", orderArray);
				request.setAttribute("IDPM", IDPM);
				request.setAttribute("orderBookArray", orderBookArray);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/order.jsp");
				dispatcher.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
