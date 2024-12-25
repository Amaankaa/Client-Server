import java.io.*;
import java.net.*;

public class client {
  public static void main(String[] args) {
    try (Socket socket = new Socket("localhost", 12345)) { // Replace "localhost" with server IP
      System.out.println("Connected to the server!");

      BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter outputToServer = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader clientConsoleInput = new BufferedReader(new InputStreamReader(System.in));

      // Chat loop
      String serverMessage = null, clientMessage;
      while (serverMessage != "exit") {
        // Client sends a message
        System.out.print("You: ");
        clientMessage = clientConsoleInput.readLine();
        outputToServer.println(clientMessage);

        // Receive message from the server
        if ((serverMessage = inputFromServer.readLine()) != null) {
          System.out.println("Server: " + serverMessage);
        }

        if (serverMessage.equalsIgnoreCase("exit")) {
          System.out.println("Server has left the chat. Goodbye!");
          break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
