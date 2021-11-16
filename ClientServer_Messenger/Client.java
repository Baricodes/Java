import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.net.Socket;

public class Client{
    public static void main(String[] args) {
        // Instantiating the socket endpoint for communication
        Socket socket = null;
        // Instantiating the input stream
        InputStreamReader inputStreamReader = null;
        // Instantiating the output stream
        OutputStreamWriter outputStreamWriter = null;
        // Reader and Writer buffers to mitigate input and output streams
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        // Using a try catch to run program
        try {
            // Creating new socket object
            socket = new Socket("localhost", 1234);
            // Creating a new inputStreamReader to read input stream from socket
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            // Creating a new outputStreamWriter to write output stream to socket
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            // Wrapping the input and output streams with buffers
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            // Creating a scanner object to receive input
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Creating a String to represent msgToSend
                String msgToSend = scanner.nextLine();
                // Writing message to output stream
                bufferedWriter.write(msgToSend);
                // Writing a new line seperator with msgToSend
                bufferedWriter.newLine();
                // Telling the buffer to flush the buffer
                bufferedWriter.flush();
                // Reading in server's response
                System.out.println("Server: " + bufferedReader.readLine());
                // To intiate exiting program
                if (msgToSend.equalsIgnoreCase("EXIT")){
                    break;
                }
            }
        } catch (IOException e){ 
            e.printStackTrace();
        } finally {
            try {
                // Closing all streams before exiting program
                if (socket != null)
                    socket.close();
                if (inputStreamReader != null)
                    inputStreamReader.close();
                if (outputStreamWriter != null)
                    outputStreamWriter.close();
                if (bufferedReader != null)
                    bufferedReader.close();
                if (bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}