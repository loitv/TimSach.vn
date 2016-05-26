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
	private ArrayList<String> ISBNs;
	private ArrayList<String> ISBNs1;
	private ArrayList<String> ISBNs2;
	private ArrayList<String> ISBNs3;
	private ArrayList<String> AuthorID;

	public DatabaseController() {
		dbv = new DatabaseView();

		// Handle event for button 'Parse HTML'
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
						if (authors.size() == 2) {
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
						int total = new Random().nextInt(20) + 1;
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

		// Handle event for button 'Update sach'
		dbv.setButtonUpdateSachActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// String book = null;
				if (dbv.getLib1().isSelected()) {
					getISBNs1();
					String isbn = dbv.getTfISBN1().getText();
					int count = 0;
					for (int i = 0; i < ISBNs1.size(); i++) {
						if (isbn.equalsIgnoreCase(ISBNs1.get(i))) {
							count++;
						}
					}
					if (count > 0) {
						System.out.println("Sach da co trong thu vien");
					} else {
						updateBook("SACH", isbn);
					}
				}
				if (dbv.getLib2().isSelected()) {
					getISBNs2();
					String isbn = dbv.getTfISBN1().getText();
					int count = 0;
					for (int i = 0; i < ISBNs2.size(); i++) {
						if (isbn.equalsIgnoreCase(ISBNs2.get(i))) {
							count++;
						}
					}
					if (count > 0) {
						System.out.println("Sach da co trong thu vien");
					} else {
						updateBook("SACHTV02", isbn);
					}
				}
				if (dbv.getLib3().isSelected()) {
					getISBNs3();
					String isbn = dbv.getTfISBN1().getText();
					int count = 0;
					for (int i = 0; i < ISBNs3.size(); i++) {
						if (isbn.equalsIgnoreCase(ISBNs3.get(i))) {
							count++;
						}
					}
					if (count > 0) {
						System.out.println("Sach da co trong thu vien");
					} else {
						updateBook("SACHTV03", isbn);
					}
				}
			}
		});

		// handle event for button 'Update thong tin sach'
		dbv.setButtonUpdateInfoActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getISBNs();
				String isbn = dbv.getTfISBN2().getText();
				int count = 0;
				for (int i = 0; i < ISBNs.size(); i++) {
					if (isbn.equalsIgnoreCase(ISBNs.get(i))) {
						count++;
					}
				}
				if (count > 0) {
					System.out.println("Sach da co trong Database");
				} else {
					String query = "INSERT into THONGTINSACH values (?,?,?,?,?,?,?,?);";
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
				}

			}
		});

		// handle event for button 'Update tac gia'
		dbv.setButtonUpdateAuthorActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AuthorID = new ArrayList<String>();
				try {
					Statement stmt = ConnectDb.getConnection().createStatement();
					String query = "select * from tacgia;";
					ResultSet rs = stmt.executeQuery(query);
					if (!rs.first()) {
						System.out.println("Empty data!");
					} else {
						do {
							AuthorID.add(rs.getString("IDAuthor"));
						} while (rs.next());
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				String AuID = dbv.getTfAuID1().getText();
				String firstName1 = dbv.getTfAuFirstName().getText();
				String lastName1 = dbv.getTfAuLastName().getText();
				int count = 0;
				for (int i = 0; i < AuthorID.size(); i++) {
					if (AuID.equalsIgnoreCase(AuthorID.get(i))) {
						count++;
					}
				}
				if (count > 0) {
					System.out.println("Author 1 has been existed in Database");
				} else {
					String query = "INSERT into TACGIA values (?,?,?);";
					try (PreparedStatement insertStmt = (PreparedStatement) ConnectDb.getConnection()
							.prepareStatement(query)) {
						insertStmt.setString(1, AuID);
						insertStmt.setString(2, lastName1);
						insertStmt.setString(3, firstName1);
						insertStmt.executeUpdate();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}

				String author2 = dbv.getTfAuthor2().getText();
				if (author2.length() > 0) {
					String AuID2 = dbv.getTfAu2ID1().getText();
					String firstName2 = dbv.getTfAuFirstName2().getText();
					String lastName2 = dbv.getTfAuLastName2().getText();
					int count2 = 0;
					for (int i = 0; i < AuthorID.size(); i++) {
						if (AuID2.equalsIgnoreCase(AuthorID.get(i))) {
							count2++;
						}
					}
					if (count2 > 0) {
						System.out.println("Author 2 has been existed in Database");
					} else {
						String query = "INSERT into TACGIA values (?,?,?);";
						try (PreparedStatement insertStmt = (PreparedStatement) ConnectDb.getConnection()
								.prepareStatement(query)) {
							insertStmt.setString(1, AuID2);
							insertStmt.setString(2, lastName2);
							insertStmt.setString(3, firstName2);
							insertStmt.executeUpdate();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});

		// handle events for button 'update Tacgia-sach'
		dbv.setButtonUpdateAuBookActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> newISBNs = new ArrayList<String>();
				try {
					Statement stmt = ConnectDb.getConnection().createStatement();
					String query = "select * from tacgia_sach;";
					ResultSet rs = stmt.executeQuery(query);
					if (!rs.first()) {
						System.out.println("Empty data!");
					} else {
						do {
							newISBNs.add(rs.getString("ISBN"));
						} while (rs.next());
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

				String isbn = dbv.getTfISBN3().getText();
				int count = 0;
				for (int i = 0; i < newISBNs.size(); i++) {
					if (isbn.equalsIgnoreCase(newISBNs.get(i))) {
						count++;
					}
				}
				if (count > 0) {
					System.out.println("DB has been had the same data");
				} else {
					String auID1 = dbv.getTfAuID1().getText();
					String query = "INSERT into tacgia_sach values (?,?);";
					try (PreparedStatement insertStmt = (PreparedStatement) ConnectDb.getConnection()
							.prepareStatement(query)) {
						insertStmt.setString(1, auID1);
						insertStmt.setString(2, isbn);
						insertStmt.executeUpdate();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}

					if (dbv.getTfAu2ID1().getText().length() > 0) {
						String query2 = "INSERT into tacgia_sach values (?,?);";
						try (PreparedStatement insertStmt = (PreparedStatement) ConnectDb.getConnection()
								.prepareStatement(query2)) {
							insertStmt.setString(1, dbv.getTfAu2ID1().getText());
							insertStmt.setString(2, isbn);
							insertStmt.executeUpdate();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});

		// handle event for button 'update XML'
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

		// handle event for button save Image
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}


	public void updateBook(String book, String isbn) {
		String title = dbv.getTfTitle1().getText();
		int total = Integer.parseInt(dbv.getTfTotal().getText());
		int remain = Integer.parseInt(dbv.getTfRemain().getText());
		int onLoan = Integer.parseInt(dbv.getTfOnLoan().getText());

		String query = "INSERT into " + book + " values (?,?,?,?,?);";
		try (PreparedStatement insertStmt = (PreparedStatement) ConnectDb.getConnection().prepareStatement(query)) {
			insertStmt.setString(1, isbn);
			insertStmt.setString(2, title);
			insertStmt.setInt(3, total);
			insertStmt.setInt(4, remain);
			insertStmt.setInt(5, onLoan);
			insertStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
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
					IDAuthors.add(rs.getString("IDAuthor"));
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

	public void getISBNs1() {
		ISBNs1 = new ArrayList<String>();
		try {
			Statement stmt = ConnectDb.getConnection().createStatement();
			String query = "select * from sach;";
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				System.out.println("Empty data!");
			} else {
				do {
					ISBNs1.add(rs.getString("ISBN"));
				} while (rs.next());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void getISBNs2() {
		ISBNs2 = new ArrayList<String>();
		try {
			Statement stmt = ConnectDb.getConnection().createStatement();
			String query = "select * from sachtv02;";
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				System.out.println("Empty data!");
			} else {
				do {
					ISBNs2.add(rs.getString("ISBN"));
				} while (rs.next());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void getISBNs3() {
		ISBNs3 = new ArrayList<String>();
		try {
			Statement stmt = ConnectDb.getConnection().createStatement();
			String query = "select * from sachtv03;";
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				System.out.println("Empty data!");
			} else {
				do {
					ISBNs3.add(rs.getString("ISBN"));
				} while (rs.next());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void getISBNs() {
		ISBNs = new ArrayList<String>();
		try {
			Statement stmt = ConnectDb.getConnection().createStatement();
			String query = "select * from thongtinsach;";
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
	}

	public ArrayList<String> splitString(String str1, String str2) {
		StringTokenizer st = new StringTokenizer(str1, str2);
		ArrayList<String> authors = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			authors.add(st.nextToken().trim());
		}
		return authors;
	}

	public static void main(String[] args) {
		// String author = "Andrew Alle, Daniel Emma";
		// StringTokenizer st = new StringTokenizer(author, ",");
		// ArrayList<String> name = new ArrayList<String>();
		// while (st.hasMoreTokens()) {
		// name.add(st.nextToken().trim());
		// }
		//
		// if (name.size() < 2) {
		//
		// }
		// for (int i = 0; i < name.size(); i++) {
		// System.out.println(name.get(i));
		// }

		new DatabaseController();

		// int total = new Random().nextInt(20) + 1;
		// int remain = 0;
		// do {
		// remain = new Random().nextInt(20) + 1;
		// } while ((total - remain) > 5 | (total - remain) < 0);
		// System.out.println(total + " " + remain);

	}
}
