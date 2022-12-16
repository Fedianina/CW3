package client.sender;

import common.Connection;
import common.Message;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.Socket;

public class Connector {
    private Connection<Message> connection;
    private final String ip;
    private final int port;
    public Connector(String ip, int port){
        this.ip=ip;
        this.port=port;
        try {
            connection= new Connection<>(new Socket(ip, port));

        } catch (IIOException  e){
            System.out.println("IO, Class");
        }catch (Exception e){
            System.out.println("Сервер недоступен, попробуйте подключится позднее");
            throw new RuntimeException("Сервер не найден");
        }
    }
    public void sendNewMessage(String clientName, String messageText){
        try {
            Message message = new Message(clientName,messageText,null);
            System.out.println("Сообщение сформировано"+connection);
            connection.sendMessage(message);
        } catch (IOException e) {
            System.out.println("Connection error: cannot send the message");
        }
    }
    public Message receiveMessage(){
        try {
            return connection.readMessage();
        } catch (IIOException | ClassNotFoundException e) {
            System.out.println("обработкаIIOException и ClassNotFoundException");
        } catch (Exception e) {
            System.out.println("Обработка Exception");
        }
        return null;
    }

    @Override
    public String toString() {
        return "Connector{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
