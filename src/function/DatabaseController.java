package function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import control.ConnectDb;
import control.XMLProcessing;
import view.DatabaseView;

public class DatabaseController {

	DatabaseView dbv;
	List<String> list;
	private XMLProcessing xml;
	private String AuID2;
	private static String IDPM;
	private ArrayList<String> ISBNs;
	private ArrayList<String> AuthorIDs;

	public DatabaseController() {
		dbv = new DatabaseView();
		////////////////////////////////////////
		//Handle event for button 'Parse HTML'//
		////////////////////////////////////////
		dbv.setButtonParseActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dbv.getTfAuthor2().setText("");
				dbv.getTfAuFirstName2().setText("");
				dbv.getTfAuLastName2().setText("");
				String url = dbv.getTfURL().getText();
				if (url.equals("")) {

				} else {
					Document document;
					list = new ArrayList<String>();
					try {
						document = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
						Elements tags = document.getElementsByAttribute("itemprop");
						for (Element a : tags) {
							list.add(a.text());
						}
						// gán thông tin cho các trường
						dbv.getTfTitle1().setText(list.get(0));
						dbv.getTfTitle2().setText(list.get(0));
						dbv.getTfDesc().setText(list.get(2));
						dbv.getTfPub().setText(list.get(3));
						dbv.getTfISBN1().setText(list.get(5));
						dbv.getTfISBN2().setText(list.get(5));
						dbv.getTfISBN3().setText(list.get(5));
						dbv.getTfISBN4().setText(list.get(5));
						dbv.getTfYear().setText(list.get(6));
						dbv.getTfPage().setText(list.get(7));
						dbv.getTfLang().setText(list.get(8));

						String author = list.get(4);
						ArrayList<String> authors = splitString(author, ",");
						if (authors.size() == 1) {
							dbv.getTfAuthor().setText(author);
							ArrayList<String> names = splitString(author, " ");
							dbv.getTfAuFirstName().setText(names.get(0));
							if (names.size() > 1) {
								dbv.getTfAuLastName().setText(names.get(1));
							} else {
								dbv.getTfAuLastName().setText("");
							}
							String AuID = generateIDAuthor(names.get(0), names.get(1));
							dbv.getTfAuID1().setText(AuID);
							dbv.getTfAuID2().setText(AuID);
						}
						if (authors.size() > 1) {
							dbv.getTfAuthor().setText(authors.get(0));
							dbv.getTfAuthor2().setText(authors.get(1));
							ArrayList<String> names1 = splitString(authors.get(0), " ");
							dbv.getTfAuFirstName().setText(names1.get(0));
							if (names1.size() > 1) {
								dbv.getTfAuLastName().setText(names1.get(1));
							} else {
								dbv.getTfAuLastName().setText("");
							}
							String AuID = generateIDAuthor(names1.get(0), names1.get(1));
							dbv.getTfAuID1().setText(AuID);
							dbv.getTfAuID2().setText(AuID);

							ArrayList<String> names2 = splitString(authors.get(1), " ");
							dbv.getTfAuFirstName2().setText(names2.get(0));
							if (names2.size() > 1) {
								dbv.getTfAuLastName2().setText(names2.get(1));
							} else {
								dbv.getTfAuLastName2().setText("");
							}
							generateIDAuthor(names2.get(0), names2.get(1));
							dbv.getTfAu2ID1().setText(AuID2);
							dbv.getTfAu2ID2().setText(AuID2);
						}

						// Get link image cover
						dbv.getTfImage().setText(tags.get(1).absUrl("src"));

						// Set random number for these fields: Tong so, SL trong
						// kho, SL cho muon, gia bia.
						int total = new Random().nextInt(10) + 1;
						int remain = 0;
						do {
							remain = new Random().nextInt(20) + 1;
						} while ((total - remain) > 5 | (total - remain) < 0);
						int onLoan = total - remain;
						double price = (new Random().nextInt(450) + 51) * 1000;

						dbv.getTfTotal().setText(Integer.toString(total));
						dbv.getTfRemain().setText(Integer.toString(remain));
						dbv.getTfOnLoan().setText(Integer.toString(onLoan));
						dbv.getTfPrice().setText(Double.toString(price));

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		//////////////////////////////////////////////
		// Handle event for button 'ADD NEW BOOK'//
		//////////////////////////////////////////////
		dbv.setButtonUpdateSachActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ISBNs = new ArrayList<String>();
				try {
					Statement stmt = ConnectDb.getConnection().createStatement();
					String query = "select ISBN from sach;";
					ResultSet rs = stmt.executeQuery(query);
					if (!rs.first()) {
						System.out.println("Empty data!");
					} else {
						do {
							ISBNs.add(rs.getString("ISBN"));
						} while (rs.next());
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				
				//~~~~~~~~~~update table 'SACH'~~~~~~~~~~~//
				String isbn = dbv.getTfISBN2().getText();
				int count = 0;
				for (int i = 0; i < ISBNs.size(); i++) {
					if (isbn.equalsIgnoreCase(ISBNs.get(i))) {
						count++;
					}
				}
				if (count > 0) {
					System.out.println("Sach da co trong Database");
				} else { // them sach moi vao trong database
					String query = "INSERT into SACH values (?,?,?,?,?,?,?,?);";
					try (PreparedStatement insertStmt = (PreparedStatement) ConnectDb.getConnection()
							.prepareStatement(query)) {
						insertStmt.setString(1, isbn);
						insertStmt.setString(2, dbv.getTfTitle1().getText());
						insertStmt.setString(3, dbv.getTfCate().getText());
						insertStmt.setString(4, dbv.getTfPub().getText());
						insertStmt.setInt(5, Integer.parseInt(dbv.getTfYear().getText()));
						insertStmt.setDouble(6, Double.parseDouble(dbv.getTfPrice().getText()));
						insertStmt.setInt(7, Integer.parseInt(dbv.getTfPage().getText()));
						insertStmt.setString(8, dbv.getTfLang().getText());
						insertStmt.executeUpdate();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					
					//~~~~~~~~~~Update table 'THUVIEN_SACH'~~~~~~~~~~~//
					String libID = "TV001";
					if (dbv.getLib2().isSelected()) {
						libID = "TV002";
					} else if (dbv.getLib3().isSelected()) {
						libID = "TV003";
					}
					int total = Integer.parseInt(dbv.getTfTotal().getText());
					int inStock = Integer.parseInt(dbv.getTfRemain().getText());
					int onLoan = Integer.parseInt(dbv.getTfOnLoan().getText());

					String query2 = "INSERT into THUVIEN_SACH values (?,?,?,?,?);";
					try (PreparedStatement insertStmt = (PreparedStatement) ConnectDb.getConnection().prepareStatement(query2)) {
						insertStmt.setString(1, libID);
						insertStmt.setString(2, isbn);
						insertStmt.setInt(3, total);
						insertStmt.setInt(4, inStock);
						insertStmt.setInt(5, onLoan);
						insertStmt.executeUpdate();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					
					//~~~~~~~~~~~~~Update table 'TACGIA' and 'TACGIA_SACH'~~~~~~~~~~~~//
					// Get list of author has been existed in DB
					AuthorIDs = new ArrayList<String>();
					try {
						Statement stmt = ConnectDb.getConnection().createStatement();
						String query3 = "select * from tacgia;";
						ResultSet rs = stmt.executeQuery(query3);
						if (!rs.first()) {

						} else {
							do {
								AuthorIDs.add(rs.getString("IDTacgia"));
							} while (rs.next());
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					// insert author 1
					String AuID1 = dbv.getTfAuID1().getText();
					String firstName1 = dbv.getTfAuFirstName().getText();
					String lastName1 = dbv.getTfAuLastName().getText();
					String fullName1 = dbv.getTfAuthor().getText();
					int count2 = 0;
					for (int i = 0; i < AuthorIDs.size(); i++) {
						if (AuID1.equalsIgnoreCase(AuthorIDs.get(i))) {
							count2++;
						}
					}
					if (count2 > 0) {
//						System.out.println("Author 1 has been existed in Database");
					} else {
						String query4 = "INSERT into TACGIA values (?,?,?,?,?);";
						try (PreparedStatement insertStmt = (PreparedStatement) ConnectDb.getConnection()
								.prepareStatement(query4)) {
							insertStmt.setString(1, AuID1);
							insertStmt.setString(2, lastName1);
							insertStmt.setString(3, firstName1);
							insertStmt.setString(4, fullName1);
							insertStmt.setString(5, firstName1.toLowerCase() + lastName1.toLowerCase() + "@xmail.com");
							insertStmt.executeUpdate();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						
						String query4a = "INSERT into tacgia_sach values (?,?);";
						try (PreparedStatement insertStmt = (PreparedStatement) ConnectDb.getConnection()
								.prepareStatement(query4a)) {
							insertStmt.setString(1, AuID1);
							insertStmt.setString(2, isbn);
							insertStmt.executeUpdate();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
					// insert author2
					String author2 = dbv.getTfAuthor2().getText();
					if (author2.length() > 0) {
						String AuID2 = dbv.getTfAu2ID1().getText();
						String firstName2 = dbv.getTfAuFirstName2().getText();
						String lastName2 = dbv.getTfAuLastName2().getText();
						int count3 = 0;
						for (int i = 0; i < AuthorIDs.size(); i++) {
							if (AuID2.equalsIgnoreCase(AuthorIDs.get(i))) {
								count3++;
							}
						}
						if (count3 > 0) {
							System.out.println("Author 2 has been existed in Database");
						} else {
							String query5 = "INSERT into TACGIA values (?,?,?,?,?);";
							try (PreparedStatement insertStmt = (PreparedStatement) ConnectDb.getConnection()
									.prepareStatement(query5)) {
								insertStmt.setString(1, AuID2);
								insertStmt.setString(2, lastName2);
								insertStmt.setString(3, firstName2);
								insertStmt.setString(4, author2);
								insertStmt.setString(5, firstName2.toLowerCase() + lastName2.toLowerCase() + "@xmail.com");
								insertStmt.executeUpdate();
							} catch (SQLException ex) {
								ex.printStackTrace();
							}
							
							String query5a = "INSERT into tacgia_sach values (?,?);";
							try (PreparedStatement insertStmt = (PreparedStatement) ConnectDb.getConnection()
									.prepareStatement(query5a)) {
								insertStmt.setString(1, AuID2);
								insertStmt.setString(2, isbn);
								insertStmt.executeUpdate();
							} catch (SQLException ex) {
								ex.printStackTrace();
							}
						}
					}
					/////////////////////////////////////////////////////////////////////////////////////////////////////////
				}
			}
		});
		
		////////////////////////////////////////
		//handle event for button 'update XML'//
		////////////////////////////////////////
		dbv.setButtonUpdateXMLActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String isbn = dbv.getTfISBN4().getText();
				xml = new XMLProcessing();
				xml.readXML();
				ArrayList<String> ISBNxml = new ArrayList<String>();
				ISBNxml = XMLProcessing.getISBNs();
				int count = 0;
				for (int i = 0; i < ISBNxml.size(); i++) {
					if (isbn.equalsIgnoreCase(ISBNxml.get(i))) {
						count++;
					}
				}
				if (count > 0) {
					System.out.println("This book has been saved before!");
				} else {
					xml.writeXML(isbn, dbv.getTfTitle2().getText(), dbv.getTfDesc().getText());
				}
			}
		});
		
		///////////////////////////////////////
		// handle event for button save Image//
		///////////////////////////////////////
		dbv.setButtonGetImageActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String isbn = dbv.getTfISBN4().getText();
				String url = dbv.getTfImage().getText();
				String dest = "WebContent/images/" + isbn + ".jpg";
				try {
					SaveImageFromURL.saveImage(url, dest);
					JOptionPane.showMessageDialog(null, "Success!");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	


	public String generateIDAuthor(String firstName1, String lastName1) {
		String AuID = "";
		ArrayList<String> firstNames = new ArrayList<String>();
		ArrayList<String> lastNames = new ArrayList<String>();
		ArrayList<String> IDAuthors = new ArrayList<String>();
		try {
			Statement stmt = ConnectDb.getConnection().createStatement();
			String query = "select * from tacgia;";
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				System.out.println("Empty data!");
			} else {
				do {
					firstNames.add(rs.getString("firstName"));
					lastNames.add(rs.getString("lastName"));
					IDAuthors.add(rs.getString("IDTacgia"));
				} while (rs.next());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		int count = 0, i = 0;
		for (i = 0; i < firstNames.size(); i++) {
			if (firstName1.equalsIgnoreCase(firstNames.get(i)) & lastName1.equals(lastNames.get(i))) {
				count++;
				break;
			}
		}
		if (count > 0) {
			AuID = IDAuthors.get(i);
			AuID2 = IDAuthors.get(i);
		} else {
			int number = IDAuthors.size();
			String str1, str2 = Integer.toString(number + 1);
			String str1a, str2a = Integer.toString(number + 2);
			if (number < 8) {
				str1a = "Au00";
			} else if (number >= 8 & number < 98) {
				str1a = "Au0";
			} else {
				str1a = "Au";
			}
			AuID2 = str1a + str2a;
			if (number < 9) {
				str1 = "Au00";
			} else if (number >= 9 & number < 99) {
				str1 = "Au0";
			} else {
				str1 = "Au";
			}
			AuID = str1 + str2;
		}

		return AuID;
	}


	public ArrayList<String> splitString(String str1, String str2) {
		StringTokenizer st = new StringTokenizer(str1, str2);
		ArrayList<String> authors = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			authors.add(st.nextToken().trim());
		}
		return authors;
	}
	
	// Them tai khoan moi vao database
	public static void insertAcc(String id, String pwd) {
		String query = "INSERT into taikhoan values (?,?,?);";
		try (PreparedStatement insertStmt = (PreparedStatement) ConnectDb.getConnection()
				.prepareStatement(query)) {
			insertStmt.setString(1, id);
			insertStmt.setString(2, pwd);
			insertStmt.setInt(3, 2);
			insertStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		String query1 = "INSERT into thongtincanhan values (?,?,?,?,?,?,?);";
		try (PreparedStatement insertStmt = (PreparedStatement) ConnectDb.getConnection()
				.prepareStatement(query1)) {
			insertStmt.setString(1, id);
			insertStmt.setString(2, null);
			insertStmt.setString(3, null);
			insertStmt.setString(4, null);
			insertStmt.setString(5, null);
			insertStmt.setString(6, null);
			insertStmt.setString(7, null);
			insertStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	// upadate personal information
	public static void updatePerInfo(String[] personalInfo) {
		String query = "UPDATE thongtincanhan SET name=? ,gender=?, birthday=?, address=?, phone=?, email=? WHERE id=?";
		try (PreparedStatement addStmt = (PreparedStatement) ConnectDb.getConnection()
				.prepareStatement(query)) {

			addStmt.setString(1, personalInfo[1]);
			addStmt.setString(2, personalInfo[2]);
			addStmt.setString(3, personalInfo[3]);
			addStmt.setString(4, personalInfo[4]);
			addStmt.setString(5, personalInfo[5]);
			addStmt.setString(6, personalInfo[6]);
			addStmt.setString(7, personalInfo[0]);
			addStmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	// them phieu muon sach moi
	public static void updatePhieu(String[] pm, List<String> orderBooks) {
		String PMID = generatePMID();
		String query = "INSERT into phieumuon values (?,?,?,?,?,?);";
		try (PreparedStatement insertStmt = (PreparedStatement) ConnectDb.getConnection()
				.prepareStatement(query)) {
			insertStmt.setString(1, PMID);
			insertStmt.setString(2, pm[0]);
			insertStmt.setString(3, pm[1]);
			insertStmt.setString(4, pm[2]);
			insertStmt.setString(5, pm[3]);
			insertStmt.setDouble(6, Double.parseDouble(pm[4]));
			insertStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		String query1 = "INSERT into phieumuon_sach values (?,?,?,?);";
		for (int i = 0; i < orderBooks.size(); i+=4) {
			try (PreparedStatement insertStmt = (PreparedStatement) ConnectDb.getConnection()
					.prepareStatement(query1)) {
				insertStmt.setString(1, PMID);
				insertStmt.setString(2, orderBooks.get(i));
				insertStmt.setString(3, orderBooks.get(i+1));
				insertStmt.setDouble(4, Double.parseDouble(orderBooks.get(i+2)));
				
				insertStmt.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
			String query2 = "UPDATE thuvien_sach SET slTrongKho = slTrongKho - 1, slChoMuon = slChoMuon + 1 WHERE IDThuvien = '" + orderBooks.get(3) + "' AND ISBN = '" + orderBooks.get(i) + "';";
			try {
				Statement stmt = ConnectDb.getConnection().createStatement();
				stmt.executeUpdate(query2);
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static String generatePMID() {
		String PMID = "";
		String query = "select * from phieumuon;";
		try {
			Statement stmt = ConnectDb.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int count = 0;
			if (!rs.first()) {
//				System.out.println("Empty!");
			} else {
				do {
					count++;
				}while (rs.next());
			}
			
			String str1, str2 = Integer.toString(count + 1);
			if (count < 9) {
				str1 = "PM00";
			} else if (count >= 9 & count < 99) {
				str1 = "PM0";
			} else {
				str1 = "PM";
			}
			PMID = str1 + str2;
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		IDPM = PMID;
		return PMID;
	}
	
	public static String getIDPM() {
		return IDPM;
	}



	public static void main(String[] args) {
		new DatabaseController();
	}
}
