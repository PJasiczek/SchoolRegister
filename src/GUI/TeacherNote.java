package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import Users.Teacher;

public class TeacherNote extends JDialog implements ActionListener 	{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextArea textAreaNote;
	private JTextField textFieldPupil;
	private long pupilId;
	Teacher teacher;
	
	public TeacherNote(JFrame owner, Teacher teacher_1, long pupilId, String pupil) {
		super(owner, "Uwaga", true);
		this.pupilId = pupilId;
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		setTitle("Wprowadz uwag\u0119");
		setBounds(100, 100, 384, 368);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setBounds(10, 42, 348, 240);
		contentPanel.add(scrollpane);

		textAreaNote = new JTextArea();
		scrollpane.setViewportView(textAreaNote);
		textAreaNote.setWrapStyleWord(true);
		textAreaNote.setToolTipText("Wpisz odpowiedni\u0105 uwag\u0119 dla wybranego ucznia");
		{
			JButton okButton = new JButton("Zatwierdz");
			okButton.setBounds(82, 293, 189, 25);
			contentPanel.add(okButton);
			okButton.addActionListener(this);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}

		JLabel lblPupil = new JLabel("Ucze\u0144:");
		lblPupil.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPupil.setBounds(19, 9, 53, 23);
		contentPanel.add(lblPupil);

		textFieldPupil = new JTextField();
		textFieldPupil.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldPupil.setBounds(82, 11, 276, 23);
		contentPanel.add(textFieldPupil);
		textFieldPupil.setColumns(10);
		textFieldPupil.setEditable(false);
		textFieldPupil.setText(pupil);
		
		teacher = teacher_1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (!textAreaNote.getText().isEmpty()) {
			
			teacher.insertNote(pupilId, textAreaNote.getText());
		}
		else {
			
			JOptionPane.showMessageDialog(null, "Proszê o wprowadzenie treœci uwagi w wyznaczonym do tego polu tekstowym.", 
					"Uwaga", JOptionPane.INFORMATION_MESSAGE, null);
		}
		
		dispose();

	}
}