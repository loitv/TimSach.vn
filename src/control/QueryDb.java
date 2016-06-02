package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryDb {

	static Statement stmt = null;
	private XMLProcessing xml;
	private static ArrayList<String> bookInfos;
	private static ArrayList<String> libInfos; // luu thong tin thu vien theo
												// cac ID
	private static ArrayList<String> libIDs; // luu ID cac thu vien co sach can
												// tim
	private static ArrayList<String> libstatus;
	private static ArrayList<String> authors;
	private static String[] libstatuss;
	// private static String[] libInfoss;
	private static String[] bookInfoss;
	private static String[] authorss;

	public QueryDb() {
	}

	public QueryDb(String isbn) {
		bookInfos = new ArrayList<String>();
		String query = "select * from SACH where isbn like '" + isbn + "';";
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				// System.out.println("Empty data!");
			} else {
				bookInfos.add(rs.getString("tenSach")); // 0
				bookInfos.add(rs.getString("linhvuc")); // 1
				bookInfos.add(rs.getString("nxb")); // 2
				bookInfos.add(rs.getString("namxb")); // 3
				bookInfos.add(rs.getString("giabia")); // 4
				bookInfos.add(rs.getString("sotrang")); // 5
				bookInfos.add(rs.getString("ngonngu")); // 6
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

		// query libraries
		queryLibBook(isbn);

		// query author
		queryAuthor(isbn);

		authorss = new String[authors.size()];
		for (int j = 0; j < authors.size(); j++) {
			authorss[j] = authors.get(j);
		}
		libstatuss = new String[libstatus.size() + libInfos.size()];
		int k = 0;
		for (int j = 0; j < (libstatus.size() + libInfos.size()); j += 5) {
			libstatuss[j + k] = libInfos.get(j);
			libstatuss[1 + j + k] = libInfos.get(1 + j);
			libstatuss[2 + j + k] = libInfos.get(2 + j);
			libstatuss[3 + j + k] = libInfos.get(3 + j);
			libstatuss[4 + j + k] = libInfos.get(4 + j);
			libstatuss[5 + j + k] = libstatus.get(k);
			k++;
			if (k == libstatus.size()) {
				break;
			}
		}

		bookInfoss = new String[bookInfos.size()];
		for (int j = 0; j < bookInfos.size(); j++) {
			bookInfoss[j] = bookInfos.get(j);
		}
	}

	// query libraries
	public void queryLibBook(String isbn) {
		String status;
		String query = "select * from THUVIEN_SACH where isbn like '" + isbn + "';";
		libIDs = new ArrayList<String>();
		libstatus = new ArrayList<String>();
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				// System.out.println("Empty data!");
			} else {
				do {
					libIDs.add(rs.getString("IDThuvien"));
					int inStock = rs.getInt("sltrongkho");
					if (inStock < 1) {
						status = "On Loan";
					} else {
						status = "Available";
					}
					libstatus.add(status);
				} while (rs.next());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		libInfos = new ArrayList<String>();
		for (String libID : libIDs) {
			queryLibInfo(libID);
		}
	}

	public void queryLibInfo(String libID) {
		String query = "select * from THUVIEN where IDThuVien like '" + libID + "';";
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				// System.out.println("Empty data!");
			} else {
				libInfos.add(libID);
				libInfos.add(rs.getString("tenThuvien"));
				libInfos.add(rs.getString("diachi"));
				libInfos.add(rs.getString("SDT"));
				libInfos.add(rs.getString("email"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// query author depend on isbn
	public void queryAuthor(String isbn) {
		String query1 = "select tacgia.firstName, tacgia.lastName from tacgia, tacgia_sach where tacgia_sach.isbn = '"
				+ isbn + "' and tacgia.IDTacgia=tacgia_sach.IDtacgia;";
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
	}

	public static ArrayList<String> queryAuthorName(String search) {
		ArrayList<String> ISBNs = new ArrayList<String>();
		String input = "%".concat(search).concat("%");
		String query = String.format(
				"select tacgia_sach.isbn from tacgia_sach, tacgia where (tacgia.lastName like '%s' or tacgia.firstName like '%s' or tacgia.fullName like '%s') and tacgia_sach.idtacgia = tacgia.idtacgia",
				input, input, input);
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				// System.out.println("Empty data!");
			} else {
				do {
					ISBNs.add(rs.getString("isbn"));
				} while (rs.next());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return ISBNs;
	}

	// query category
	public static ArrayList<String> queryCate(String cate) {
		ArrayList<String> ISBNs = new ArrayList<String>();
		String input = cate;
		if (cate.equals("vanhoc")) {
			input = "Van hoc";
		}
		if (cate.equals("khth")) {
			input = "Khoa hoc tu nhien";
		}
		String query = "select * from sach where linhVuc like '" + input + "';";
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				// System.out.println("Empty data!");
			} else {
				do {
					ISBNs.add(rs.getString("isbn"));
				} while (rs.next());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return ISBNs;
	}

	// query account for register
	public static boolean queryAcc(String id) {
		String query = "select * from taikhoan where id = '" + id + "';";
		boolean ok = false;
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				ok = true;
			} else {
				ok = false;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return ok;
	}
	
	// query account for login
	public static boolean queryAcc2(String id, String pwd) {
		String query = "select * from taikhoan where id = '" + id + "';";
		boolean ok = false;
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				ok = false;
			} else {
				String password = rs.getString("password");
				if (pwd.equals(password)) {
					ok = true;
				} else {
					ok = false;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return ok;
	}
	
	// query account for getInformation
	public static ArrayList<String> queryAccInfo(String id) {
		ArrayList<String> accInfo= new ArrayList<String>();
		String query = "select * from thongtincanhan where id = '" + id + "';";
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
//				ok = false;
			} else {
				accInfo.add(id);
				accInfo.add(rs.getString("name"));
				accInfo.add(rs.getString("gender"));
				accInfo.add(rs.getString("birthday"));
				accInfo.add(rs.getString("address"));
				accInfo.add(rs.getString("phone"));
				accInfo.add(rs.getString("email"));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return accInfo;
	}
	
	public static String[] queryBook(String isbn) {
		String query = "select * from sach where isbn = '" + isbn +"';";
		String[] info = new String[2];
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				
			} else {
				info[0] = rs.getString("tenSach");
				info[1] = Double.toString(rs.getDouble("giabia"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return info;
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
		// new QueryDb("978-1-119-07085-6");
		// for (int i = 0; i < libstatuss.length; i++) {
		// System.out.println(libstatuss[i]);
		// }
		// for (int i = 0; i < authorss.length; i++) {
		// System.out.println(authorss[i]);
		// }
		// for (int i = 0; i < bookInfoss.length; i++) {
		// System.out.println(bookInfoss[i]);
		// }

//		queryAuthorName("luke");
//		queryAccInfo("loitv");
	}
}
