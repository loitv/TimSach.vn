package control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> orderBooks;

	public Order() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String libID = request.getParameter("libID");
		String isbn = request.getParameter("isbn");
		String[] info = QueryDb.queryBook(isbn);
		String title = info[0];
		String price = info[1];

		HttpSession session = request.getSession();
		
		String[] date = (String[])session.getAttribute("date");
		if (date == null) {
			date = new String[2];
			session.setAttribute("date", date);
		}
		date = getDate();
		session.setAttribute("date", date);

		orderBooks = (List<String>) session.getAttribute("orderSession");
		if (orderBooks == null) {
			orderBooks = new ArrayList<String>();
			session.setAttribute("orderSession", orderBooks);
		}

		if (!(isbn == null)) {
			int count = 0;
			for (String item : orderBooks) {
				if (isbn.equals(item)) {
					count++;
				}
			}
			if (count == 0) {
				orderBooks.add(isbn);
				orderBooks.add(title);
				orderBooks.add(price);
				orderBooks.add(libID);
			}
		}
		session.setAttribute("orderSession", orderBooks);

		String[] orderArray = new String[orderBooks.size()];
		for (int j = 0; j < orderBooks.size(); j++) {
			orderArray[j] = orderBooks.get(j);
			// System.out.println(orderBooks.get(j));

		}
		request.setAttribute("orderArray", orderArray);
		request.setAttribute("date", date);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/order.jsp");
		dispatcher.forward(request, response);

	}
	
	public static String[] getDate() {
		String[] dateA = new String[2];
 		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		Calendar date = Calendar.getInstance();
		dateA[0] = ft.format(date.getTime());
		date.add(Calendar.MONTH, 2);
		dateA[1] = ft.format(date.getTime());
		return dateA;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
