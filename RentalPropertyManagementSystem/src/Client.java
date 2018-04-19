import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.InetAddress;

public class Client
{

	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String serverIP;
	private String serverResponse;
	private Socket connection;

	public Client(String host)
	{
		//super("Client");
		serverIP = host;
	}

	public static void main(String[] args)
	{
		//instantiate main gui form
		GUI mainGui = new GUI();
		mainGui.setVisible(true);

		Client application;
		if (args.length == 0)
		{
			application = new Client("127.0.0.1");
		}
		else
		{
			application = new Client(args[0]);
		}
		application.runClient();

	}
	public void runClient()
	{
		try
		{
			connect();
			getStreams();
			readServer();
		}
		catch (EOFException eofException)
		{
			System.out.println("Connection terminated");
		}
		catch (IOException ioException)
		{
			ioException.printStackTrace();
		}
		finally
		{
			closeConnections();
		}
	}

	private void connect() throws IOException
	{
		System.out.println("Trying to connect to server...");
		connection = new Socket(InetAddress.getByName(serverIP), 12345);
		System.out.println("Connection successful!");
	}

	private void getStreams() throws IOException
	{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
	}

	private void readServer() throws IOException
	{
		do
		{
			try
			{
				serverResponse = (String) input.readObject();
				System.out.println(serverResponse);
			}
			catch (ClassNotFoundException classNotFoundException)
			{
				System.out.println("Error reading from server.");
			}
		} while (!serverResponse.equals("Exit"));
	}

	private void closeConnections()
	{
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
