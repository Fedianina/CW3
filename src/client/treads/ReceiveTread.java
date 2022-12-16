package client.treads;

import client.sender.Connector;

import javax.imageio.IIOException;
import java.net.Socket;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ReceiveTread extends Thread{
    private final Connector connector;
    public ReceiveTread(Connector connector){
        this.connector=connector;
    }
    @Override
    public void run(){
        while (true){
            System.out.println(connector.receiveMessage().getText());
        }
    }
}
