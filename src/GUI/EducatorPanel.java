package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import Register.SQLConnection;
import System.Subject;
import System.Class;
import Users.Educator;
import Users.Pupil;
import net.proteanit.sql.DbUtils;

public class EducatorPanel extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	static JTable table_1;
	private JButton btnPupilModification;
	private JButton btnLookOverGrades; 
	private JButton btnLogout;
	private JButton btnLookOverNotes;
	private JButton btnSelectClass;
	private JButton btnSelectSubject;
	private JButton btnSelectPupil;
	static JComboBox<String> comboBoxPupils;
	static JComboBox<String> comboBoxSubjects;
	static JComboBox<String> comboBoxClass;
	static ArrayList<Subject> subjectsList = new ArrayList<Subject>();
	static ArrayList<Pupil> pupilsList = new ArrayList<Pupil>();
	static ArrayList<Class> classList = new ArrayList<Class>();
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private long educatorId;
	private long contactNumber;
	private String dateOfBirth;
	private String academicTitle;
	Educator educator;
	
	public EducatorPanel(long educatorId) {
		this.educatorId = educatorId;
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		setResizable(false);
		setTitle("Dziennik - Wychowawca");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1170, 681);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(225, 98, 927, 531);
		contentPane.add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);

		btnLookOverGrades = new JButton("Przegl¹daj oceny");
		btnLookOverGrades.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLookOverGrades.addActionListener(this);
		btnLookOverGrades.setBounds(29, 134, 164, 23);
		contentPane.add(btnLookOverGrades);

		btnLookOverNotes = new JButton("Przegl¹daj uwagi");
		btnLookOverNotes.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLookOverNotes.setBounds(29, 170, 164, 23);
		btnLookOverNotes.addActionListener(this);
		contentPane.add(btnLookOverNotes);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBoxPupils = new JComboBox<String>();
		comboBoxPupils.setBounds(939, 28, 150, 23);
		contentPane.add(comboBoxPupils);

		JLabel lblPupilData = new JLabel("Wyszukaj ucznia:");
		lblPupilData.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPupilData.setBounds(823, 30, 150, 16);
		contentPane.add(lblPupilData);

		JLabel lblSelectSubject = new JLabel("Wyszukaj przedmiot:");
		lblSelectSubject.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSelectSubject.setBounds(503, 30, 144, 16);
		contentPane.add(lblSelectSubject);

		comboBoxSubjects = new JComboBox<String>();
		comboBoxSubjects.setBounds(635, 28, 144, 23);
		contentPane.add(comboBoxSubjects);

		JLabel lblClass = new JLabel("Wyszukaj klase:");
		lblClass.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClass.setBounds(225, 30, 125, 16);
		contentPane.add(lblClass);

		comboBoxClass = new JComboBox<String>();
		comboBoxClass.setBounds(322, 28, 144, 23);
		contentPane.add(comboBoxClass);

		btnSelectClass = new JButton("Wybierz klase");
		btnSelectClass.addActionListener(this);
		btnSelectClass.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSelectClass.setBounds(269, 59, 173, 23);
		contentPane.add(btnSelectClass);

		btnPupilModification = new JButton("Modyfikacja ucznia");
		btnPupilModification.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPupilModification.addActionListener(this);
		btnPupilModification.setBounds(29, 206, 164, 23);
		contentPane.add(btnPupilModification);
		
		btnLogout = new JButton("Wyloguj");
		btnLogout.setBounds(12, 608, 97, 23);
		btnLogout.addActionListener(this);
		contentPane.add(btnLogout);
		
		btnSelectSubject = new JButton("Wybierz przedmiot");
		btnSelectSubject.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSelectSubject.addActionListener(this);
		btnSelectSubject.setBounds(550, 59, 173, 23);
		contentPane.add(btnSelectSubject);
		
		btnSelectPupil = new JButton("Wybierz ucznia");
		btnSelectPupil.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSelectPupil.addActionListener(this);
		btnSelectPupil.setBounds(876, 59, 173, 23);
		contentPane.add(btnSelectPupil);
		
		JLabel lblFirstName = new JLabel("Imi\u0119:");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFirstName.setBounds(-1, 31, 74, 14);
		contentPane.add(lblFirstName);
		
		JLabel lbLastName = new JLabel("Nazwisko:");
		lbLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lbLastName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbLastName.setBounds(-1, 69, 74, 14);
		contentPane.add(lbLastName);
		
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
		
		Educator.findClass(comboBoxClass, classList, comboBoxSubjects, subjectsList, comboBoxPupils, pupilsList, educatorId);
		
		try {
			
			Connection connection = SQLConnection.getConnection();
			
			String query = "SELECT first_name, last_name, contact_number, date_of_birth, academic_title\r\n" + "FROM educator\r\n" + "WHERE educator_id = " + Long.toString(this.educatorId)
					+ " GROUP BY educator_id";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {

				textFieldFirstName.setText(rs.getString("first_name"));
				textFieldLastName.setText(rs.getString("last_name"));
				this.contactNumber = rs.getLong("contact_number");
				this.dateOfBirth = rs.getString("date_of_birth");
				this.academicTitle = rs.getString("academic_title");
			}
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
		educator = new Educator(educatorId, textFieldFirstName.getText(), textFieldLastName.getText(), this.contactNumber, this.dateOfBirth, this.academicTitle);
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
			
			Educator.findSubject(comboBoxSubjects, subjectsList, classId, educatorId);
		}
		else if (source == btnSelectSubject) {
			
			if (comboBoxSubjects.getItemCount() != 0) {
				
				comboBoxPupils.removeAllItems();
				pupilsList.clear();
				long classId = classList.get(comboBoxClass.getSelectedIndex()).getId();
				Educator.findPupil(comboBoxPupils, pupilsList, classId);
			}
		}
		else if (source == btnSelectPupil) {
			
			long classId = classList.get(comboBoxClass.getSelectedIndex()).getId();
			long subjectId = subjectsList.get(comboBoxSubjects.getSelectedIndex()).getId();
			table_1.setModel(DbUtils.resultSetToTableModel(educator.lookOverGrades(classId, subjectId)));
		}
		else if(source ==  btnLookOverGrades) {
			
			long classId = classList.get(comboBoxClass.getSelectedIndex()).getId();
			long subjectId = subjectsList.get(comboBoxSubjects.getSelectedIndex()).getId();
			table_1.setModel(DbUtils.resultSetToTableModel(educator.lookOverGrades(classId, subjectId)));
		}
		else if(source == btnLookOverNotes) {
			
			table_1.setModel(DbUtils.resultSetToTableModel(educator.lookOverNotes()));
		}
		else if(source == btnPupilModification) {
			
			long pupilId = pupilsList.get(comboBoxPupils.getSelectedIndex()).getId();
			EducatorDataModification educatorDataModification = new EducatorDataModification(null, educator, pupilId);
			educatorDataModification.setVisible(true);
			educatorDataModification.setLocationRelativeTo(null);
		}
		else if(source == btnLogout) {
			
			EducatorLogin educatorLog = new EducatorLogin();
			educatorLog.setVisible(true);
			educatorLog.setLocationRelativeTo(null);
			educatorLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		}
	}

}