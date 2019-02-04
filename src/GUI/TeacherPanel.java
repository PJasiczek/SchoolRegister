package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import Register.SQLConnection;
import System.Subject;
import System.Class;
import Users.Pupil;
import Users.Teacher;
import net.proteanit.sql.DbUtils;

public class TeacherPanel extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTable table_1;
	private JScrollPane scrollPane;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JButton btnInsertGrades;
	private JButton btnLogout;
	private JButton btnInsertNote;
	private JButton btnSelectClass;
	private JButton btnSelectSubject;
	private JButton btnSelectPupil;
	private JButton btnInsertAbsence;
	private JLabel lblSelectSubject;
	static JComboBox<String> comboBoxPupils;
	static JComboBox<String> comboBoxSubjects;
	static JComboBox<String> comboBoxClass;
	static ArrayList<Subject> subjectsList = new ArrayList<Subject>();
	static ArrayList<Pupil> pupilsList = new ArrayList<Pupil>();
	static ArrayList<Class> classList = new ArrayList<Class>();
	private long teacherId;
	private long contactNumber;
	private String dateOfBirth;
	private String academicTitle;
	Teacher teacher;

	public TeacherPanel(long teacherId) {
		this.teacherId = teacherId;
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		setResizable(false);
		setTitle("Dziennik - Nauczyciel");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1170, 681);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(225, 102, 927, 531);
		contentPane.add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);

		JLabel lblSelectClass = new JLabel("Wyszukaj klas\u0119:");
		lblSelectClass.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSelectClass.setBounds(225, 30, 125, 16);
		contentPane.add(lblSelectClass);

		comboBoxClass = new JComboBox<String>();
		comboBoxClass.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxClass.setToolTipText("Wybierz klas\u0119, dla kt\u00F3rej chcesz wprowadzi\u0107 zmiany");
		comboBoxClass.addActionListener(this);
		comboBoxClass.setBounds(322, 28, 144, 23);
		comboBoxClass.addActionListener(this);
		contentPane.add(comboBoxClass);

		btnInsertGrades = new JButton("Wpisz oceny");
		btnInsertGrades.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnInsertGrades.addActionListener(this);
		btnInsertGrades.setBounds(29, 146, 164, 23);
		contentPane.add(btnInsertGrades);

		btnInsertNote = new JButton("Wpisz uwag\u0119");
		btnInsertNote.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnInsertNote.setBounds(29, 182, 164, 23);
		btnInsertNote.addActionListener(this);
		contentPane.add(btnInsertNote);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnLogout = new JButton("Wyloguj");
		btnLogout.setBounds(12, 608, 97, 23);
		btnLogout.addActionListener(this);
		contentPane.add(btnLogout);

		lblSelectSubject = new JLabel("Wyszukaj przedmiot:");
		lblSelectSubject.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSelectSubject.setBounds(503, 30, 144, 16);
		contentPane.add(lblSelectSubject);

		comboBoxSubjects = new JComboBox<String>();
		comboBoxSubjects.setToolTipText("Wybierz klas\u0119, dla kt\u00F3rej chcesz wprowadzi\u0107 zmiany");
		comboBoxSubjects.addActionListener(this);
		comboBoxSubjects.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxSubjects.setBounds(635, 28, 144, 23);
		contentPane.add(comboBoxSubjects);

		btnSelectClass = new JButton("Wybierz klase");
		btnSelectClass.addActionListener(this);
		btnSelectClass.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSelectClass.setBounds(269, 59, 173, 23);
		contentPane.add(btnSelectClass);

		btnSelectSubject = new JButton("Wybierz przedmiot");
		btnSelectSubject.addActionListener(this);
		btnSelectSubject.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSelectSubject.setBounds(550, 59, 173, 23);
		contentPane.add(btnSelectSubject);

		JLabel lblSelectPupil = new JLabel("Wyszukaj ucznia:");
		lblSelectPupil.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSelectPupil.setBounds(823, 30, 150, 16);
		contentPane.add(lblSelectPupil);

		comboBoxPupils = new JComboBox<String>();
		comboBoxPupils.setToolTipText("Wybierz klas\u0119, dla kt\u00F3rej chcesz wprowadzi\u0107 zmiany");
		comboBoxPupils.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxPupils.setBounds(930, 28, 150, 23);
		contentPane.add(comboBoxPupils);

		btnInsertAbsence = new JButton("Wprowadz nieobecno\u015B\u0107");
		btnInsertAbsence.addActionListener(this);
		btnInsertAbsence.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnInsertAbsence.setBounds(11, 218, 202, 25);
		contentPane.add(btnInsertAbsence);
		
		btnSelectPupil = new JButton("Wybierz ucznia");
		btnSelectPupil.addActionListener(this);
		btnSelectPupil.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSelectPupil.setBounds(876, 59, 173, 23);
		contentPane.add(btnSelectPupil);
	
		Teacher.findClass(comboBoxClass, classList, comboBoxSubjects, subjectsList, comboBoxPupils, pupilsList, teacherId);
		
		JLabel lblImi = new JLabel("Imi\u0119:");
		lblImi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblImi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImi.setBounds(-1, 31, 74, 14);
		contentPane.add(lblImi);
		
		JLabel lblNazwisko = new JLabel("Nazwisko:");
		lblNazwisko.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNazwisko.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNazwisko.setBounds(-1, 69, 74, 14);
		contentPane.add(lblNazwisko);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFirstName.setEditable(false);
		textFieldFirstName.setBounds(75, 26, 131, 25);
		contentPane.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldLastName.setEditable(false);
		textFieldLastName.setBounds(75, 64, 131, 25);
		contentPane.add(textFieldLastName);
		textFieldLastName.setColumns(10);
	
		try {
			
			Connection connection = SQLConnection.getConnection();
			
			String query = "SELECT imie, nazwisko, nr_kontaktowy, data_urodzenia, tytul_naukowy\r\n" + "FROM nauczyciel\r\n" + "WHERE nauczyciel_id = " + Long.toString(this.teacherId)
					+ " GROUP BY nauczyciel_id";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {

				textFieldFirstName.setText(rs.getString("imie"));
				textFieldLastName.setText(rs.getString("nazwisko"));
				this.contactNumber = rs.getLong("nr_kontaktowy");
				this.dateOfBirth = rs.getString("data_urodzenia");
				this.academicTitle = rs.getString("tytul_naukowy");
			}
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		teacher = new Teacher(teacherId, textFieldFirstName.getText(), textFieldLastName.getText(), this.contactNumber, this.dateOfBirth, this.academicTitle);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == btnSelectClass) {
			
			comboBoxSubjects.removeAllItems();
			comboBoxPupils.removeAllItems();
			subjectsList.clear();
			pupilsList.clear();
			long classId = classList.get(comboBoxClass.getSelectedIndex()).getId();
			Teacher.findSubject(comboBoxSubjects, subjectsList, classId, teacherId);
		}
		else if (source == btnSelectSubject) {
			
			if (comboBoxSubjects.getItemCount() != 0) {
				
				comboBoxPupils.removeAllItems();
				pupilsList.clear();
				long classId = classList.get(comboBoxClass.getSelectedIndex()).getId();
				Teacher.findPupil(comboBoxPupils, pupilsList, classId);
			}
		}
		else if (source == btnSelectPupil) {
			
			int index = comboBoxSubjects.getSelectedIndex();
			long classId = classList.get(comboBoxClass.getSelectedIndex()).getId();
			long subjectId = subjectsList.get(comboBoxSubjects.getSelectedIndex()).getId();
			scrollPane.setBorder(BorderFactory.createTitledBorder("Oceny z przedmiotu: " + subjectsList.get(index).getName()));
			
			table_1.setModel(DbUtils.resultSetToTableModel(teacher.lookOverGrades(classId, subjectId)));
		}
		else if (source == btnInsertAbsence) {
			
			long pupilId = pupilsList.get(comboBoxPupils.getSelectedIndex()).getId();
			long classId = classList.get(comboBoxClass.getSelectedIndex()).getId();
			long subjectId = subjectsList.get(comboBoxSubjects.getSelectedIndex()).getId();
			String pupilData = pupilsList.get(comboBoxPupils.getSelectedIndex()).getFirst_LastName();
			
			teacher.checkPresence(pupilId, subjectId, classId, pupilData);
		}
		else if(source == btnInsertGrades) {
			
			int index = comboBoxSubjects.getSelectedIndex();
			scrollPane.setBorder(BorderFactory.createTitledBorder("Oceny z przedmiotu: " + subjectsList.get(index).getName()));
			long classId = classList.get(comboBoxClass.getSelectedIndex()).getId();
			long subjectId = subjectsList.get(comboBoxSubjects.getSelectedIndex()).getId();
			
			table_1.setModel(DbUtils.resultSetToTableModel(teacher.lookOverGrades(classId, subjectId)));
			
			long pupilId = pupilsList.get(comboBoxPupils.getSelectedIndex()).getId();
			
			TeacherGrade teacherGrade = new TeacherGrade(null, teacher, pupilId, pupilsList.get(comboBoxPupils.getSelectedIndex()).getFirst_LastName(), subjectId, subjectsList.get(comboBoxSubjects.getSelectedIndex()).getName());
			teacherGrade.setVisible(true);
			teacherGrade.setLocationRelativeTo(null);
		}
		else if(source == btnInsertNote) {
			
			long pupilId = pupilsList.get(comboBoxPupils.getSelectedIndex()).getId();
			TeacherNote teacherNote = new TeacherNote(null, teacher, pupilId, pupilsList.get(comboBoxPupils.getSelectedIndex()).getFirst_LastName());
			teacherNote.setVisible(true);
			teacherNote.setLocationRelativeTo(null);
		}
		else if(source == btnLogout) {
			
			TeacherLogin teacherlLog = new TeacherLogin();
			teacherlLog.setVisible(true);
			teacherlLog.setLocationRelativeTo(null);
			teacherlLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		}
		
	}
}

