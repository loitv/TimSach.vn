package control;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import function.UnAccent;

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ArrayList<Integer> indexs;
	private static ArrayList<String> results;
	private static XMLProcessing xml;
	
	private static String[] isbns2;
	private static String[] titles2;
	private static String[] descs2;
	private static String[] images;

	public Search() {
		super();
	}

	public static void getResult(String type, String search) {
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
			ISBNs = QueryDb.queryCate("linhVuc", search);
			sIsbns = ISBNs;
			for (int j = 0; j < ISBNs.size(); j++) {
				search(isbns, ISBNs.get(j));
				for (int i = 0; i < indexs.size(); i++) {
					sTitles.add(titles.get(indexs.get(i)));
					sDescs.add(descs.get(indexs.get(i)));
				}
			}
		}
		
		// search by publisher
		if (type.equals("publisher")) {
			ArrayList<String> ISBNs = new ArrayList<String>();
			ISBNs = QueryDb.queryCate("nxb", search);
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
		isbns2 = new String[sTitles.size()];
		titles2 = new String[sTitles.size()];
		descs2 = new String[sTitles.size()];
		images = new String[sTitles.size()];

		if (!sTitles.isEmpty()) {
			for (int i = 0; i < sTitles.size(); i++) {
				isbns2[i] = sIsbns.get(i);
				titles2[i] = sTitles.get(i);
				descs2[i] = sDescs.get(i);
				images[i] = "images/" + sIsbns.get(i) + ".jpg";
			}
		}
	}

	public static void search(ArrayList<String> array, String search) {
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

	public static String[] getIsbns2() {
		return isbns2;
	}

	public static String[] getTitles2() {
		return titles2;
	}

	public static String[] getDescs2() {
		return descs2;
	}

	public static String[] getImages() {
		return images;
	}
	
	
}
