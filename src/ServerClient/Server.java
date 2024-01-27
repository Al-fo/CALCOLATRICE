package ServerClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static boolean close = false;
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(4444)){
            ServerThread serverThread;
            do{
                Socket socket = serverSocket.accept();
                serverThread = new ServerThread(socket);
                serverThread.start();
            }while(!close);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
