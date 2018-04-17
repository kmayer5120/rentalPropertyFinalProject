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
          getQueries();
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
  //Throws statement is only temporary
  public void waitForConnection() throws EOFException
  {

  }

  public void getStreams()
  {

  }

  public void getQueries()
  {

  }

  public void closeConnections()
  {

  }

}
