package ru.itis.multithreading.waitnotify.messagebroker.model;

public class Message {
    private String data;

    public Message(String string) {
        this.data = string;
    }

    public String getData() {
        return data;
    }
}
