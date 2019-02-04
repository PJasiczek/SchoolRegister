package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import Users.Educator;

public class EducatorDataModification extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldPesel;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldAddress;
	private JTextField textFieldMotherName;
	private JTextField textFieldFatherName;
	private JTextField textFieldContactNumber;
	private JTextField textFieldSex;
	private JTextField textFieldDateOfBirth;
	private JButton okButton;
	private JCheckBox chckbxChangePassword;
	private long pupilId;
	Educator educator;

	public EducatorDataModification(JFrame owner, Educator educator_1, long pupilId) {
		super(owner, "Dane", true);
		this.pupilId = pupilId;
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		setTitle("Dane");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 417, 427);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		{
			JLabel lblPesel = new JLabel("PESEL:", JLabel.RIGHT);
			lblPesel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblPesel.setBounds(53, 18, 68, 16);
			contentPanel.add(lblPesel);
		}
		{
			JLabel lblFirstName = new JLabel("Imi\u0119:", JLabel.RIGHT);
			lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblFirstName.setBounds(53, 47, 68, 16);
			contentPanel.add(lblFirstName);
		}
		{
			JLabel lblLastName = new JLabel("Nazwisko:", JLabel.RIGHT);
			lblLastName.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblLastName.setBounds(53, 76, 68, 16);
			contentPanel.add(lblLastName);
		}
		{
			JLabel lblContactNumber = new JLabel("Adres zam:", JLabel.RIGHT);
			lblContactNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblContactNumber.setBounds(30, 105, 91, 16);
			contentPanel.add(lblContactNumber);
		}
		{
			JLabel lblMotherName = new JLabel("Imie matki:", JLabel.RIGHT);
			lblMotherName.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblMotherName.setBounds(40, 134, 81, 16);
			contentPanel.add(lblMotherName);
		}
		{
			JLabel lblDateOfBirth = new JLabel("Imie ojca:", JLabel.RIGHT);
			lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblDateOfBirth.setBounds(30, 163, 91, 16);
			contentPanel.add(lblDateOfBirth);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(166, 305, 59, 35);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}

		JLabel lblContactName = new JLabel("Nr. kont:", JLabel.RIGHT);
		lblContactName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblContactName.setBounds(40, 192, 81, 16);
		contentPanel.add(lblContactName);

		JLabel lblSex = new JLabel("P\u0142e\u0107:");
		lblSex.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSex.setBounds(89, 221, 56, 16);
		contentPanel.add(lblSex);

		JLabel lblDateOfBirth = new JLabel("Data urodz:");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateOfBirth.setBounds(44, 251, 81, 16);
		contentPanel.add(lblDateOfBirth);

		JButton btnChangePassword = new JButton("Zmie\u0144 has\u0142o");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EducatorChangePassword changePassword = new EducatorChangePassword(null, educator, pupilId);
				changePassword.setVisible(true);
				changePassword.setLocationRelativeTo(null);
			}
		});
		btnChangePassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnChangePassword.setBounds(269, 342, 113, 25);
		contentPanel.add(btnChangePassword);

		textFieldPesel = new JTextField();
		textFieldPesel.setEditable(false);
		textFieldPesel.setBounds(133, 13, 165, 27);
		contentPanel.add(textFieldPesel);
		textFieldPesel.setColumns(10);

		textFieldFirstName = new JTextField();
		textFieldFirstName.setEditable(false);
		textFieldFirstName.setColumns(10);
		textFieldFirstName.setBounds(133, 42, 165, 27);
		contentPanel.add(textFieldFirstName);

		textFieldLastName = new JTextField();
		textFieldLastName.setEditable(false);
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(133, 71, 165, 27);
		contentPanel.add(textFieldLastName);

		textFieldAddress = new JTextField();
		textFieldAddress.setEditable(false);
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(133, 100, 165, 27);
		contentPanel.add(textFieldAddress);

		textFieldMotherName = new JTextField();
		textFieldMotherName.setEditable(false);
		textFieldMotherName.setColumns(10);
		textFieldMotherName.setBounds(133, 128, 165, 27);
		contentPanel.add(textFieldMotherName);

		textFieldFatherName = new JTextField();
		textFieldFatherName.setEditable(false);
		textFieldFatherName.setColumns(10);
		textFieldFatherName.setBounds(133, 157, 165, 27);
		contentPanel.add(textFieldFatherName);

		textFieldContactNumber = new JTextField();
		textFieldContactNumber.setEditable(false);
		textFieldContactNumber.setColumns(10);
		textFieldContactNumber.setBounds(133, 187, 165, 27);
		contentPanel.add(textFieldContactNumber);

		textFieldSex = new JTextField();
		textFieldSex.setEditable(false);
		textFieldSex.setBounds(133, 216, 165, 27);
		contentPanel.add(textFieldSex);
		textFieldSex.setColumns(10);

		textFieldDateOfBirth = new JTextField();
		textFieldDateOfBirth.setEditable(false);
		textFieldDateOfBirth.setBounds(133, 246, 165, 27);
		contentPanel.add(textFieldDateOfBirth);
		textFieldDateOfBirth.setColumns(10);

		chckbxChangePassword = new JCheckBox("Zmie\u0144 dane");
		chckbxChangePassword.addActionListener(this);
		chckbxChangePassword.setBounds(269, 282, 113, 35);
		contentPanel.add(chckbxChangePassword);
		
		try {
			
			String query = "SELECT * FROM uczen WHERE id=?";
			Connection connection = SQLConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, pupilId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				textFieldPesel.setText((rs.getString("PESEL")));
				textFieldFirstName.setText((rs.getString("imie")));
				textFieldLastName.setText((rs.getString("nazwisko")));
				textFieldAddress.setText((rs.getString("adres_zamieszkania")));
				textFieldMotherName.setText((rs.getString("imie_matki")));
				textFieldFatherName.setText((rs.getString("imie_ojca")));
				textFieldContactNumber.setText((rs.getString("nr_kontaktowy")));
				textFieldSex.setText((rs.getString("plec")));
				textFieldDateOfBirth.setText((rs.getString("data_urodzenia")));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		educator = educator_1;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (chckbxChangePassword.isSelected()) {
			
			textFieldPesel.setEditable(true);
			textFieldFirstName.setEditable(true);
			textFieldLastName.setEditable(true);
			textFieldAddress.setEditable(true);
			textFieldMotherName.setEditable(true);
			textFieldFatherName.setEditable(true);
			textFieldContactNumber.setEditable(true);
			textFieldSex.setEditable(true);
			textFieldDateOfBirth.setEditable(true);

			String s1 = textFieldPesel.getText();
			String s2 = textFieldFirstName.getText();
			String s3 = textFieldFirstName.getText();
			String s4 = textFieldAddress.getText();
			String s5 = textFieldMotherName.getText();
			String s6 = textFieldFatherName.getText();
			String s7 = textFieldContactNumber.getText();
			String s8 = textFieldSex.getText();
			String s9 = textFieldDateOfBirth.getText();

			if (source == okButton) {
				
				educator.pupilModification(pupilId, s1, s2, s3, s4, s5, s6, s7, s8, s9);
				dispose();
			}

		} else {
			
			textFieldPesel.setEditable(false);
			textFieldFirstName.setEditable(false);
			textFieldLastName.setEditable(false);
			textFieldAddress.setEditable(false);
			textFieldMotherName.setEditable(false);
			textFieldFatherName.setEditable(false);
			textFieldContactNumber.setEditable(false);
			textFieldSex.setEditable(false);
			textFieldDateOfBirth.setEditable(false);

			if (source == okButton) {
				
				dispose();
			}
		}
		
	}

}