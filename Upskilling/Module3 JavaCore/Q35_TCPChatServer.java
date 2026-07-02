import java.io.*;
import java.net.*;
public class Q35_TCPChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started. Waiting for client...");
        Socket socket = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Client connected.");
        
        String msg = in.readLine();
        System.out.println("Client says: " + msg);
        out.println("Hello from server!");
        
        socket.close();
        serverSocket.close();
    }
}