import java.io.*;
import java.net.*;

public class Ex35_TCPServer {
    public static void main(String[] args) throws IOException {
        int port = 5000;
        System.out.println("Server started. Waiting for client on port " + port + "...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            BufferedReader  in  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter     out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader  kbd = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while (true) {
             
                message = in.readLine();
                if (message == null || message.equalsIgnoreCase("bye")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                System.out.println("Client: " + message);

             
                System.out.print("Server: ");
                String reply = kbd.readLine();
                out.println(reply);
                if (reply.equalsIgnoreCase("bye")) break;
            }
        }
    }
}