import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.InetAddress;
import javax.swing.SwingUtilities;

public class Client
{

	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	private String serverIP;
	public static Object clientResponse;
	public static Object serverResponse;
	private Socket connection;
	private static boolean isConnected = false;
	public static GUI mainGui = new GUI();

	public Client(String host)
	{
		//super("Client");
		serverIP = host;

	}

	public static void main(String[] args)
	{
		//Forces the database to trigger.
		//May be able to remove. Test this out. Also try removing/renaming db file
		//To make sure that removing this is okay.
		DBManager db = new DBManager();
		db.createDB();
		db.createTable();

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
		System.out.println("In runClient");
		try
		{
			connect();
			getStreams();
			readServer();
		}
		catch (EOFException eofException)
		{
			System.out.println("Connection terminated by client.");

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
		//set indicator on gui
		isConnected = true;
		mainGui.setConnectionIndicator(isConnected);
	}

	private void getStreams() throws IOException
	{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
	}

	public static void readServer() throws IOException
	{
		do
		{
			try
			{
				//Right now, serverResponse is "Writing..." in Server.java
				serverResponse = input.readObject();
				//Need to make this a variable dependent on which display button is clicked
				//mainGui.queryDisplay(DBManager.select("Tenants"));
				//System.out.println(serverResponse);
			}
			catch (ClassNotFoundException classNotFoundException)
			{
				System.out.println("Error reading from server.");
			}

		} while (!serverResponse.equals("Exit"));
	}

	public static void sendData(Tenant data) throws IOException
	{
		output.writeObject(data);
		output.flush();
	}
	public static void sendData(RentalProperty data) throws IOException
	{
		output.writeObject(data);
		output.flush();
	}
	public static void sendData(String data) throws IOException
	{
		output.writeObject(data);
		output.flush();
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
