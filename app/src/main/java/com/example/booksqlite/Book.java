package com.example.booksqlite;

public class Book {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Book() {
    }

    private String name;

    public Book(int id, String name, String author, String discription) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.discription = discription;
    }
    public Book( String name, String author, String discription) {
        this.name = name;
        this.author = author;
        this.discription = discription;
    }

    private String author;
    private String discription;
}
