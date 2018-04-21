
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.IOException;

public class AddTenantForm extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtAge;
	private JTextField txtEmail;
	private JTextField txtBillingAddress;
	private JTextField txtPropertyID;
	private Tenant tenant = new Tenant();

	public AddTenantForm()
	{
		//constructor for Tenant form


		//set up basic window parameters
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Rental Database Management System");
		getContentPane().setLayout(null);

		//------Text boxes/fields
		txtFirstName = new JTextField();
		txtFirstName.setBounds(309, 152, 136, 19);
		getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setBounds(309, 179, 136, 19);
		getContentPane().add(txtLastName);
		txtLastName.setColumns(10);

		txtAge = new JTextField();
		txtAge.setBounds(309, 206, 136, 19);
		getContentPane().add(txtAge);
		txtAge.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(309, 232, 136, 19);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		txtBillingAddress = new JTextField();
		txtBillingAddress.setBounds(309, 259, 136, 19);
		getContentPane().add(txtBillingAddress);
		txtBillingAddress.setColumns(10);

		//------Labels
		JLabel lblAddTenantForm = new JLabel("Add Tenant Form");
		lblAddTenantForm.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAddTenantForm.setBounds(186, 12, 240, 30);
		getContentPane().add(lblAddTenantForm);

		JLabel lblFields = new JLabel("Fields");
		lblFields.setFont(new Font("Dialog", Font.BOLD, 14));
		lblFields.setBounds(235, 125, 66, 15);
		getContentPane().add(lblFields);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(125, 156, 109, 15);
		getContentPane().add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(125, 183, 103, 15);
		getContentPane().add(lblLastName);

		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(125, 210, 66, 15);
		getContentPane().add(lblAge);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(125, 236, 66, 15);
		getContentPane().add(lblEmail);

		JLabel lblBillingAddress = new JLabel("Billing Address");
		lblBillingAddress.setBounds(125, 263, 103, 15);
		getContentPane().add(lblBillingAddress);

		JLabel lblDirections = new JLabel("Please fill in all fields of form before clicking submit.");
		lblDirections.setBounds(104, 72, 375, 27);
		getContentPane().add(lblDirections);

		JLabel lblDescriptionslinetwo = new JLabel("All fields are required for record keeping purposes.");
		lblDescriptionslinetwo.setBounds(114, 98, 360, 15);
		getContentPane().add(lblDescriptionslinetwo);

		JLabel lblPropertyID = new JLabel("Property ID");
		lblPropertyID.setBounds(125, 290, 87, 15);
		getContentPane().add(lblPropertyID);

		txtPropertyID = new JTextField();
		txtPropertyID.setBounds(309, 289, 136, 19);
		getContentPane().add(txtPropertyID);
		txtPropertyID.setColumns(10);
		setSize(600,800);

		//-------Buttons and event listeners
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			//when submit button is pressed get all tenant info and put into tenant object
			public void actionPerformed(ActionEvent arg0) {
				tenant.setFirstName(txtFirstName.getText());
				tenant.setLastName(txtLastName.getText());
				tenant.setAge(Integer.parseInt(txtAge.getText()));
				tenant.setEmailAddress(txtEmail.getText());
				tenant.setBillingAddress(txtBillingAddress.getText());
				tenant.setPropertyID(txtPropertyID.getText());
				try
				{
					Client.sendData(tenant);
					setVisible(false);
				}
				catch (IOException ioException)
				{
					ioException.printStackTrace();
				}
			}
		});
		btnSubmit.setBounds(216, 320, 114, 25);
		getContentPane().add(btnSubmit);

	}

	public Tenant getTenantFromForm()
	{
		//needed for client to access the tenant in this class
		return this.tenant;
	}

}
