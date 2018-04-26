import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.SwingUtilities;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;


public class GUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JButton btnSearch;
	private JButton btnAddTenant;
	private JButton btnAddProperty;
	private JButton btnShowAvailable;
	private JButton btnShowLate;
	private JTable queryResults;
	private JLabel lblConnectionIndicator;
	private JLabel lblConnectionStatus;
	JTextArea txtQueryResults = new JTextArea();
	private JTextField txtDeleteByID;

	public GUI()
	{
		//set up basic window parameters
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Rental Database Management System");
		getContentPane().setLayout(null);


		//--------labels
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(56, 28, 88, 23);
		getContentPane().add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(56, 51, 78, 25);
		getContentPane().add(lblLastName);

		JLabel lblQueryResults = new JLabel("Query Results");
		lblQueryResults.setBounds(30, 160, 114, 15);
		lblQueryResults.setFont(new Font("Dialog", Font.BOLD, 14));
		getContentPane().add(lblQueryResults);

		lblConnectionStatus = new JLabel("Connection Status: ");
		lblConnectionStatus.setBounds(736, 0, 148, 15);
		getContentPane().add(lblConnectionStatus);

		lblConnectionIndicator = new JLabel("Disconnected");
		lblConnectionIndicator.setForeground(Color.RED);
		lblConnectionIndicator.setBounds(877, 0, 107, 15);
		getContentPane().add(lblConnectionIndicator);
		setSize(1000,800);

		JLabel lblDeleteBy = new JLabel("Delete By:");
		lblDeleteBy.setBounds(311, 740, 88, 15);
		getContentPane().add(lblDeleteBy);

		//-------text fields
		txtFirstName = new JTextField();
		txtFirstName.setBounds(162, 30, 124, 19);
		txtFirstName.setText("First Name");
		getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setBounds(162, 54, 124, 19);
		txtLastName.setText("Last Name");
		getContentPane().add(txtLastName);
		txtLastName.setColumns(10);

		txtDeleteByID = new JTextField();
		txtDeleteByID.setBounds(516, 738, 78, 19);
		getContentPane().add(txtDeleteByID);
		txtDeleteByID.setColumns(10);

		JLabel lblSearchBy = new JLabel("Search by:");
		lblSearchBy.setBounds(56, 12, 88, 15);
		getContentPane().add(lblSearchBy);


		JScrollPane scrollPane = new JScrollPane(queryResults);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane.setBounds(30, 176, 934, 552);
		scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
		getContentPane().add(scrollPane);

		//--------drop down/combo box
		JComboBox<String> comboBoxDeleteByID = new JComboBox<String>();
	    comboBoxDeleteByID.addItem("Property ID:");
	    comboBoxDeleteByID.addItem("Tenant ID:");
		comboBoxDeleteByID.setBounds(390, 735, 114, 24);
		getContentPane().add(comboBoxDeleteByID);



		//--------buttons and event listeners

		btnSearch = new JButton("Search");
		btnSearch.setBounds(162, 78, 124, 25); //set size

		//event listener for the search button
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//------SEARCH
				//get text from fields on gui and store into String for query building
				String lastName = txtLastName.getText();
				String firstName = txtFirstName.getText();
				String query = "SELECT * FROM Tenants where firstName='" + firstName
												+ "' AND lastName='" + lastName + "'";

				try
				{
					Client.sendData(query);
					queryResults = (JTable) Client.serverResponse;

					JScrollPane scrollPane = new JScrollPane(queryResults);
					scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
					getContentPane().add(scrollPane);
				}
				catch (IOException ioException)
				{
					ioException.printStackTrace();
				}
			}
		});
		getContentPane().add(btnSearch); //add to window


		btnAddTenant = new JButton("Add Tenant");
		btnAddTenant.setBounds(590, 51, 148, 25); //set size

		//event listener for the add tenant button
		btnAddTenant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//----ADD TENANT
				AddTenantForm tenantForm = new AddTenantForm();
				tenantForm.setVisible(true);
				//give focus to pop up with form to add a tenant to database
			}
		});

		getContentPane().add(btnAddTenant); //add to window


		btnAddProperty = new JButton("Add Property");
		btnAddProperty.setBounds(760, 51, 148, 25); //set size

		//event listener for add property button
		btnAddProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//-----ADD PROPERTY
				AddPropertyForm propertyForm = new AddPropertyForm();
				propertyForm.setVisible(true);
				//give focus to pop up with form to add a property to database
			}
		});

		getContentPane().add(btnAddProperty); //add to window


		/*
		 * Button show available will submit an sql query to the database
		 * and display all properties that are not currently being leased
		 * by tenants.
		 */
		btnShowAvailable = new JButton("Show Available");
		btnShowAvailable.setBounds(30, 127, 148, 25); //set size

		//event listener for show available button
		btnShowAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//----SHOW AVAILABLE
				//clear results txt area first
				txtQueryResults.setText("");
				String query = "SELECT * FROM Properties where isAvailable='T'";
				try
				{
					Client.sendData(query);
					queryResults = (JTable) Client.serverResponse;

					JScrollPane scrollPane = new JScrollPane(queryResults);
					scrollPane.setBounds(30, 176, 934, 552);
					scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
					getContentPane().add(scrollPane);
				} catch (IOException ioException)
				{
					ioException.printStackTrace();
				}
			}
		});

		getContentPane().add(btnShowAvailable);


		/*
		 * Button show late will submit an sql query to the database
		 * and display all properties that have not been paid on time.
		 * This will also include tenant info.
		 */
		btnShowLate = new JButton("Show Late");
		btnShowLate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//----SHOW LATE
				//clear results txt area first
				txtQueryResults.setText("");
				String query = "SELECT * FROM Properties WHERE isLate='T'";
				//TODO send letter with LateHandler object
				//LateHandler lateHandler = new LateHandler(tenant, property);
				//lateHandler.writeLetter();
				try
				{
					Client.sendData(query);
					queryResults = (JTable) Client.serverResponse;

					JScrollPane scrollPane = new JScrollPane(queryResults);
					scrollPane.setBounds(30, 176, 934, 552);
					scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
					getContentPane().add(scrollPane);
				} catch (IOException ioException)
				{
					ioException.printStackTrace();
				}
			}
		});
		btnShowLate.setBounds(183, 127, 114, 25);
		getContentPane().add(btnShowLate);


		/*
		 * Buttons show vacation homes, apartments, and homes are coarse
		 * grained controls on the gui just to display every property that
		 * fits into that category.
		 */

		JButton btnShowVacationHomes = new JButton("Show Vacation Homes");
		btnShowVacationHomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//----SHOW VACATION HOMES
				//clear results txt area first
				txtQueryResults.setText("");
				//assuming that vacation family propertyIDs begin with V
				String query = "SELECT * FROM Properties WHERE propertyID LIKE 'V%'";
				try
				{
					Client.sendData(query);
					queryResults = (JTable) Client.serverResponse;

					JScrollPane scrollPane = new JScrollPane(queryResults);
					scrollPane.setBounds(30, 176, 934, 552);
					scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
					getContentPane().add(scrollPane);
				} catch (IOException ioException)
				{
					ioException.printStackTrace();
				}
			}
		});
		btnShowVacationHomes.setBounds(724, 127, 184, 25);
		getContentPane().add(btnShowVacationHomes);

		JButton btnShowApartments = new JButton("Show Apartments");
		btnShowApartments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//----SHOW APARTMENTS
				//clear results txt area first
				txtQueryResults.setText("");
				//assuming that apartment propertyIDs begin with A
				String query = "SELECT * FROM Properties WHERE propertyID LIKE 'A%'";
				try
				{
					Client.sendData(query);
					queryResults = (JTable) Client.serverResponse;

					JScrollPane scrollPane = new JScrollPane(queryResults);
					scrollPane.setBounds(30, 176, 934, 552);
					scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
					getContentPane().add(scrollPane);
				} catch (IOException ioException)
				{
					ioException.printStackTrace();
				}
			}
		});
		btnShowApartments.setBounds(438, 127, 156, 25);
		getContentPane().add(btnShowApartments);

		JButton btnShowHomes = new JButton("Show Homes");
		btnShowHomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//----SHOW HOMES
				//assuming that single family propertyIDs begin with S
				String query = "SELECT * FROM Properties WHERE propertyID LIKE 'S%'";
				try
				{
					Client.sendData(query);
					queryResults = (JTable) Client.serverResponse;

					JScrollPane scrollPane = new JScrollPane(queryResults);
					scrollPane.setBounds(30, 176, 934, 552);
					scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
					getContentPane().add(scrollPane);
				} catch (IOException ioException)
				{
					ioException.printStackTrace();
				}
			}
		});
		btnShowHomes.setBounds(600, 127, 122, 25);
		getContentPane().add(btnShowHomes);


		JButton btnDeleterecord = new JButton("Delete Record");
		btnDeleterecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//----DELETE
				//get selection from combo box
				Object comboBoxSelectedItem = comboBoxDeleteByID.getSelectedItem();
				//get which type to delete by propertyID or tenantID and put into String
				String typeToDelete = (String)comboBoxSelectedItem;
				//get text from txt box. This is the ID chosen for deletion
				String recordToDelete = txtDeleteByID.getText();
				String tableToDeleteFrom;
				String fieldToDelete;
				if(typeToDelete.equals("PropertyID: "))
				{
					tableToDeleteFrom = "Properties";
					fieldToDelete = "rentalID";
				}
				else
				{
					tableToDeleteFrom = "Tenants";
					fieldToDelete = "tenantID";
				}

				String sql = "DELETE FROM " + tableToDeleteFrom + " WHERE " + fieldToDelete + "='" + recordToDelete + "';";
				try
				{
					Client.sendData(sql);
				} catch (IOException ioException)
				{
					System.out.println("Error: Unable to delete record from database.");
					ioException.printStackTrace();
				}
			}
		});
		btnDeleterecord.setBounds(606, 735, 134, 25);
		getContentPane().add(btnDeleterecord);

		//update tenant button
		JButton btnUpdateTenant = new JButton("Update Tenant");
		btnUpdateTenant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateTenantForm updateTenantForm = new UpdateTenantForm();
				updateTenantForm.setVisible(true);
				//give focus to pop up with form to property to database
			}
		});
		btnUpdateTenant.setBounds(590, 90, 148, 25);
		getContentPane().add(btnUpdateTenant);

		//update property button
		JButton btnUpdateProperty = new JButton("Update Property");
		btnUpdateProperty.setBounds(760, 90, 148, 25);
		getContentPane().add(btnUpdateProperty);

		//show tenants button
		JButton btnShowTenants = new JButton("Show Tenants");
		btnShowTenants.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "SELECT * FROM Tenants";
				try
				{
					Client.sendData(query);
					queryResults = (JTable) Client.serverResponse;

					JScrollPane scrollPane = new JScrollPane(queryResults);
					scrollPane.setBounds(30, 176, 934, 552);
					scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
					getContentPane().add(scrollPane);
				} catch (IOException ioException)
				{
					ioException.printStackTrace();
				}
			}
		});
		btnShowTenants.setBounds(302, 127, 134, 25);
		getContentPane().add(btnShowTenants);

		btnUpdateProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdatePropertyForm updatePropertyForm = new UpdatePropertyForm();
				updatePropertyForm.setVisible(true);
				//give focus to pop up with form to property to database
			}
		});


	} //end constructor


	public void setConnectionIndicator(boolean isConnected)
	{
		//method used in Client.java to set connection indicator
		if(isConnected == true)
		{
			this.lblConnectionIndicator.setText("Connected");
			this.lblConnectionIndicator.setForeground(Color.GREEN);
		}
		else
		{
			this.lblConnectionIndicator = new JLabel("Disconnected");
			this.lblConnectionIndicator.setForeground(Color.RED);
		}
	}

	public void queryDisplay(String query)
	{
		SwingUtilities.invokeLater(
			new Runnable()
			{
				public void run()
				{
					txtQueryResults.append(query);
				}
			}
		);
	}
}
