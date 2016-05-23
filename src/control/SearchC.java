package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchC
 */
@WebServlet("/SearchC")
public class SearchC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchC() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath()) trần;
		request.setCharacterEncoding("UTF-8");
		String search = request.getParameter("searchString");
//		System.out.println(search);
		String input = "%".concat(search).concat("%");
		String query = String.format("select * from SACH where TENSACH like '%s' ORDER by tenSach", input);
		QueryDb queryDb = new QueryDb(query);
		String[] results = queryDb.getResult();
		String[][] info = queryDb.getInnfoBook();
		String[] isbn = queryDb.getISBN();

		request.setAttribute("result", results);
		request.setAttribute("Info", info);
		request.setAttribute("ISBN", isbn);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SearchV.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
