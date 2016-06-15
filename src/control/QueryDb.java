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

	// query by category and publisher
	public static ArrayList<String> queryCate(String type, String q) {
		ArrayList<String> ISBNs = new ArrayList<String>();
		q = q.replace("'", "\\'");
		String input = "'" + q + "'";
		switch (q) {
		case "vanhoc":
			input = "'van hoc'";
			break;
		case "khtn":
			input = "'Khoa hoc tu nhien'";
			break;
		case "it":
			input = "'Programming', 'Web Development', 'Operating Systems', 'Datebases'";
			break;

		case "web":
			input = "'Web Development'";
			break;
		case "os":
			input = "'Operating Systems'";
			break;
		case "database":
			input = "'Datebases'";
			break;
		case "et":
			input = "'digital design'";
			break;
		}

		String query = "select * from sach where " + type + " IN (" + input + ");";
//		System.out.println(query);
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
		ArrayList<String> accInfo = new ArrayList<String>();
		String query = "select * from thongtincanhan where id = '" + id + "';";
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				// ok = false;
			} else {
				accInfo.add(id);
				accInfo.add(rs.getString("name"));
				accInfo.add(rs.getString("gender"));
				accInfo.add(rs.getString("birthday"));
				accInfo.add(rs.getString("address"));
				accInfo.add(rs.getString("phone"));
				accInfo.add(rs.getString("email"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return accInfo;
	}

	public static String[] queryBook(String isbn) {
		String query = "select * from sach where isbn = '" + isbn + "';";
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
	
	public static String[][] queryCart(String userID) {
		String query1 = "select COUNT(*) from phieumuon where IDngdung = '" + userID + "';";
		String query2 = "select * from phieumuon where IDngdung = '" + userID + "';";
		int a= 0 ;
		String[][] cart = null;
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query1);
			if(!rs.first()) {
				///////
			} else {
				do {
					a = rs.getInt("count(*)");
				}while (rs.next());
				cart = new String[a][5];
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		if (cart != null) {
			try {
				stmt = ConnectDb.getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(query2);
				if(!rs.first()) {
					///////
				} else {
					int count = 0;
					do {
						cart[count][0] = rs.getString("IDphieumuon");
						String libraryID = rs.getString("IDthuvien");
						switch(libraryID) {
						case "TV001":
							cart[count][1] = "Thư viện Tạ Quang Bửu";
							break;
						case "TV002":
							cart[count][1] = "Thư viện Quốc gia";
							break;
						case "TV003":
							cart[count][1] = "Thư viện Hà Nội";
							break;
						}
						cart[count][2] = rs.getString("ngaymuon");
						cart[count][3] = rs.getString("ngaytra");
						cart[count][4] = rs.getString("tienung");
						count++;
					}while (rs.next());
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return cart;
	}
	
	public static String[][] queryBookCart(String cartID) {
		String query1 = "select COUNT(*) from phieumuon_sach where idphieumuon = '" + cartID + "';";
		String[][] bookCart = null;
		int a= 0 ;
		try {
			stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query1);
			if(!rs.first()) {
				/////////////
			} else {
				do {
					a = rs.getInt("count(*)");
				} while(rs.next());
				bookCart = new String[a][3];
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		if (bookCart != null) {
			try {
				String query2 = "select * from phieumuon_sach where idphieumuon = '" + cartID + "';";
				stmt = ConnectDb.getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(query2);
				if(!rs.first()) {
					///////
				} else {
					int count = 0;
					do {
						bookCart[count][0] = rs.getString("ISBN");
						bookCart[count][1] = rs.getString("tensach");
						bookCart[count][2] = rs.getString("giabia");
						count++;
					}while (rs.next());
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return bookCart;
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

		// queryAuthorName("luke");
		// queryAccInfo("loitv");
//		String a = "O'sds";
//		a = a.replace("'","\\'");
//		System.out.println(a);
		
//		String query1 = "select COUNT(*) from phieumuon where IDngdung = 'admin';";
//		int count = 0;
//		try {
//			stmt = ConnectDb.getConnection().createStatement();
//			ResultSet rs = stmt.executeQuery(query1);
//			if(!rs.first()) {
//				System.out.println("No result!");
//			} else {
//				do {
//					count = rs.getInt("count(*)");
//				}while(rs.next());
//			}
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//		System.out.println(count);
		
		String[][] cart = queryCart("admin");
		if (cart != null) {
			for (int i = 0; i < cart.length; i ++) {
				System.out.println(cart[i][0]);
				System.out.println(cart[i][1]);
				System.out.println(cart[i][2]);
				System.out.println(cart[i][3]);
				System.out.println(cart[i][4]);
				System.out.println("--------------------------");
//				for (int j = 0; j < )
				String[][] bookCart = queryBookCart(cart[i][0]);
				for (int j = 0; j < bookCart.length; j ++) {
					System.out.println(bookCart[j][0]);
					System.out.println(bookCart[j][1]);
					System.out.println(bookCart[j][2]);
					System.out.println("----------------");
				}
				System.out.println("/////////////////////////////");
			}
		}
	}
}
