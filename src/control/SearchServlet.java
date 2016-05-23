package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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
		// ").append(request.getContextPath());
		String search = request.getParameter("searchString");
		String input = "%".concat(search).concat("%");
		String query = String.format("select * from song where title like '%s' ORDER by title", input);
		QueryDb queryDb = new QueryDb(query);
		String[] results = queryDb.getResult();

		// Set the MIME type for the response message
		response.setContentType("text/html; charset = UTF-8");

		PrintWriter out = response.getWriter();

		out.println("<html><head><title>Query Results</title></head><body>");
		out.println("You search for: <em>" + search + "</em><hr />");
		if (results.length == 0) {
			out.println("<p>No result!</p>");
		} else {
			for (int i = 0; i < results.length; i++) {
				out.println("<p>" + results[i] + "</p>");
			}
		}
		out.println("</body></html>");

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
