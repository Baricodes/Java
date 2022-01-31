import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Server class responsible for listening for clients
// that want to connect and when they do it will spawn
// a new thread to handle them.
public class Server {
    // Responsible for listening for incoming connections from
    // clients and in response creating a new socket object
    // to service them.
    private ServerSocket serverSocket;

    // The server constructor with the serverSocket obj as
    // a pass in parameter.
    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    // method responsible for starting server, keeping it running and listening for connections
    public void startServer() {
        // using a try catch block to handle the potential IOExceptions when working with sockets
        try {
            // while server socket is open/server is running
            while (!serverSocket.isClosed()) {
                // ServerSocket.accept() is a blocking method, meaning the program will be
                // halted here until a connection has been made.
                // once a connection has been made the socket object returned
                // is used so the server can communicate with the client
                //  using the returned socket. Socket - a communication endpoint.q
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");

                // Instantiating a clientHandler with the socket
                // returned from the accept method
                ClientHandler clientHandler = new ClientHandler(socket);

                // Starting a new thread for each new connection made
                // clientHandler implements which qualifies it to be
                // able to run in a thread, running what ever is
                // implemented in it Overridden run() method
                Thread thread = new Thread(clientHandler);
                thread.start();
            }

        } catch (IOException e){
            closeServerSocket();
            e.printStackTrace();
        }
    }

    // method used to close connections
    // to avoid nested try catch blocks.
    public void closeServerSocket(){

        try{
            // checking if = or != to null to
            // avoid null pointer exception
            if (serverSocket != null){
                serverSocket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

            // Instantiating serverSocket with port #
            ServerSocket serverSocket = new ServerSocket(1234);
            // Using server class constructor to instantiate server object
            Server server = new Server(serverSocket);
            // Using start server method to wait for connections to be made
            server.startServer();

    }
}
