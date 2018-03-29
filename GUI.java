import java.awt.BorderLayout;
import javax.swing.*;

public class GUI extends JFrame
{
    //create window
    JPanel panel = new JPanel();
    //create buttons
    JButton btnCreateRecord = new JButton("Create Record");
    JButton btnUpdateRecord = new JButton("Update Record");
    JButton btnDeleteRecord = new JButton("Delete Record");
    //create labels
    JLabel lblProgramTitle = new JLabel("Property Rental Management System");
    JLabel lblCurrentRecord = new JLabel("Current Record: ");
    JLabel lblOptions = new JLabel("Record Types");
    //just using this for an example. String tenant would be pulled from the db
    String tenant = "John Smith Apartment 2B";
    JLabel lblTenantInfo = new JLabel(tenant);
    

    //create array with options for combo box
    String[] options = {
        "Tenants",
        "Properties",
    };
    //create combo box
    JComboBox dropdown = new JComboBox(options);

        public static void main(String[] args)
    {
        new GUI();
    }


    public GUI()
    {
        super("Rental Management System");
        setSize(640, 480);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //add items to window
        panel.add(lblProgramTitle);
        panel.add(btnCreateRecord);
        panel.add(btnUpdateRecord);
        panel.add(btnDeleteRecord);
        panel.add(lblOptions);
        panel.add(dropdown);
        panel.add(lblCurrentRecord);
        panel.add(lblTenantInfo);
        add(panel);
        setVisible(true);
    }
}
