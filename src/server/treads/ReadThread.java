package server.treads;

import common.Connection;
import common.Message;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

public class ReadThread extends Thread{

    private ArrayBlockingQueue<Message> messages;
    private Connection <Message> connection;

    public ReadThread (ArrayBlockingQueue<Message> messages, Connection <Message> connection){
        this.messages = messages;
        this.connection = connection;
    }


    @Override
    public void run(){

        while (!Thread.currentThread().isInterrupted()){

            try {
                Message fromClient = connection.readMessage();
                fromClient.setSender(connection);
                messages.put(fromClient);
                System.out.println(fromClient.getText());
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
