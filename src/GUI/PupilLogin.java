package GUI;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Register.SQLConnection;

public class PupilLogin extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	Connection connection = null;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JButton btnTeacher;
	private JButton btnEducator;
	private JCheckBox chckbxShowPassword;

	public PupilLogin() {
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		setTitle("Logowanie");
		setBounds(100, 100, 645, 339);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		JLabel lblNewLabel = new JLabel("Nazwa u¿ytkownika:");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 17));
		lblNewLabel.setBounds(79, 127, 221, 26);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Has³o:");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 17));
		lblNewLabel_1.setBounds(196, 159, 149, 26);
		getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(264, 128, 161, 26);
		getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(264, 161, 161, 24);
		getContentPane().add(passwordField);

		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		btnLogin.addActionListener(this);
		btnLogin.setBounds(233, 198, 161, 26);
		getContentPane().add(btnLogin);

		JLabel lblNewLabel_2 = new JLabel("");
		Image image = new ImageIcon(this.getClass().getResource("/PolitechnikaWroclawska.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(image));
		lblNewLabel_2.setBounds(26, 13, 521, 91);
		getContentPane().add(lblNewLabel_2);

		chckbxShowPassword = new JCheckBox("Poka¿ has³o");
		chckbxShowPassword.setBounds(434, 161, 113, 25);
		chckbxShowPassword.addActionListener(this);
		getContentPane().add(chckbxShowPassword);

		btnTeacher = new JButton("Nauczyciel");
		btnTeacher.setBounds(415, 242, 161, 25);
		btnTeacher.addActionListener(this);
		getContentPane().add(btnTeacher);

		btnEducator = new JButton("Wychowawca");
		btnEducator.setBounds(48, 242, 161, 25);
		btnEducator.addActionListener(this);
		getContentPane().add(btnEducator);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == btnLogin) {
			
			long id = 0;
			Connection connection = SQLConnection.getConnection();
			PreparedStatement ps;
			ResultSet rs;
			String query;

			try {
				query = "SELECT id, PESEL, haslo_dostepu FROM uczen WHERE PESEL=? and haslo_dostepu=?";
				ps = connection.prepareStatement(query);
				ps.setString(1, textField.getText());
				ps.setString(2, String.valueOf(passwordField.getPassword()));
				rs = ps.executeQuery();

					if (rs.next()) {
						
						JOptionPane.showMessageDialog(null, "Nast¹pi³o poprawne zalogowanie");
						
						id = (rs.getInt("id"));
						
						PupilPanel pupilPane = new PupilPanel(id);
						pupilPane.setVisible(true);
						pupilPane.setLocationRelativeTo(null);
						pupilPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Nazwa u¿ytkownika lub has³o jest niepoprawne. Spróbuj ponownie!");
					}
				rs.close();
				ps.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
		}
		else if(source == chckbxShowPassword) {
			
			if (chckbxShowPassword.isSelected()) {
				passwordField.setEchoChar((char) 0);
			} else {
				passwordField.setEchoChar('*');
			}
		}
		else if(source == btnTeacher) {
			
			TeacherLogin teacherLog = new TeacherLogin();
			teacherLog.setVisible(true);
			teacherLog.setLocationRelativeTo(null);
			teacherLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		}
		else if(source == btnEducator) {
			
			EducatorLogin educatorLog = new EducatorLogin();
			educatorLog.setVisible(true);
			educatorLog.setLocationRelativeTo(null);
			educatorLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		}
	}
}