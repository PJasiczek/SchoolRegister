package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import Register.SQLConnection;
import Users.Pupil;
import net.proteanit.sql.DbUtils;

public class SearchInfoAboutTeachersPanel extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection connection = null;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTable table_1;

	String query;
	PreparedStatement ps;
	ResultSet rs;

	public SearchInfoAboutTeachersPanel() {
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		connection = SQLConnection.getConnection();
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 714, 402);
		contentPane.add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setEnabled(false);

		JLabel lblFirstName = new JLabel("Imi\u0119:", JLabel.RIGHT);
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFirstName.setBounds(12, 15, 55, 21);
		contentPane.add(lblFirstName);

		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(75, 8, 135, 31);
		contentPane.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);

		JLabel lblLastName = new JLabel("Nazwisko:", JLabel.RIGHT);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLastName.setBounds(203, 15, 86, 21);
		contentPane.add(lblLastName);

		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(295, 7, 135, 32);
		contentPane.add(textFieldLastName);
		textFieldLastName.setColumns(10);

		query = "SELECT nauczyciel.imie as 'Imie', nauczyciel.nazwisko as 'Nazwisko', nauczyciel.tytul_naukowy as 'Tytul', \r\n"
				+ "nauczyciel.nr_kontaktowy as 'Numer kontaktowy', przedmiot.nazwa_przedmiotu as 'Prowadzony przedmiot'\r\n"
				+ "FROM nauczyciel, przedmiot\r\n" + "WHERE przedmiot.nauczyciel_id=nauczyciel.nauczyciel_id\r\n"
				+ "GROUP BY nauczyciel.nauczyciel_id";

		try {
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (SQLException ex) {
			
			ex.printStackTrace();
		}
		
		JButton btnSearchTeacher = new JButton("Szukaj");
		btnSearchTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!textFieldFirstName.getText().isEmpty() && !textFieldLastName.getText().isEmpty()) {
					
					table_1.setModel(DbUtils.resultSetToTableModel(Pupil.searchInformationAboutTeachers(textFieldFirstName.getText(), textFieldLastName.getText())));
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Proszê o wprowadzenie imienia i nazwiska nauczyciela w wyznaczonych polach do wyszukiwania.", "Wyszukiwanie informacji o nauczycielach", JOptionPane.INFORMATION_MESSAGE, null);
				}
			}
		});
		btnSearchTeacher.setBounds(474, 13, 135, 25);
		contentPane.add(btnSearchTeacher);
		
	}
}
