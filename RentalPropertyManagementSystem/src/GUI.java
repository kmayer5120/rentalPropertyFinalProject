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

/*Right now, when the GUI opens, the tenant and property forms open too.
	This isn't ideal right now but it works. There's probably some window
	open method or something that we should use instead of setVisible.
*/

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
	//AddTenantForm tenantForm = new AddTenantForm();
	//AddPropertyForm propertyForm = new AddPropertyForm();

	public GUI()
	{
		//tenantForm.setVisible(true);
		//tenantForm.setVisible(false);
		//propertyForm.setVisible(true);
		//propertyForm.setVisible(false);
		//set up basic window parameters
		getContentPane().setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Rental Database Management System");
		getContentPane().setLayout(null);


		//--------labels
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(12, 31, 88, 23);
		getContentPane().add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(12, 54, 78, 25);
		getContentPane().add(lblLastName);

		JLabel lblQueryResults = new JLabel("Query Results");
		lblQueryResults.setBounds(34, 161, 114, 15);
		lblQueryResults.setFont(new Font("Dialog", Font.BOLD, 14));
		getContentPane().add(lblQueryResults);

		lblConnectionStatus = new JLabel("Connection Status: ");
		lblConnectionStatus.setBounds(321, 3, 148, 15);
		getContentPane().add(lblConnectionStatus);

		lblConnectionIndicator = new JLabel("Disconnected");
		lblConnectionIndicator.setForeground(Color.RED);
		lblConnectionIndicator.setBounds(462, 3, 107, 15);
		getContentPane().add(lblConnectionIndicator);
		setSize(600,800);
		
		JLabel lblDeleteBy = new JLabel("Delete By:");
		lblDeleteBy.setBounds(63, 745, 88, 15);
		getContentPane().add(lblDeleteBy);

		//-------text fields
		txtFirstName = new JTextField();
		txtFirstName.setBounds(101, 33, 124, 19);
		txtFirstName.setText("First Name");
		getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setBounds(101, 57, 124, 19);
		txtLastName.setText("Last Name");
		getContentPane().add(txtLastName);
		txtLastName.setColumns(10);		
		
		txtDeleteByID = new JTextField();
		txtDeleteByID.setBounds(281, 743, 78, 19);
		getContentPane().add(txtDeleteByID);
		txtDeleteByID.setColumns(10);

    /*
		Replaced with JTable, left this here in case we need to revert
		txtQueryResults.setBounds(32, 187, 533, 573);
		txtQueryResults.setFont(new Font("Dialog", Font.PLAIN, 12));
		getContentPane().add(txtQueryResults);
		*/
		//This queryResults needs to be within the ActionEvent for the display Buttons
		//It's here temporarily for testing
		//queryResults = DBManager.select("Tenants");


		JScrollPane scrollPane = new JScrollPane(queryResults);
		scrollPane.setBounds(34, 179, 533, 555);
		scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
		getContentPane().add(scrollPane);

		//--------drop down/combo box
		JComboBox comboBoxDeleteByID = new JComboBox();
	    comboBoxDeleteByID.addItem("Property ID:");
	    comboBoxDeleteByID.addItem("Tenant ID:");
	    //Object comboBoxSelectedItem = comboBoxDeleteByID.getSelectedItem();
		comboBoxDeleteByID.setBounds(155, 740, 114, 24);
		getContentPane().add(comboBoxDeleteByID);



		//--------buttons and event listeners

		/*
		 * Button search will extract the first name and last name from
		 * the text fields on gui and create an sql query to submit to
		 * the database. All results will be displayed to text box named
		 * txtQueryResults.
		 */
		btnSearch = new JButton("Search");
		btnSearch.setBounds(270, 30, 148, 25); //set size

		//event listener for the search button
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO connect query with database
				//get text from fields on gui and store into String for query building
				String lastName = txtLastName.getText();
				String firstName = txtFirstName.getText();
				String query = "SELECT * FROM Tenants where firstName='" + firstName
												+ "' AND lastName='" + lastName + "'";

				try
				{
					//Need to consider what data is being sent, but this is how it's done
					//For some reason, search must be clicked twice...
					Client.sendData(query);
					queryResults = (JTable) Client.serverResponse;

					JScrollPane scrollPane = new JScrollPane(queryResults);
					scrollPane.setBounds(32, 187, 533, 555);
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
		btnAddTenant.setBounds(436, 31, 148, 25); //set size

		//event listener for the add tenant button
		btnAddTenant.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				AddTenantForm tenantForm = new AddTenantForm();
				tenantForm.setVisible(true);
				//give focus to pop up with form to add a tenant to database
			}
		});

		getContentPane().add(btnAddTenant); //add to window


		btnAddProperty = new JButton("Add Property");
		btnAddProperty.setBounds(436, 68, 148, 25); //set size

		//event listener for add property button
		btnAddProperty.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
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
		btnShowAvailable.setBounds(133, 98, 160, 25); //set size

		//event listener for show available button
		btnShowAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO connect query with database

				//clear results txt area first
				txtQueryResults.setText("");
				String query = "SELECT * FROM Properties where isAvailable='T'";
				try
				{
					Client.sendData(query);
					queryResults = (JTable) Client.serverResponse;

					JScrollPane scrollPane = new JScrollPane(queryResults);
					scrollPane.setBounds(32, 187, 533, 555);
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
				//TODO connect query with database

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
					scrollPane.setBounds(32, 187, 533, 555);
					scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
					getContentPane().add(scrollPane);
				} catch (IOException ioException)
				{
					ioException.printStackTrace();
				}
			}
		});
		btnShowLate.setBounds(332, 98, 166, 25);
		getContentPane().add(btnShowLate);


		/*
		 * Buttons show vacation homes, apartments, and homes are coarse
		 * grained controls on the gui just to display every property that
		 * fits into that category.
		 */

		JButton btnShowVacationHomes = new JButton("Show Vacation Homes");
		btnShowVacationHomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO connect query with database

				//clear results txt area first
				txtQueryResults.setText("");
				//assuming that vacation family propertyIDs begin with V
				//TODO May need to adjust query
				String query = "SELECT * FROM Properties WHERE propertyID LIKE 'V%'";
				try
				{
					Client.sendData(query);
					queryResults = (JTable) Client.serverResponse;

					JScrollPane scrollPane = new JScrollPane(queryResults);
					scrollPane.setBounds(32, 187, 533, 555);
					scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
					getContentPane().add(scrollPane);
				} catch (IOException ioException)
				{
					ioException.printStackTrace();
				}
			}
		});
		btnShowVacationHomes.setBounds(371, 135, 193, 25);
		getContentPane().add(btnShowVacationHomes);

		JButton btnShowApartments = new JButton("Show Apartments");
		btnShowApartments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO connect query with database

				//clear results txt area first
				txtQueryResults.setText("");
				//assuming that apartment propertyIDs begin with A
				//TODO verify query is correct
				String query = "SELECT * FROM Properties WHERE propertyID LIKE 'A%'";
				try
				{
					Client.sendData(query);
					queryResults = (JTable) Client.serverResponse;

					JScrollPane scrollPane = new JScrollPane(queryResults);
					scrollPane.setBounds(32, 187, 533, 555);
					scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
					getContentPane().add(scrollPane);
				} catch (IOException ioException)
				{
					ioException.printStackTrace();
				}
			}
		});
		btnShowApartments.setBounds(32, 135, 193, 25);
		getContentPane().add(btnShowApartments);

		JButton btnShowHomes = new JButton("Show Homes");
		btnShowHomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//assuming that single family propertyIDs begin with S
				String query = "SELECT * FROM Properties WHERE propertyID LIKE 'S%'";
				try
				{
					Client.sendData(query);
					queryResults = (JTable) Client.serverResponse;

					JScrollPane scrollPane = new JScrollPane(queryResults);
					scrollPane.setBounds(32, 187, 533, 555);
					scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
					getContentPane().add(scrollPane);
				} catch (IOException ioException)
				{
					ioException.printStackTrace();
				}
			}
		});
		btnShowHomes.setBounds(237, 135, 122, 25);
		getContentPane().add(btnShowHomes);
	
		
		JButton btnDeleterecord = new JButton("Delete Record");
		btnDeleterecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
					tableToDeleteFrom = "PROPERTIES";
					fieldToDelete = "propertyID";
				}
				else
				{
					tableToDeleteFrom = "TENANTS"; 
					fieldToDelete = "tenantID";
				}
				
				String sql = "DELETE FROM " + tableToDeleteFrom + "WHERE " + fieldToDelete + " = '" + recordToDelete + "';";
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
		btnDeleterecord.setBounds(364, 740, 134, 25);
		getContentPane().add(btnDeleterecord);

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
