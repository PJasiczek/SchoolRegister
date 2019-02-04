package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import Users.Educator;

public class EducatorChangePassword extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private long pupilId;
	Connection connection = null;
	Educator educator;

	public EducatorChangePassword(JFrame owner, Educator educator_1, long pupilId) {
		super(owner, "Zmiana has³a", true);
		this.pupilId = pupilId;
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		setTitle("Zmiana has\u0142a");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 384, 217);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(156, 123, 59, 35);
		contentPanel.add(buttonPane);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(this);
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			JLabel lblPassword = new JLabel("Has\u0142o:");
			lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblPassword.setBounds(85, 49, 56, 16);
			contentPanel.add(lblPassword);
		}
		{
			JLabel lblRepeatPassword = new JLabel("Powt\u00F3rz has\u0142o:");
			lblRepeatPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblRepeatPassword.setBounds(28, 89, 108, 16);
			contentPanel.add(lblRepeatPassword);
		}

		passwordField = new JPasswordField();
		passwordField.setBounds(135, 44, 185, 27);
		contentPanel.add(passwordField);
		{
			passwordField_1 = new JPasswordField();
			passwordField_1.setBounds(135, 81, 185, 27);
			contentPanel.add(passwordField_1);
		}

		JCheckBox chckbxShowPassword = new JCheckBox("Poka\u017C has\u0142o");
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxShowPassword.isSelected()) {
					
					passwordField.setEchoChar((char) 0);
				} else {
					
					passwordField.setEchoChar('*');
				}
			}
		});
		chckbxShowPassword.setBounds(236, 117, 113, 25);
		contentPanel.add(chckbxShowPassword);
		
		educator = educator_1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String password = new String(passwordField.getPassword());
		String password_1 = new String(passwordField_1.getPassword());
		
		if (password.equals(password_1) && !password.isEmpty() && !password_1.isEmpty()) {
			
			educator.changePasswordToLoginPupil(pupilId, password);
			dispose();
		} else {
			
			JOptionPane.showMessageDialog(null, "Proszê o wprowadzenie nowego has³a jeszcze raz", "Zmiana has³a",
					JOptionPane.ERROR_MESSAGE, null);
		}	
	}
}
