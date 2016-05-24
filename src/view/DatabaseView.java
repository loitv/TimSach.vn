package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DatabaseView extends JFrame {

	private JLabel lbURL, lbUpdateSach, lbUpdateInfo, lbUpdateAuBook, lbUpdateAu, lbUpdateXML, lbISBN1, lbISBN2,
			lbISBN3, lbISBN4, lbTitle1, lbTitle2, lbTotal, lbRemain, lbOnLoan, lbCategory, lbPublisher, lbYear, lbPrice,
			lbPage, lbLang, lbDesc, lbAuID1, lbAuID2, lbAu2ID1, lbAu2ID2, lbAuFirstName, lbAuFirstName2, lbAuLastName,
			lbAuLastName2, lbAuthor, lbAuthor2;
	private JTextField tfURL, tfISBN1, tfISBN2, tfISBN3, tfISBN4, tfTitle1, tfTitle2, tfTotal, tfRemain, tfOnLoan,
			tfCate, tfPub, tfYear, tfPrice, tfPage, tfLang, tfAuID1, tfAuID2, tfAu2ID1, tfAu2ID2, tfAuFirstName,
			tfAuLastName, tfAuFirstName2, tfAuLastName2, tfDesc, tfAuthor, tfAuthor2;
	private JButton btnParse, btnUpdateSach, btnUpdateInfo, btnUpdateAuBook, btnUpdateAu, btnUpdateXML;

	private JRadioButton lib1, lib2, lib3;
	private ButtonGroup lib;

	public DatabaseView() {
		super("Update Database");
		setSize(750, 650);
		setLayout(new GridLayout(23, 1, 5, 5));

		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout(5, 5));
		lbURL = new JLabel("URL: ");
		tfURL = new JTextField();
		btnParse = new JButton("Parse HTML");
		panel1.add(lbURL, BorderLayout.WEST);
		panel1.add(tfURL, BorderLayout.CENTER);
		panel1.add(btnParse, BorderLayout.EAST);
		add(panel1);

		JPanel panel2 = new JPanel();
		lbUpdateSach = new JLabel("Update 'Sach'");
		lbUpdateSach.setForeground(Color.BLUE);
		panel2.add(lbUpdateSach);
		add(panel2);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1, 6, 5, 5));
		lbISBN1 = new JLabel("ISBN");
		tfISBN1 = new JTextField();
		lbTotal = new JLabel("Tong so");
		tfTotal = new JTextField();
		lbOnLoan = new JLabel("SL cho muon");
		tfOnLoan = new JTextField();
		panel3.add(lbISBN1);
		panel3.add(tfISBN1);
		panel3.add(lbTotal);
		panel3.add(tfTotal);
		panel3.add(lbOnLoan);
		panel3.add(tfOnLoan);
		add(panel3);

		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayout(1, 6, 5, 5));
		lbTitle1 = new JLabel("Tieu de");
		lbRemain = new JLabel("SL trong kho");
		tfTitle1 = new JTextField();
		tfRemain = new JTextField();
		panel4.add(lbTitle1);
		panel4.add(tfTitle1);
		panel4.add(lbRemain);
		panel4.add(tfRemain);
		panel4.add(new JLabel());
		panel4.add(new JLabel());
		add(panel4);

		JPanel panel5 = new JPanel();
		panel5.setLayout(new GridLayout(1, 6, 5, 5));
		lib1 = new JRadioButton("Thu vien 1");
		lib1.setSelected(true);
		lib2 = new JRadioButton("Thu vien 2");
		lib3 = new JRadioButton("Thu vien 3");
		lib = new ButtonGroup();
		lib.add(lib1);
		lib.add(lib2);
		lib.add(lib3);
		panel5.add(lib1);
		panel5.add(lib2);
		panel5.add(lib3);
		panel5.add(new JLabel());
		panel5.add(new JLabel());
		panel5.add(new JLabel());
		add(panel5);

		JPanel panel5a = new JPanel();
		panel5a.add(new JLabel("---------------------------------------------------------------------------------------"
				+ "---------------------------------------------------------------------------------------------------"));
		add(panel5a);

		JPanel panel6 = new JPanel();
		lbUpdateInfo = new JLabel("Update 'Thong tin sach'");
		lbUpdateInfo.setForeground(Color.BLUE);
		panel6.add(lbUpdateInfo);
		add(panel6);

		JPanel panel7 = new JPanel();
		panel7.setLayout(new GridLayout(1, 6, 5, 5));
		lbISBN2 = new JLabel("ISBN");
		lbYear = new JLabel("Nam xb");
		lbLang = new JLabel("Ngon ngu");
		tfISBN2 = new JTextField();
		tfYear = new JTextField();
		tfLang = new JTextField();
		panel7.add(lbISBN2);
		panel7.add(tfISBN2);
		panel7.add(lbYear);
		panel7.add(tfYear);
		panel7.add(lbLang);
		panel7.add(tfLang);
		add(panel7);

		JPanel panel8 = new JPanel();
		panel8.setLayout(new GridLayout(1, 6, 5, 5));
		lbCategory = new JLabel("Linh vuc");
		lbPrice = new JLabel("Gia bia");
		tfCate = new JTextField("Programming");
		tfPrice = new JTextField();
		panel8.add(lbCategory);
		panel8.add(tfCate);
		panel8.add(lbPrice);
		panel8.add(tfPrice);
		panel8.add(new JLabel());
		panel8.add(new JLabel());
		add(panel8);

		JPanel panel9 = new JPanel();
		panel9.setLayout(new GridLayout(1, 6, 5, 5));
		lbPublisher = new JLabel("NXB");
		lbPage = new JLabel("So trang");
		tfPub = new JTextField();
		tfPage = new JTextField();
		panel9.add(lbPublisher);
		panel9.add(tfPub);
		panel9.add(lbPage);
		panel9.add(tfPage);
		panel9.add(new JLabel());
		panel9.add(new JLabel());
		add(panel9);

		JPanel panel9a = new JPanel();
		panel9a.add(new JLabel("---------------------------------------------------------------------------------------"
				+ "---------------------------------------------------------------------------------------------------"));
		add(panel9a);

		JPanel panel10 = new JPanel();
		lbUpdateAuBook = new JLabel("Update 'Tac gia - Sach'");
		lbUpdateAuBook.setForeground(Color.BLUE);
		panel10.add(lbUpdateAuBook);
		add(panel10);

		JPanel panel11 = new JPanel();
		panel11.setLayout(new GridLayout(1, 6, 5, 5));
		lbAuID1 = new JLabel("ID tac gia 1: ");
		lbAu2ID1 = new JLabel("ID tac gia 2: ");
		lbISBN3 = new JLabel("ISBN");
		tfAuID1 = new JTextField();
		tfAu2ID1 = new JTextField();
		tfISBN3 = new JTextField();
		panel11.add(lbAuID1);
		panel11.add(tfAuID1);
		panel11.add(lbAu2ID1);
		panel11.add(tfAu2ID1);
		panel11.add(lbISBN3);
		panel11.add(tfISBN3);
		// panel11.add(new JLabel());
		// panel11.add(new JLabel());
		add(panel11);

		JPanel panel11a = new JPanel();
		panel11a.add(
				new JLabel("---------------------------------------------------------------------------------------"
						+ "---------------------------------------------------------------------------------------------------"));
		add(panel11a);

		JPanel panel12 = new JPanel();
		lbUpdateAu = new JLabel("Update 'Tac gia'");
		lbUpdateAu.setForeground(Color.BLUE);
		panel12.add(lbUpdateAu);
		add(panel12);

		JPanel panel12a = new JPanel();
		panel12a.setLayout(new GridLayout(1, 2, 5, 5));
		JPanel panel12a1 = new JPanel();
		panel12a1.setLayout(new BorderLayout(5, 5));
		lbAuthor = new JLabel("Tac gia 1: ");
		tfAuthor = new JTextField();
		panel12a1.add(lbAuthor, BorderLayout.WEST);
		panel12a1.add(tfAuthor, BorderLayout.CENTER);
		panel12a.add(panel12a1);
		JPanel panel12a2 = new JPanel();
		panel12a2.setLayout(new BorderLayout(5, 5));
		lbAuthor2 = new JLabel("Tac gia 2: ");
		tfAuthor2 = new JTextField();
		panel12a2.add(lbAuthor2, BorderLayout.WEST);
		panel12a2.add(tfAuthor2, BorderLayout.CENTER);
		panel12a.add(panel12a2);
		add(panel12a);

		JPanel panel13 = new JPanel();
		panel13.setLayout(new GridLayout(1, 6, 5, 5));
		JPanel panel13a = new JPanel();
		panel13a.setLayout(new BorderLayout(3, 3));
		JPanel panel13b = new JPanel();
		panel13b.setLayout(new BorderLayout(3, 3));
		JPanel panel13c = new JPanel();
		panel13c.setLayout(new BorderLayout(3, 3));
		JPanel panel13d = new JPanel();
		panel13d.setLayout(new BorderLayout(3, 3));
		JPanel panel13e = new JPanel();
		panel13e.setLayout(new BorderLayout(3, 3));
		JPanel panel13f = new JPanel();
		panel13f.setLayout(new BorderLayout(3, 3));
		lbAuID2 = new JLabel("ID tac gia 1:");
		lbAuFirstName = new JLabel("Ten 1:");
		lbAuLastName = new JLabel("Ho 1:");
		tfAuID2 = new JTextField();
		tfAuFirstName = new JTextField();
		tfAuLastName = new JTextField();
		lbAu2ID2 = new JLabel("ID tac gia 2:");
		lbAuFirstName2 = new JLabel("Ten 2:");
		lbAuLastName2 = new JLabel("Ho 2:");
		tfAu2ID2 = new JTextField();
		tfAuFirstName2 = new JTextField();
		tfAuLastName2 = new JTextField();

		panel13a.add(lbAuID2, BorderLayout.WEST);
		panel13a.add(tfAuID2, BorderLayout.CENTER);
		panel13b.add(lbAuFirstName, BorderLayout.WEST);
		panel13b.add(tfAuFirstName, BorderLayout.CENTER);
		panel13c.add(lbAuLastName, BorderLayout.WEST);
		panel13c.add(tfAuLastName, BorderLayout.CENTER);
		panel13d.add(lbAu2ID2, BorderLayout.WEST);
		panel13d.add(tfAu2ID2, BorderLayout.CENTER);
		panel13e.add(lbAuFirstName2, BorderLayout.WEST);
		panel13e.add(tfAuFirstName2, BorderLayout.CENTER);
		panel13f.add(lbAuLastName2, BorderLayout.WEST);
		panel13f.add(tfAuLastName2, BorderLayout.CENTER);
		panel13.add(panel13a);
		panel13.add(panel13b);
		panel13.add(panel13c);
		panel13.add(panel13d);
		panel13.add(panel13e);
		panel13.add(panel13f);
		add(panel13);

		JPanel panel13aa = new JPanel();
		panel13aa.add(
				new JLabel("---------------------------------------------------------------------------------------"
						+ "---------------------------------------------------------------------------------------------------"));
		add(panel13aa);

		JPanel panel14 = new JPanel();
		lbUpdateXML = new JLabel("Update file XML");
		lbUpdateXML.setForeground(Color.BLUE);
		panel14.add(lbUpdateXML);
		add(panel14);

		JPanel panel15 = new JPanel();
		panel15.setLayout(new GridLayout(1, 6, 5, 5));
		lbISBN4 = new JLabel("ISBN");
		lbTitle2 = new JLabel("Tieu de");
		tfISBN4 = new JTextField();
		tfTitle2 = new JTextField();
		panel15.add(lbISBN4);
		panel15.add(tfISBN4);
		panel15.add(lbTitle2);
		panel15.add(tfTitle2);
		panel15.add(new JLabel());
		panel15.add(new JLabel());
		add(panel15);

		JPanel panel16 = new JPanel();
		panel16.setLayout(new BorderLayout(5, 5));
		lbDesc = new JLabel("Gioi thieu");
		tfDesc = new JTextField();
		panel16.add(lbDesc, BorderLayout.WEST);
		panel16.add(tfDesc, BorderLayout.CENTER);
		add(panel16);

		JPanel panel17 = new JPanel();
		add(panel17);

		JPanel panel18 = new JPanel();
		panel18.setLayout(new GridLayout(1, 5, 5, 5));
		btnUpdateSach = new JButton("Update 'Sach'");
		btnUpdateInfo = new JButton("Update 'TT Sach'");
		btnUpdateAuBook = new JButton("Update 'TG-Sach'");
		btnUpdateAu = new JButton("Update 'Tac gia'");
		btnUpdateXML = new JButton("Update file XML");
		panel18.add(btnUpdateSach);
		panel18.add(btnUpdateInfo);
		panel18.add(btnUpdateAuBook);
		panel18.add(btnUpdateAu);
		panel18.add(btnUpdateXML);
		add(panel18);

		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);

	}
	// SET ACTION LISTENER

	public void setButtonParseActionListener(ActionListener al) {
		this.btnParse.addActionListener(al);
	}

	public void setButtonUpdateSachActionListener(ActionListener al) {
		this.btnUpdateSach.addActionListener(al);
	}

	public void setButtonUpdateInfoActionListener(ActionListener al) {
		this.btnUpdateInfo.addActionListener(al);
	}

	public void setButtonUpdateAuBookActionListener(ActionListener al) {
		this.btnUpdateAuBook.addActionListener(al);
	}

	public void setButtonUpdateAuthorActionListener(ActionListener al) {
		this.btnUpdateAu.addActionListener(al);
	}

	public void setButtonUpdateXMLActionListener(ActionListener al) {
		this.btnUpdateXML.addActionListener(al);
	}

	// GETTER SETTER METHODS

	public JTextField getTfURL() {
		return tfURL;
	}

	public JTextField getTfISBN1() {
		return tfISBN1;
	}

	public JTextField getTfISBN2() {
		return tfISBN2;
	}

	public JTextField getTfISBN3() {
		return tfISBN3;
	}

	public JTextField getTfISBN4() {
		return tfISBN4;
	}

	public JTextField getTfTitle1() {
		return tfTitle1;
	}

	public JTextField getTfTitle2() {
		return tfTitle2;
	}

	public JTextField getTfTotal() {
		return tfTotal;
	}

	public JTextField getTfRemain() {
		return tfRemain;
	}

	public JTextField getTfOnLoan() {
		return tfOnLoan;
	}

	public JTextField getTfCate() {
		return tfCate;
	}

	public JTextField getTfPub() {
		return tfPub;
	}

	public JTextField getTfYear() {
		return tfYear;
	}

	public JTextField getTfPrice() {
		return tfPrice;
	}

	public JTextField getTfPage() {
		return tfPage;
	}

	public JTextField getTfLang() {
		return tfLang;
	}

	public JTextField getTfAuID1() {
		return tfAuID1;
	}

	public JTextField getTfAuID2() {
		return tfAuID2;
	}

	public JTextField getTfAuFirstName() {
		return tfAuFirstName;
	}

	public JTextField getTfAuLastName() {
		return tfAuLastName;
	}

	public JTextField getTfDesc() {
		return tfDesc;
	}

	public JButton getBtnParse() {
		return btnParse;
	}

	public JButton getBtnUpdateSach() {
		return btnUpdateSach;
	}

	public JButton getBtnUpdateInfo() {
		return btnUpdateInfo;
	}

	public JButton getBtnUpdateAuBook() {
		return btnUpdateAuBook;
	}

	public JButton getBtnUpdateAu() {
		return btnUpdateAu;
	}

	public JButton getBtnUpdateXML() {
		return btnUpdateXML;
	}

	public JRadioButton getLib1() {
		return lib1;
	}

	public JRadioButton getLib2() {
		return lib2;
	}

	public JRadioButton getLib3() {
		return lib3;
	}

	public JTextField getTfAuthor() {
		return tfAuthor;
	}

	public JTextField getTfAu2ID1() {
		return tfAu2ID1;
	}

	public JTextField getTfAu2ID2() {
		return tfAu2ID2;
	}

	public JTextField getTfAuFirstName2() {
		return tfAuFirstName2;
	}

	public JTextField getTfAuLastName2() {
		return tfAuLastName2;
	}

	public JTextField getTfAuthor2() {
		return tfAuthor2;
	}

	// Main
	public static void main(String[] args) {
		new DatabaseView();
	}
}
