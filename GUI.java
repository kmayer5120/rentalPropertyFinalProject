import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	
	public GUI() 
	{
		getContentPane().setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Rental Database Management System");
		getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(12, 12, 88, 23);
		getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(12, 35, 78, 25);
		getContentPane().add(lblLastName);
		
		txtFirstName = new JTextField();
		txtFirstName.setText("First Name");
		txtFirstName.setBounds(101, 14, 124, 19);
		getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setText("Last Name");
		txtLastName.setBounds(101, 38, 124, 19);
		getContentPane().add(txtLastName);
		txtLastName.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(270, 11, 148, 25);
		getContentPane().add(btnSearch);
		
		btnAddTenant = new JButton("Add Tenant");
		btnAddTenant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//give focus to pop up with form to add a tenant to database
			}
		});
		btnAddTenant.setBounds(436, 12, 148, 25);
		getContentPane().add(btnAddTenant);
		
		btnAddProperty = new JButton("Add Property");
		btnAddProperty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//give focus to pop up with form to add a property to database
			}
		});
		btnAddProperty.setBounds(436, 49, 148, 25);
		getContentPane().add(btnAddProperty);
		
		btnShowAvailable = new JButton("Show Available");
		btnShowAvailable.setBounds(133, 97, 160, 25);
		getContentPane().add(btnShowAvailable);
		
		btnShowLate = new JButton("Show Late");
		btnShowLate.setBounds(332, 97, 166, 25);
		getContentPane().add(btnShowLate);
		
		JTextArea txtQueryResults = new JTextArea();
		txtQueryResults.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtQueryResults.setBounds(32, 187, 533, 573);
		getContentPane().add(txtQueryResults);
		
		JLabel lblQueryResults = new JLabel("Query Results");
		lblQueryResults.setFont(new Font("Dialog", Font.BOLD, 14));
		lblQueryResults.setBounds(32, 170, 114, 15);
		getContentPane().add(lblQueryResults);
		
		JButton btnShowVacationHomes = new JButton("Show Vacation Homes");
		btnShowVacationHomes.setBounds(371, 134, 193, 25);
		getContentPane().add(btnShowVacationHomes);
		
		JButton btnShowApartments = new JButton("Show Apartments");
		btnShowApartments.setBounds(32, 134, 193, 25);
		getContentPane().add(btnShowApartments);
		
		JButton btnShowHomes = new JButton("Show Homes");
		btnShowHomes.setBounds(237, 134, 122, 25);
		getContentPane().add(btnShowHomes);
		setSize(600,800);
	}


	
	public static void main(String[] args)
	{
		GUI gui = new GUI();
		gui.setVisible(true);
	}
}
