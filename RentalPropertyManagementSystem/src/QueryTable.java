import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class QueryTable
{
  public JTable QueryTable()
  {
    //These values are test values, need to be connected to DBManager.select()
    String[][] data =
    {
      {"Austin", "Smith", "26"},
      {"Steve", "Jobs", "97"},
      {"Michael", "Cera", "33"}
    };
    String columns[] = {"First Name", "Last Name", "Age"};
    JTable table = new JTable(data, columns);
    return table;
  }
}
