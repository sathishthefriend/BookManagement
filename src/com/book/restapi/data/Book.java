package com.book.restapi.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Sathish on 12/29/2017.
 */
@XmlRootElement
public class Book {
    private Integer id;
    private String name;
    private String author;
    private Double price;
    
    public Book() {}
    
    public Book(Integer id, String name, String author, Double price) {	
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
	}

	public Integer getId() {
        return id;
    }

    @XmlElement
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    @XmlElement
    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
