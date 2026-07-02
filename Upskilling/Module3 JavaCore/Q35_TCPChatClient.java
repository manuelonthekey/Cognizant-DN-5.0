import java.io.*;
import java.net.*;
public class Q35_TCPChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        out.println("Hello from client!");
        String response = in.readLine();
        System.out.println("Server says: " + response);
        
        socket.close();
    }
}