package com.example.ilustra.domain;

import java.io.Serializable;

public class PopularDomain implements Serializable {

    private String title;
    private String picURL;
    private int review;
    private double score;
    private double price;
    private String description;
    private int numberInCart;

    public int getNumberInCart(){
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart){
        this.numberInCart=numberInCart;
    }

    public PopularDomain(String title, String picUrl, int review, double score, double price, String description) {
        this.title = title;
        this.picURL = picUrl;
        this.review = review;
        this.score = score;
        this.price = price;
        this.description = description;
    }

    public String getDescription() {
        return description;

    }

    public void setDescription(String description) {
        this.description = description;

    }

    public String getTitle() {
        return title;

    }

    public void setTitle(String title) {
        this.title = title;

    }

    public String getPicURL() {
        return picURL;

    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;

    }

    public int getReview() {
        return review;

    }

    public void setReview(int review) {
        this.review = review;

    }

    public double getScore() {
        return score;

    }

    public void setScore(double score) {
        this.score = score;

    }

    public double getPrice() {
        return price;
        
    }
}



