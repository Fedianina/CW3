package server.treads;

import common.Connection;
import common.Message;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class SendingStream extends Thread{

    private ArrayBlockingQueue<Message> messages;
    private CopyOnWriteArrayList<Connection> connectionsCollect;

    public SendingStream (ArrayBlockingQueue<Message> messages, CopyOnWriteArrayList<Connection> connectionsCollect){
        this.messages = messages;
        this.connectionsCollect = connectionsCollect;
    }


    @Override
    public void run(){

        while (!Thread.currentThread().isInterrupted()){
            Message message;
            try {
                message = messages.take();
                Connection sender=message.getSender();
                message.setSender(null);
                System.out.println(message.getText()+" "+connectionsCollect.get(0));
                connectionsCollect.forEach((connection)->{
                    if (!connection.equals(sender)){
                        try {
                            connection.sendMessage(message);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

