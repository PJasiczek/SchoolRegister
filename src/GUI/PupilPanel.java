package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Register.SQLConnection;
import System.Subject;
import Users.Pupil;
import net.proteanit.sql.DbUtils;

public class PupilPanel extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1;
	Connection connection = null;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldClass;
	private JTextField textFieldPesel;
	private JButton btnLookOverNotes;
	private JButton btnLookOverGrades;
	private JButton btnSearchInfoAboutTeachers;
	private JButton btnLogout;
	private JButton btnDisplayFinalGrades;
	private JButton btnLookOverAbsences;
	private Pupil pupil;
	private JComboBox<String> comboBoxSubjects;
	private ArrayList<Subject> SubjectsList = new ArrayList<Subject>();
	private long pupilId;
	private long classId;
	private long contactNumber;
	private String dateOfBirth;

	String query;
	PreparedStatement ps;
	ResultSet rs;

	public PupilPanel(long pupilId) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		this.pupilId = pupilId;
		connection = SQLConnection.getConnection();
		
		setTitle("Uczeñ");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1170, 681);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(225, 98, 927, 531);
		contentPane.add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setEnabled(false);

		JLabel lblFirstName = new JLabel("Imi\u0119:", JLabel.RIGHT);
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFirstName.setBounds(-1, 31, 74, 14);
		contentPane.add(lblFirstName);

		textFieldFirstName = new JTextField();
		textFieldFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFirstName.setBounds(75, 26, 131, 25);
		contentPane.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		textFieldFirstName.setEditable(false);

		textFieldLastName = new JTextField();
		textFieldLastName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldLastName.setBounds(75, 64, 131, 25);
		contentPane.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		textFieldLastName.setEditable(false);

		textFieldClass = new JTextField();
		textFieldClass.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldClass.setBounds(75, 140, 131, 25);
		contentPane.add(textFieldClass);
		textFieldClass.setColumns(10);
		textFieldClass.setEditable(false);

		JLabel lblLastName = new JLabel("Nazwisko:", JLabel.RIGHT);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLastName.setBounds(-1, 69, 74, 14);
		contentPane.add(lblLastName);

		JLabel lblClass = new JLabel("Klasa:", JLabel.RIGHT);
		lblClass.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblClass.setBounds(-1, 145, 74, 14);
		contentPane.add(lblClass);

		btnLookOverNotes = new JButton("Przegl\u0105daj Uwagi");
		btnLookOverNotes.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLookOverNotes.addActionListener(this);
		btnLookOverNotes.setBounds(17, 330, 196, 25);
		contentPane.add(btnLookOverNotes);

		btnLookOverGrades = new JButton("Przegl\u0105daj Oceny ");
		btnLookOverGrades.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLookOverGrades.addActionListener(this);
		btnLookOverGrades.setBounds(17, 253, 196, 25);
		contentPane.add(btnLookOverGrades);

		comboBoxSubjects = new JComboBox<String>();
		comboBoxSubjects.setBounds(434, 26, 196, 25);
		contentPane.add(comboBoxSubjects);
		comboBoxSubjects.setEditable(false);

		btnSearchInfoAboutTeachers = new JButton("Wyszukaj informacji o nauczycielu");
		btnSearchInfoAboutTeachers.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSearchInfoAboutTeachers.addActionListener(this);
		btnSearchInfoAboutTeachers.setBounds(831, 26, 253, 25);
		contentPane.add(btnSearchInfoAboutTeachers);

		JLabel lblPesel = new JLabel("PESEL:", JLabel.RIGHT);
		lblPesel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPesel.setBounds(-1, 107, 74, 14);
		contentPane.add(lblPesel);

		textFieldPesel = new JTextField();
		textFieldPesel.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPesel.setBounds(75, 102, 131, 25);
		contentPane.add(textFieldPesel);
		textFieldPesel.setColumns(10);
		textFieldPesel.setEditable(false);

		btnLogout = new JButton("Wyloguj");
		btnLogout.setBounds(12, 608, 97, 23);
		btnLogout.addActionListener(this);
		contentPane.add(btnLogout);

		btnDisplayFinalGrades = new JButton("Wy\u015Bwietl Oceny Ko\u0144cowe");
		btnDisplayFinalGrades.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDisplayFinalGrades.addActionListener(this);
		btnDisplayFinalGrades.setBounds(10, 291, 208, 25);
		contentPane.add(btnDisplayFinalGrades);

		btnLookOverAbsences = new JButton("Przegl\u0105daj nieobecno\u015Bci");
		btnLookOverAbsences.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLookOverAbsences.addActionListener(this);
		btnLookOverAbsences.setBounds(17, 215, 196, 25);
		contentPane.add(btnLookOverAbsences);

		JLabel lblSearchSubjects = new JLabel("Wyszukaj przedmiot:");
		lblSearchSubjects.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSearchSubjects.setBounds(291, 30, 148, 16);
		contentPane.add(lblSearchSubjects);
		
		try {
			
			query = "SELECT PESEL, imie, nazwisko, klasa_id, nr_kontaktowy, data_urodzenia\r\n" + "FROM uczen\r\n" + "WHERE id = " + Long.toString(uczen_id)
					+ " GROUP BY id";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {

				textFieldPesel.setText(rs.getString("PESEL"));
				textFieldFirstName.setText(rs.getString("imie"));
				textFieldLastName.setText(rs.getString("nazwisko"));
				this.classId = rs.getLong("klasa_id");
				this.contactNumber = rs.getLong("nr_kontaktowy");
				this.dateOfBirth = rs.getString("data_urodzenia");
			}
			
			query = "SELECT nazwa\r\n" + "FROM klasa\r\n" + "WHERE klasa_id = " + Long.toString(classId);
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				textFieldClass.setText(rs.getString("nazwa"));
			}
			
			query = "SELECT przedmiot_id, nazwa_przedmiotu \r\n" + "FROM przedmiot\r\n" + "WHERE klasa_id = "
					+ Long.toString(classId) + " GROUP BY przedmiot.przedmiot_id ";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("nazwa_przedmiotu");
				SubjectsList.add(new Subject(rs.getLong("przedmiot_id"), name));
				comboBoxSubjects.addItem(name);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		pupil = new Pupil(this.pupilId, textFieldFirstName.getText(), textFieldLastName.getText(), this.contactNumber, this.dateOfBirth);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == btnLookOverNotes) {
			table_1.setModel(DbUtils.resultSetToTableModel(pupil.przegladajUwagi()));
		}
		else if(source == btnLookOverGrades) {
			int index = comboBoxSubjects.getSelectedIndex();
			long subjectId = SubjectsList.get(index).getId();
			table_1.setModel(DbUtils.resultSetToTableModel(pupil.przegladajOceny(subjectId)));
		}
		else if(source == btnSearchInfoAboutTeachers) {
			SearchInfoAboutTeachersPanel searchInfoAbouttearchersPane = new SearchInfoAboutTeachersPanel();
			searchInfoAbouttearchersPane.setVisible(true);
			searchInfoAbouttearchersPane.setLocationRelativeTo(null);
		}
		else if(source == btnLogout) {
			PupilLogin pupilLog = new PupilLogin();
			pupilLog.setVisible(true);
			pupilLog.setLocationRelativeTo(null);
			pupilLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		}
		else if(source == btnDisplayFinalGrades) {
			table_1.setModel(DbUtils.resultSetToTableModel(pupil.przegladajOcenyKoncowe()));
		}
		else if(source == btnLookOverAbsences) {
			table_1.setModel(DbUtils.resultSetToTableModel(pupil.sprawdzNieobecnosci()));
		}	
	}
}

