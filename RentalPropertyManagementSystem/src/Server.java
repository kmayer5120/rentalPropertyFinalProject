import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
  //Make getTenant and getRental instead
  public void getData() throws IOException
  {
    do
    {
      try
      {
        //Test value, to be modified
        Object query =  input.readObject();
        if (query instanceof Tenant)
        {
          System.out.println("Tenant");
        }
        else if (query instanceof RentalProperty)
        {
          System.out.println("Rental Property");
        }
      }
      catch (ClassNotFoundException classNotFoundException)
      {
        System.out.println("Unknown object received.");
      }
      //Probably change this end message.
    } while (true);
    //return query;
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
