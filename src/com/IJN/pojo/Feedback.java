package com.IJN.pojo;

public class Feedback {
    public int id;
    public String username;
    public String content;

    public Feedback(){}

    public Feedback(String username, String content) {
        this.username = username;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", content='\r\n" + content + '\'' +
                "\r\n}\r\n";
    }
}
