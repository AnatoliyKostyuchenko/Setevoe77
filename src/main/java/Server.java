import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server= new ServerSocket(8189);
        while(true){
            Socket socket = server.accept();
            System.out.println("New client...");
            new Thread(new Xran(socket)).start();
        }
    }
}
