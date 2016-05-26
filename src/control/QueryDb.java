package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryDb {

	static Statement stmt = null;
	private XMLProcessing xml;
	private static ArrayList<String> bookInfos;
	private static ArrayList<String> libstatus;
	private static ArrayList<String> authors;
	private static String[] libstatuss;
	private static String[] bookInfoss;
	private static String[] authorss;

	public QueryDb() {
	}

	public QueryDb(String isbn) {
		bookInfos = new ArrayList<String>();
		String query = "select * from THONGTINSACH where isbn like '" + isbn + "';";
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				// System.out.println("Empty data!");
			} else {
				do {
					bookInfos.add(rs.getString("tenSach")); // 0
					bookInfos.add(rs.getString("linhvuc")); // 1
					bookInfos.add(rs.getString("nxb")); // 2
					bookInfos.add(rs.getString("namxb")); // 3
					bookInfos.add(rs.getString("giabia")); // 4
					bookInfos.add(rs.getString("sotrang")); // 5
					bookInfos.add(rs.getString("ngonngu")); // 6
				} while (rs.next());
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		xml = new XMLProcessing();
		xml.readXML();
		ArrayList<String> isbns = XMLProcessing.getISBNs();
		ArrayList<String> descs = XMLProcessing.getDescs();
		int i = 0;
		for (i = 0; i < isbns.size(); i++) {
			if (isbn.equals(isbns.get(i))) {
				break;
			}
		}
		String desc = descs.get(i);
		bookInfos.add(desc); // 7
		String imageUrl = "images/" + isbn + ".jpg";
		bookInfos.add(imageUrl); // 8

		// query libraries 1
		libstatus = new ArrayList<String>();
		queryLibs("sach", isbn);
		queryLibs("sachtv02", isbn);
		queryLibs("sachtv03", isbn);

		// query author
		String query1 = "select tacgia.firstName, tacgia.lastName from tacgia, tacgia_sach where tacgia_sach.isbn = '"
				+ isbn + "' and tacgia.IDAuthor=tacgia_sach.IDtacgia;";
		authors = new ArrayList<String>();
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query1);
			if (!rs.first()) {
				// System.out.println("Empty data!");
			} else {
				do {
					authors.add(rs.getString("firstName"));
					authors.add(rs.getString("lastName"));
				} while (rs.next());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		authorss = new String[authors.size()];
		for (int j = 0; j < authors.size(); j++) {
			authorss[j] = authors.get(j);
		}
		libstatuss = new String[libstatus.size()];
		for (int j = 0; j < libstatus.size(); j++) {
			libstatuss[j] = libstatus.get(j);
		}
		bookInfoss = new String[bookInfos.size()];
		for (int j = 0; j < bookInfos.size(); j++) {
			bookInfoss[j] = bookInfos.get(j);
		}
	}

	// query libraries
	public void queryLibs(String dbName, String isbn) {
		String status;
		String query = "select * from " + dbName + " where isbn like '" + isbn + "';";
		String libID = "", libName = "", libAdd = "";
		if (dbName.equalsIgnoreCase("sach")) {
			libID = "TV001";
			libName = "Thư viện Tạ Quang Bửu";
			libAdd = "1 Đại Cồ Việt, Hai Bà Trưng, Hà Nội";
		} else if (dbName.equalsIgnoreCase("sachtv02")) {
			libID = "TV002";
			libName = "Thư viện Quốc gia";
			libAdd = "31 Tràng Thi, Hàng Trống, Hoàn Kiếm, Hà Nội";
		} else if (dbName.equalsIgnoreCase("sachtv03")) {
			libID = "TV003";
			libName = "Thư viện Hà Nội";
			libAdd = "47 Bà Triệu, Hoàn Kiếm, Hà Nội";
		}
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				// System.out.println("Empty data!");
			} else {
				int remain = rs.getInt("sltrongkho");
				if (remain < 1) {
					status = "On Loan";
				} else {
					status = "Available";
				}
				libstatus.add(libID);
				libstatus.add(libName);
				libstatus.add(libAdd);
				libstatus.add(status);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static String[] getLibstatuss() {
		return libstatuss;
	}

	public static String[] getBookInfoss() {
		return bookInfoss;
	}

	public static String[] getAuthorss() {
		return authorss;
	}

	public static void main(String[] args) {

		// new QueryDb("978-1-4493-9321-2");
		ArrayList<String> list = new ArrayList<String>();
		String query = "select tacgia.firstName, tacgia.lastName from tacgia, tacgia_sach where tacgia_sach.isbn = '978-1-4493-9321-2' and tacgia.IDAuthor=tacgia_sach.IDtacgia;";
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				// System.out.println("Empty data!");
			} else {
				do {
					list.add(rs.getString("firstName"));
					list.add(rs.getString("lastName"));
				} while (rs.next());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		for (String l : list) {
			System.out.println(l);
		}
	}
}
