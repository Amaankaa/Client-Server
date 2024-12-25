import java.io.*;
import java.net.*;

public class server {
  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(12345)) { // Port 12345
      System.out.println("Server is ready. Waiting for a client...");
      Socket clientSocket = serverSocket.accept();
      System.out.println("Client connected!");

      BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);
      BufferedReader serverConsoleInput = new BufferedReader(new InputStreamReader(System.in));

      // Chat loop
      String clientMessage = null, serverMessage;
      while (true) {
        // Receive message from the client
        if ((clientMessage = inputFromClient.readLine()) != null) {
          System.out.println("Client: " + clientMessage);
        }

        // Server sends a message
        System.out.print("You: ");
        serverMessage = serverConsoleInput.readLine();
        outputToClient.println(serverMessage);

        if (clientMessage.equalsIgnoreCase("exit")) {
          System.out.println("Client has left the chat. Goodbye!");
          break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
