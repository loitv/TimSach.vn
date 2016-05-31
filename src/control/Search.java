package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import function.UnAccent;

/**
 * Servlet implementation class SearchC
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<Integer> indexs;
	private ArrayList<String> results;
	private String search, type;
	private XMLProcessing xml;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath()) trần;
		request.setCharacterEncoding("UTF-8");
		search = request.getParameter("q");
		type = request.getParameter("type");
//		category = request.getParameter("category");

		xml = new XMLProcessing();
		xml.readXML();
		ArrayList<String> titles = XMLProcessing.getTitles();
		ArrayList<String> isbns = XMLProcessing.getISBNs();
		ArrayList<String> descs = XMLProcessing.getDescs();

		ArrayList<String> sTitles = new ArrayList<String>();
		ArrayList<String> sIsbns = new ArrayList<String>();
		ArrayList<String> sDescs = new ArrayList<String>();

		// search by isbn
		if (type.equals("isbn")) {
			search(isbns, search);
			sIsbns = results;
			for (int i = 0; i < indexs.size(); i++) {
				sTitles.add(titles.get(indexs.get(i)));
				sDescs.add(descs.get(indexs.get(i)));
			}

		}
		// search by title
		if (type.equals("title")) {
			search(titles, search);
			sTitles = results;
			for (int i = 0; i < indexs.size(); i++) {
				sIsbns.add(isbns.get(indexs.get(i)));
				sDescs.add(descs.get(indexs.get(i)));
			}
		}
		// search by author
		if (type.equals("author")) {
			ArrayList<String> ISBNs = new ArrayList<String>();
			ISBNs = QueryDb.queryAuthorName(search);
			sIsbns = ISBNs;
			for (int j = 0; j < ISBNs.size(); j++) {
				search(isbns, ISBNs.get(j));
				for (int i = 0; i < indexs.size(); i++) {
					sTitles.add(titles.get(indexs.get(i)));
					sDescs.add(descs.get(indexs.get(i)));
				}
			}
		}

		// search by category
		if (type.equals("category")){
			ArrayList<String> ISBNs = new ArrayList<String>();
			ISBNs = QueryDb.queryCate(search);
			sIsbns = ISBNs;
			for (int j = 0; j < ISBNs.size(); j++) {
				search(isbns, ISBNs.get(j));
				for (int i = 0; i < indexs.size(); i++) {
					sTitles.add(titles.get(indexs.get(i)));
					sDescs.add(descs.get(indexs.get(i)));
				}
			}
		}
		
		// process result and forward to search.jsp
		String[] isbns2 = new String[sTitles.size()];
		String[] titles2 = new String[sTitles.size()];
		String[] descs2 = new String[sTitles.size()];
		String[] images = new String[sTitles.size()];

		if (!sTitles.isEmpty()) {
			for (int i = 0; i < sTitles.size(); i++) {
				isbns2[i] = sIsbns.get(i);
				titles2[i] = sTitles.get(i);
				descs2[i] = sDescs.get(i);
				images[i] = "images/" + sIsbns.get(i) + ".jpg";
			}
		}
		request.setAttribute("isbn", isbns2);
		request.setAttribute("title", titles2);
		request.setAttribute("desc", descs2);
		request.setAttribute("image", images);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/search.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void search(ArrayList<String> array, String search) {
		UnAccent ua = new UnAccent(); // chuyen ky tu dac biet thanh ky tu
										// thuong
		results = new ArrayList<String>(); // chua ket qua tim kiem
		int count = 0;
		indexs = new ArrayList<Integer>(); //
		for (int i = 0; i < array.size(); i++) {
			if (ua.unAccent(array.get(i)).toLowerCase().contains(search.toLowerCase())) {
				count++;
				indexs.add(i);
			}
		}
		if (count > 0) {
			for (int i = 0; i < indexs.size(); i++) {
				results.add(array.get(indexs.get(i)));
			}
		} else {
		}
	}
}
