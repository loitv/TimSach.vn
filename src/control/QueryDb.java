package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryDb {

	static Statement stmt = null;
	private static ArrayList<String> dbTitle;
	private static ArrayList<String> dbISBN;

	private List<String> dbTempTitle;
	private int soLuong;
	// private static ArrayList<String> dbTempTitle;

	public QueryDb() {
		try {
			dbTempTitle = new ArrayList<String>();
			stmt = ConnectDb.getConnection().createStatement();
			String query1 = "select * from SACH;";
			ResultSet rs = stmt.executeQuery(query1);
			if (!rs.first()) {
				// System.out.println("Empty data!");
			} else {
				do {
					dbTempTitle.add(rs.getString("tenSach"));
				} while (rs.next());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		soLuong = dbTempTitle.size();
	}

	public QueryDb(String query) {
		try {
			dbTitle = new ArrayList<String>();
			dbISBN = new ArrayList<String>();
			stmt = ConnectDb.getConnection().createStatement();
			// String query = "select * from song;";
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				// System.out.println("Empty data!");
			} else {
				do {
					dbTitle.add(rs.getString("tenSach"));
					dbISBN.add(rs.getString("isbn"));
				} while (rs.next());
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public String[][] getInnfoBook() {
		String[][] infoBooks = new String[dbISBN.size()][6];

		for (int i = 0; i < dbISBN.size(); i++) {
			String query1 = "select * from thongtinsach where isbn like " + dbISBN.get(i);
			try {
				stmt = ConnectDb.getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(query1);
				if (!rs.first()) {
					// System.out.println("Empty data!");
				} else {
					do {
						infoBooks[i][0] = rs.getString("linhvuc");
						infoBooks[i][1] = rs.getString("nxb");
						infoBooks[i][2] = Integer.toString(rs.getInt("namXb"));
						infoBooks[i][3] = Double.toString(rs.getDouble("giaBia"));
						infoBooks[i][4] = Integer.toString(rs.getInt("taiBan"));
						infoBooks[i][5] = Integer.toString(rs.getInt("soTrang"));
					} while (rs.next());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return infoBooks;
	}

	public List<String> getData(String query) {

		String tenSach = null;
		query = query.toLowerCase();
		List<String> matched = new ArrayList<String>();
		for (int i = 0; i < soLuong; i++) {
			tenSach = dbTempTitle.get(i).toLowerCase();
			if (tenSach.startsWith(query)) {
				matched.add(dbTempTitle.get(i));
			}
		}
		return matched;
	}

	public static ArrayList<String> getDbTitle() {
		return dbTitle;
	}

	public static void setDbTitle(ArrayList<String> dbTitle) {
		QueryDb.dbTitle = dbTitle;
	}

	public String[] getResult() {
		String[] results = new String[dbTitle.size()];
		for (int i = 0; i < dbTitle.size(); i++) {
			results[i] = dbTitle.get(i);
		}
		return results;
	}
	
	public String[] getISBN() {
		String[] ISBNs = new String[dbISBN.size()];
		for (int i = 0; i < dbISBN.size(); i++) {
			ISBNs[i] = dbISBN.get(i);
		}
		return ISBNs;
	}

	public static void main(String[] args) {
//		new QueryDb("select * from sach where tenSach like 'tôi thấy hoa vàng trên cỏ xanh'");
//		System.out.println(dbTitle.get(0));
//		System.out.println(dbISBN.get(0));
//		String query = "select * from thongtinsach where isbn like " + dbISBN.get(0);
//		try {
//			stmt = ConnectDb.getConnection().createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			if (!rs.first()) {
//				// System.out.println("Empty data!");
//			} else {
//				do {
//					System.out.println(rs.getString("linhvuc"));
//					System.out.println(rs.getString("nxb"));
//					System.out.println(rs.getInt("namXb"));
//					System.out.println(rs.getDouble("giaBia"));
//					System.out.println(rs.getInt("taiBan"));
//					System.out.println(rs.getInt("soTrang"));
//				} while (rs.next());
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		String[][] demo = new String[2][3];
		demo[0][0] = "1";
		demo[0][1] = "2";
		demo[0][2] = "3";
		demo[1][0] = "4";
		demo[1][1] = "5";
		demo[1][2] = "6";
		
		for (int i = 0; i < 2; i++) {
			System.out.println("Luot: " + i);
			for (int j = 0; j < 3; j ++) {
				System.out.println(demo[i][j]);
			}
		}

	}
}
