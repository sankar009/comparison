package com.zynick.comparison;

import org.json.simple.JSONObject;

public class Item {

    private String source;
    private String title;
    private double price;
    private String img;
    private String url;

    public Item(String source, String title, double price, String img, String url) {
        this.source = source;
        this.title = title;
        this.price = price;
        this.img = img;
        this.url = url;
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
    
    public String getUrl() {
        return url;
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
                .append("\"url\":\"").append(JSONObject.escape(url)).append("\"}")
                .toString();
    }
}
