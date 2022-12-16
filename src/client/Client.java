package client;

import client.sender.Connector;
import client.treads.ReceiveTread;
import client.treads.SendTread;

import java.util.Scanner;

public class Client {
    private final String ip;
    private final int port = 1024;
    private final String clientName;
    private Connector connector;



    public Client() {
        String [] clientNames = new  String[] {"Василий", "Анна", "Петр","Александра"};
        String [] arrIp = new String[] {"127.0.0.1.","255.255.255.255", "192.158.1.38.", "192.168.50.1."};
        clientName = clientNames[(int)(Math.random()*3)];
        this.ip="0.0.0.0";
    }



    public void startClientWork(){
        connector = new Connector(ip,port);
        System.out.println(connector);
        Scanner scanner = new Scanner(System.in);
        SendTread sendTread = new SendTread(scanner,connector,clientName);
        ReceiveTread receiveTread = new ReceiveTread(connector);
        sendTread.start();
        receiveTread.start();
        try {
            sendTread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Client().startClientWork();
    }

}

