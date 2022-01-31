import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

// implementing runnable allows every instance of
// the clientHandler class to be ran in a separate thread
public class ClientHandler implements Runnable {

    // keep track of all our clients
    // enables communication/broadcasting to all clients
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    // represents the socket to be passed in from the Server class
    // from instantiating a clientHandler object
    private Socket socket;

    // used to read in messages sent to our client
    private BufferedReader bufferedReader;

    //used to write message out that have been sent from our client
    private BufferedWriter bufferedWriter;

    // client username
    private String clientUserName;

    // ClientHandler constructor, which takes in a socket object
    public ClientHandler(Socket socket){
        try {
            // Instantiating the socket passed in from the server class
            // returned from serverSocket.accept() method
            this.socket = socket;

            // in java char streams end with writer/reader while byte streams
            // end with the word stream. Here we wrap our socket bit stream
            // with an output stream writer to transform stream from bytes to chars
            // then we instantiate a new bufferedWriter.
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // same here as above, we wrap our input stream with an input stream reader
            // and instantiate a new buffered read for the socket endpoint connection instance.
            this.bufferedReader = new BufferedReader(new InputStreamReader((socket.getInputStream())));

            // the first line in the client's class sendMessage method, sends
            // over the username of the client and this line reads it in
            // and assign it as the username used for the chat room identity
            this.clientUserName = bufferedReader.readLine();

            // adds this clientHandler to the clientHandlers list.
            clientHandlers.add(this);

            // announcing new clients' entry into the chat.
            broadcastMessage("SERVER: " + clientUserName + " has entered the chat.");

        } catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
            e.printStackTrace();

        }
    }

    @Override
    public void run(){
        String messageFromClient;

        while (socket.isConnected()){
            try{
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            } catch( IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            try{
                if (!clientHandler.clientUserName.equals(clientUserName)) {
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                e.printStackTrace();

            }
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        broadcastMessage("SERVER: " + clientUserName + " has left the chat.");
    }

    public  void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClientHandler();
        try{
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}










