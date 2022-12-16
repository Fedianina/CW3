package common;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private String name;
    private String text;
    private LocalDateTime dateTime;

    private Connection<Message> sender;

    public Message(String name, String text, Connection connection) {
        this.name = name;
        this.text = text;
        this.sender=connection;
        dateTime = LocalDateTime.now();
    }

    public Connection<Message> getSender() {
        return sender;
    }

    public void setSender(Connection<Message> sender) {
        this.sender = sender;
    }

    public String getText() {
        return "["+dateTime.toString()+"]"+name+": "+text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateTime() {
        dateTime = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return "SimpleMessage{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

}