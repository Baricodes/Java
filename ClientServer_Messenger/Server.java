import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        // Instantiating the socket endpoint for communication
        Socket socket = null;
        // Instantiating the input stream
        InputStreamReader inputStreamReader = null;
        // Instantiating the output stream
        OutputStreamWriter outputStreamWriter = null;
        // Reader and Writer buffers to mitigate input and output streams
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = null;
        // Initiating a serverSocket object to listen for connections
        serverSocket = new ServerSocket(1234);

        while (true) {

            try {
                // Having the server socket object wait/listen for a connection
                socket = serverSocket.accept();
                // Creating a new inputStreamReader to read input stream from socket
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                // Creating a new outputStreamWriter to write output stream to socket
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                // Wrapping the input and output streams with buffers
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while (true) {
                    // Reading in message from client program
                    String msgFromClient = bufferedReader.readLine();
                    // Printing out the client's message
                    System.out.println("Client: " + msgFromClient);
                    // Writing msg to client that it's message was received
                    bufferedWriter.write("MSG Received");
                    bufferedWriter.newLine();
                    // Flushing the buffer to send the message
                    bufferedWriter.flush();
                    // To intiate exiting program
                    if (msgFromClient.equalsIgnoreCase("EXIT"))
                        break;
                }
                // Closing all streams before exiting program
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
                System.exit(0);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
