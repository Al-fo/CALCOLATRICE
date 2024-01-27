package ServerClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import ClasseCalcolatrice.Calculator;

public class ServerThread extends Thread{
    Socket socket;
    
    public ServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try(InputStream streamIn = socket.getInputStream();
            OutputStream streamOut = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(streamIn));
            DataOutputStream writer = new DataOutputStream(streamOut)){
                String espressione = "";
                do{
                    espressione = reader.readLine();
                    try{
                        double soluzione = Calculator.evaluate(espressione);
                        writer.writeBytes("Soluzione = " + soluzione + "\n");
                    }catch(Exception e){
                        if(!"EXIT".equalsIgnoreCase(espressione))   writer.writeBytes("Errore nel calcolo dell'espressione\n");
                        else{
                            writer.writeBytes("Chiudo la connessione");
                            Server.close = true;
                        }
                    }
                }while(!"EXIT".equalsIgnoreCase(espressione));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
