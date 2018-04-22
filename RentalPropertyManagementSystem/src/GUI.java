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
	//AddTenantForm tenantForm = new AddTenantForm();
	AddPropertyForm propertyForm = new AddPropertyForm();

	public GUI()
	{
		//tenantForm.setVisible(true);
		//tenantForm.setVisible(false);
		propertyForm.setVisible(true);
		propertyForm.setVisible(false);
		//set up basic window parameters
		getContentPane().setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Rental Database Management System");
		getContentPane().setLayout(null);


		//--------labels
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(12, 12, 88, 23);
		getContentPane().add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(12, 35, 78, 25);
		getContentPane().add(lblLastName);

		JLabel lblQueryResults = new JLabel("Query Results");
		lblQueryResults.setBounds(32, 170, 114, 15);
		lblQueryResults.setFont(new Font("Dialog", Font.BOLD, 14));
		getContentPane().add(lblQueryResults);

		lblConnectionStatus = new JLabel("Connection Status: ");
		lblConnectionStatus.setBounds(34, 745, 148, 15);
		getContentPane().add(lblConnectionStatus);

		lblConnectionIndicator = new JLabel("Disconnected");
		lblConnectionIndicator.setForeground(Color.RED);
		lblConnectionIndicator.setBounds(175, 745, 107, 15);
		getContentPane().add(lblConnectionIndicator);
		setSize(600,800);


		//-------text fields
		txtFirstName = new JTextField();
		txtFirstName.setBounds(101, 14, 124, 19);
		txtFirstName.setText("First Name");
		getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setBounds(101, 38, 124, 19);
		txtLastName.setText("Last Name");
		getContentPane().add(txtLastName);
		txtLastName.setColumns(10);

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
		scrollPane.setBounds(32, 187, 533, 555);
		scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
		getContentPane().add(scrollPane);




		//--------buttons and event listeners

		/*
		 * Button search will extract the first name and last name from
		 * the text fields on gui and create an sql query to submit to
		 * the database. All results will be displayed to text box named
		 * txtQueryResults.
		 */
		btnSearch = new JButton("Search");
		btnSearch.setBounds(270, 11, 148, 25); //set size

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
		btnAddTenant.setBounds(436, 12, 148, 25); //set size

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
		btnAddProperty.setBounds(436, 49, 148, 25); //set size

		//event listener for add property button
		btnAddProperty.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
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
		btnShowAvailable.setBounds(133, 97, 160, 25); //set size

		//event listener for show available button
		btnShowAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO connect query with database

				//clear results txt area first
				txtQueryResults.setText("");
				String query = "SELECT * FROM Properties WHERE isAvailable = T ";
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
				String query = "SELECT * FROM Properties WHERE isLate = T ";
				}
			});
		btnShowLate.setBounds(332, 97, 166, 25);
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
				String query = "SELECT * FROM Properties WHERE propertyID LIKE V% ";
			}
		});
		btnShowVacationHomes.setBounds(371, 134, 193, 25);
		getContentPane().add(btnShowVacationHomes);

		JButton btnShowApartments = new JButton("Show Apartments");
		btnShowApartments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO connect query with database

				//clear results txt area first
				txtQueryResults.setText("");
				//assuming that apartment propertyIDs begin with A
				String query = "SELECT * FROM Properties WHERE propertyID LIKE A% ";
			}
		});
		btnShowApartments.setBounds(32, 134, 193, 25);
		getContentPane().add(btnShowApartments);

		JButton btnShowHomes = new JButton("Show Homes");
		btnShowHomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO connect query with database
				//assuming that single family propertyIDs begin with S
				String query = "SELECT * FROM Properties WHERE propertyID LIKE S% ";
			}
		});
		btnShowHomes.setBounds(237, 134, 122, 25);
		getContentPane().add(btnShowHomes);

		}


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
