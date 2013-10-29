package com.zynick.comparison;

import org.json.simple.JSONObject;

public class Item {

    private String source;
    private String title;
    private double price;
    private String img;
    private String link;

    public Item(String source, String title, double price, String img, String link) {
        this.source = source;
        this.title = title;
        this.price = price;
        this.img = img;
        this.link = link;
    }

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
    
    public String getImg() {
        return img;
    }
    
    public String getLink() {
        return link;
    }

    /**
     * Output to JSON format
     */
    @Override
    public String toString() {
        return new StringBuilder().append("{")
                .append("\"source\":\"").append(JSONObject.escape(source)).append("\",")
                .append("\"title\":\"").append(JSONObject.escape(title)).append("\",")
                .append("\"price\":").append(price).append(",")
                .append("\"img\":\"").append(JSONObject.escape(img)).append("\",")
                .append("\"link\":\"").append(JSONObject.escape(link)).append("\"}")
                .toString();
    }
}
