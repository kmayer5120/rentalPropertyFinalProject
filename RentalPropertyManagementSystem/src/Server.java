import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/*Need to move the sqlite-jdbc-3.21.0.jar to the src folder
  When running Server and Client, the terminal command NEEDS to be:
  java -classpath ".:sqlite-jdbc-3.21.0.jar" Server
  java -classpath ".:sqlite-jdbc-3.21.0.jar" Client
*/
public class Server
{

  private ServerSocket server;
  private Socket connection;
  private ObjectOutputStream output;
  private ObjectInputStream input;

  public static void main(String[] args)
  {
    Server application = new Server();
    application.runServer();
  }

  public void runServer()
  {
    try
    {
      server = new ServerSocket(12345, 100);
      while(true)
      {
        try
        {
          waitForConnection();
          getStreams();
          getData();
        }
        catch(EOFException eofException)
        {
          System.out.println("Connection terminated.");
        }
        finally
        {
          closeConnections();
        }
      }
    }
    catch(IOException ioException)
    {
      ioException.printStackTrace();
    }
  }
  //Establishes connection with client
  private void waitForConnection() throws IOException
  {
    System.out.println("Waiting for connection...");
    connection = server.accept();
    System.out.println("Connection received from " +
                      connection.getInetAddress().getHostName());
  }
  //Gets input and output streams
  private void getStreams() throws IOException
  {
    output = new ObjectOutputStream(connection.getOutputStream());
    output.flush();
    input = new ObjectInputStream(connection.getInputStream());
  }

  public void getData() throws IOException
  {
    do
    {
      try
      {
        Object data =  input.readObject();
        if (data instanceof Tenant)
        {
          HashMap<String,String> map = SqlRental.createHash((Tenant) data);
          DBManager.insert("Tenants", map);
        }
        else if (data instanceof RentalProperty)
        {
          HashMap<String,String> map = SqlRental.createHash((RentalProperty) data);
          DBManager.insert("Properties", map);
        }
        else if (data instanceof String && ((String) data).startsWith("S"))
        {
          //select from db
          output.writeObject(DBManager.select((String) data));
        }
        else if (data instanceof String && ((String) data).startsWith("D"))
        {
          //delete from db
          output.writeObject(DBManager.delete((String) data));
        }
        else if(data instanceof String && ((String) data).startsWith("U"))
        {
          //update db with new values
          output.writeObject(DBManager.update((String) data));
        }
        else
        {
          output.writeObject("Error here.");
        }
      }
      catch (ClassNotFoundException classNotFoundException)
      {
        System.out.println("Unknown object received.");
      }
    } while (true);
  }

  //Closes all streams and sockets
  private void closeConnections()
  {
    System.out.println("Connections are being terminated...");
    try
    {
      output.close();
      input.close();
      connection.close();
    }
    catch (IOException ioException)
    {
      ioException.printStackTrace();
    }
  }

}
