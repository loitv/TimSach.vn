package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Book
 */
@WebServlet("/Book")
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Book() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		String isbn = request.getParameter("isbn");
		new QueryDb(isbn);
		String[] bookInfo = QueryDb.getBookInfoss();
		String[] libStatus = QueryDb.getLibstatuss();
		String[] authors = QueryDb.getAuthorss();
		
		request.setAttribute("isbn", isbn);
		request.setAttribute("bookInfo", bookInfo);
		request.setAttribute("libStatus", libStatus);
		request.setAttribute("authors", authors);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/book.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
