package ServerClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4444);
            InputStream streamIn = socket.getInputStream();
            OutputStream streamOut = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(streamIn));
            DataOutputStream writer = new DataOutputStream(streamOut);
            Scanner scanner = new Scanner(System.in)) {
            String input = "";
            do{
                System.out.println("Inserisci un'espressione da risolvere");
                input = scanner.nextLine();
                writer.writeBytes(input + "\n");
                String risposta = reader.readLine();
                System.out.println(risposta);
            }while(input != "EXIT");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
