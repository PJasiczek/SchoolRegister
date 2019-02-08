package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import Register.SQLConnection;
import Users.Teacher;

public class TeacherGrade extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldPupil;
	private JTextField textFieldGrades;
	private JTextField textFieldGrade;
	private JTextField textFieldSubject;
	private long pupilId;
	private long subjectId;
	Teacher teacher;

	public TeacherGrade(JFrame owner, Teacher teacher_1, long pupilId, String pupil, long subjectId, String subject) {
		super(owner, "Ocena", true);
		this.pupilId = pupilId;
		this.subjectId = subjectId;
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		setTitle("Wprowadz oceny");
		setBounds(100, 100, 496, 245);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(182, 173, 113, 35);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("OK");
				okButton.setBounds(34, 0, 49, 25);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
		}

		JLabel lblInsertGrade = new JLabel("Wprowadz ocen\u0119/oceny:", JLabel.RIGHT);
		lblInsertGrade.setBounds(39, 98, 154, 16);
		contentPanel.add(lblInsertGrade);

		JLabel lblInsertFinalGrade = new JLabel("Wprowadz ocen\u0119 ko\u0144cow\u0105:", JLabel.RIGHT);
		lblInsertFinalGrade.setBounds(56, 138, 158, 16);
		contentPanel.add(lblInsertFinalGrade);

		textFieldGrade = new JTextField();
		textFieldGrade.setBounds(226, 133, 69, 27);
		contentPanel.add(textFieldGrade);
		textFieldGrade.setColumns(10);

		textFieldGrades = new JTextField();
		textFieldGrades.setBounds(205, 93, 113, 27);
		contentPanel.add(textFieldGrades);
		textFieldGrades.setColumns(10);

		textFieldSubject = new JTextField();
		textFieldSubject.setEditable(false);
		textFieldSubject.setBounds(182, 53, 154, 27);
		contentPanel.add(textFieldSubject);
		textFieldSubject.setColumns(10);
		textFieldSubject.setText(subject);
		
		textFieldPupil = new JTextField();
		textFieldPupil.setEditable(false);
		textFieldPupil.setBounds(161, 13, 208, 27);
		contentPanel.add(textFieldPupil);
		textFieldPupil.setColumns(10);
		textFieldPupil.setText(pupil);
		JLabel lblPupilName = new JLabel("Imi\u0119 i nazwisko:");
		lblPupilName.setBounds(56, 18, 121, 16);
		contentPanel.add(lblPupilName);

		JLabel lblSubject = new JLabel("Przedmiot:");
		lblSubject.setBounds(101, 58, 69, 16);
		contentPanel.add(lblSubject);
		
		teacher = teacher_1;
		
		try {
			
			Connection connection = SQLConnection.getConnection();
			
			String query = "SELECT grades, grade FROM grade WHERE pupil_id=? and subject_id=?";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, pupilId);
			ps.setLong(2, subjectId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				textFieldGrades.setText((rs.getString("grades")));
				textFieldGrade.setText((rs.getString("grade")));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			teacher.insertGrade(subjectId, pupilId, textFieldGrades.getText(), textFieldGrade.getText());
			dispose();
		}catch(Exception ex) {
			
			ex.getMessage();
		}
	}
}
