import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.IOException;

public class UpdatePropertyForm extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JTextField txtRentalID;
	private JTextField txtPropertyDescription;
	private JTextField txtRentalType;
	private JTextField txtMoveInDate;
	private JTextField txtIsAvailable;
	private JTextField txtPropertyID;



	public UpdatePropertyForm()
	{

		//constructor for Property form

		//set up basic window parameters
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Update Property");
		getContentPane().setLayout(null);

		//------Text boxes/fields
		txtRentalID = new JTextField();
		txtRentalID.setBounds(309, 152, 136, 19);
		getContentPane().add(txtRentalID);
		txtRentalID.setColumns(10);

		txtPropertyDescription = new JTextField();
		txtPropertyDescription.setBounds(309, 179, 136, 19);
		getContentPane().add(txtPropertyDescription);
		txtPropertyDescription.setColumns(10);

		txtIsAvailable = new JTextField();
		txtIsAvailable.setBounds(309, 208, 136, 19);
		getContentPane().add(txtIsAvailable);
		txtIsAvailable.setColumns(10);


		//------Labels
		JLabel lblUpdatePropertyForm = new JLabel("Update Property Form");
		lblUpdatePropertyForm.setFont(new Font("Dialog", Font.BOLD, 18));
		lblUpdatePropertyForm.setBounds(186, 12, 240, 30);
		getContentPane().add(lblUpdatePropertyForm);

		JLabel lblFields = new JLabel("Fields");
		lblFields.setFont(new Font("Dialog", Font.BOLD, 14));
		lblFields.setBounds(235, 125, 66, 15);
		getContentPane().add(lblFields);

		JLabel lblRentalID = new JLabel("Rental ID");
		lblRentalID.setBounds(125, 156, 109, 15);
		getContentPane().add(lblRentalID);

		JLabel lblPropertyDescription = new JLabel("Description");
		lblPropertyDescription.setBounds(125, 183, 103, 15);
		getContentPane().add(lblPropertyDescription);

		JLabel lblIsAvailable = new JLabel("Available? (T or F)");
		lblIsAvailable.setBounds(125, 210, 144, 15);
		getContentPane().add(lblIsAvailable);

		JLabel lblDirections = new JLabel("Please fill in all fields of form before clicking update.\n");
		lblDirections.setBounds(104, 72, 375, 27);
		getContentPane().add(lblDirections);

		JLabel lblDescriptionslinetwo = new JLabel("All fields are required for record keeping purposes.");
		lblDescriptionslinetwo.setBounds(114, 98, 360, 15);
		getContentPane().add(lblDescriptionslinetwo);

		setSize(600,339);


		//-------Buttons and event listeners
		JButton btnUpdate = new JButton("Update Record");
		btnUpdate.addActionListener(new ActionListener() {
			//when submit button is pressed get all property info and put into property object
			public void actionPerformed(ActionEvent arg0) {
				RentalProperty property = new RentalProperty();
				property.setRentalID(txtRentalID.getText());
				property.setPropertyDescription(txtPropertyDescription.getText());
				property.setIsAvailable(txtIsAvailable.getText());
				String updateQuery = "UPDATE Properties SET ";

				updateQuery += "isAvailable = " + "'" + property.getIsAvailable() + "',";
				updateQuery += "propertyDescription = " + "'" + property.getPropertyDescription() + "' ";
				updateQuery += "WHERE propertyID = '" + property.getRentalID() + "'";

				try
				{
					//send String updateQuery with Client to Server
					Client.sendData(updateQuery);
					setVisible(false);
				}
				catch (IOException ioException)
				{
					ioException.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(220, 257, 144, 25);
		getContentPane().add(btnUpdate);

	}

}
