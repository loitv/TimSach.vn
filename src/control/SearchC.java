package control;

import java.io.IOException;
import java.util.ArrayList;

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

	private ArrayList<Integer> indexs;
	private ArrayList<String> results;
	private String search, searchIn;
	private XMLProcessing xml;

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
		search = request.getParameter("q");
		searchIn = request.getParameter("searchIn");
		
		xml = new XMLProcessing();
		xml.readXML();
		ArrayList<String> titles = XMLProcessing.getTitles();
		ArrayList<String> isbns = XMLProcessing.getISBNs();
		ArrayList<String> descs = XMLProcessing.getDescs();
		
		ArrayList<String> sTitles = new ArrayList<String>();
		ArrayList<String> sIsbns = new ArrayList<String>();
		ArrayList<String> sDescs = new ArrayList<String>();
		
		if (searchIn.equals("isbn")) {
			search(isbns, search);
			sIsbns = results;
			for (int i = 0; i < indexs.size(); i ++) {
				sTitles.add(titles.get(indexs.get(i)));
				sDescs.add(descs.get(indexs.get(i)));
			}
			
		}
		if (searchIn.equals("title")) {
			search(titles, search);
			sTitles = results;
			for (int i = 0; i < indexs.size(); i ++) {
				sIsbns.add(isbns.get(indexs.get(i)));
				sDescs.add(descs.get(indexs.get(i)));
			}
		}
		
		String[] isbns2 = new String[sTitles.size()];
		String[] titles2 = new String[sTitles.size()];
		String[] descs2 = new String[sTitles.size()];
		String[] images = new String[sTitles.size()];
		
		if (!sTitles.isEmpty()) {
			for (int i = 0; i < sTitles.size(); i ++) {
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
	
	public void search(ArrayList<String> array, String search) {
		results = new ArrayList<String>();
		int count = 0;
		indexs = new ArrayList<Integer>();
		for (int i = 0; i < array.size(); i ++) {
			if(array.get(i).toLowerCase().contains(search.toLowerCase())) {
				count++;
				indexs.add(i);
			}
		}
		if (count > 0) {
			for(int i = 0; i < indexs.size(); i ++) {
				results.add(array.get(indexs.get(i)));
			}
		} else {
		}
	}
//	public static void main(String[] args) {
//		XMLProcessing xml = new XMLProcessing();
//		xml.readXML();
//		ArrayList<String> titles = XMLProcessing.getTitles();
//		ArrayList<Integer> indexs = new ArrayList<Integer>();
//		String search = "head";
//		int count = 0;
//		for (int i = 0; i < titles.size(); i ++) {
//			if(titles.get(i).toLowerCase().contains(search.toLowerCase())) {
//				count++;
//				indexs.add(i);
//			}
//		}
//		if (count > 0) {
//			System.out.println("FOUND!");
//			for(int i = 0; i < indexs.size(); i ++) {
//				System.out.println(titles.get(indexs.get(i)));
//			}
//		} else {
//			System.out.println("NOT FOUND!");
//		}
//	}

}
