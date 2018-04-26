/*
 * All of this needs to be changed to work with properties
 * 		- Polymorphism of properties once Submit button is clicked.
 * 		- Change all field names/etc
 */

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

public class AddPropertyForm extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JTextField txtRentalID;
	private JTextField txtPropertyDescription;
	private JTextField txtMoveInDate;
	private JTextField txtIsAvailable;



	public AddPropertyForm()
	{
		//set up basic window parameters
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Add Property");
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

		txtMoveInDate = new JTextField();
		txtMoveInDate.setBounds(309, 210, 136, 19);
		getContentPane().add(txtMoveInDate);
		txtMoveInDate.setColumns(10);

		txtIsAvailable = new JTextField();
		txtIsAvailable.setBounds(309, 237, 136, 19);
		getContentPane().add(txtIsAvailable);
		txtIsAvailable.setColumns(10);
		setSize(600,370);

		//------Labels
		JLabel lblAddPropertyForm = new JLabel("Add Property Form");
		lblAddPropertyForm.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAddPropertyForm.setBounds(186, 12, 240, 30);
		getContentPane().add(lblAddPropertyForm);

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

		JLabel lblMoveInDate = new JLabel("Move In Date");
		lblMoveInDate.setBounds(125, 214, 109, 15);
		getContentPane().add(lblMoveInDate);

		JLabel lblIsAvailable = new JLabel("Available? (T or F)");
		lblIsAvailable.setBounds(125, 241, 136, 15);
		getContentPane().add(lblIsAvailable);

		JLabel lblDirections = new JLabel("Please fill in all fields of form before clicking submit.");
		lblDirections.setBounds(104, 72, 375, 27);
		getContentPane().add(lblDirections);

		JLabel lblDescriptionslinetwo = new JLabel("All fields are required for record keeping purposes.");
		lblDescriptionslinetwo.setBounds(114, 98, 360, 15);
		getContentPane().add(lblDescriptionslinetwo);


		//-------Buttons and event listeners
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			//when submit button is pressed get all property info and put into property object
			public void actionPerformed(ActionEvent arg0) {
				RentalProperty property = new RentalProperty();
				property.setRentalID(txtRentalID.getText());
				property.setPropertyDescription(txtPropertyDescription.getText());
				property.setIsAvailable(txtIsAvailable.getText());

				try
				{
					Client.sendData(property);
					setVisible(false);
				}
				catch (IOException ioException)
				{
					ioException.printStackTrace();
				}
			}
		});
		btnSubmit.setBounds(222, 290, 114, 25);
		getContentPane().add(btnSubmit);

	}

}
