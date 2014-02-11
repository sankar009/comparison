package com.zynick.comparison.sites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zynick.comparison.Constant;
import com.zynick.comparison.Item;

public class Zalora implements Website {
    
    public List<Item> parse(String query, int size) throws IOException {
        
        // request for a page
        Document doc = Jsoup.connect("http://www.zalora.com.my/catalog/?q=" + query)
                            .userAgent(Constant.HTTP_USER_AGENT) 
                            .timeout(Constant.HTTP_TIMEOUT).get();
        
        Elements listS = doc.getElementById("productsCatalog").children();
        
        // grab the list
        ArrayList<Item> result = new ArrayList<Item> (size);
        for (int i = 0; i < size; i++) {
            Element listE = listS.get(i);
            String title = listE.getElementsByClass("itm-title").get(0).text().trim();
            String price = listE.getElementsByClass("itm-price").not(" .old").text().substring(3).replaceAll(",", "");
            double dPrice = Double.parseDouble(price);
            String img = listE.select("span.itm-imageWrapper img").first().attr("data-src");
            String url = listE.select("a.itm-link").first().attr("href");
            url = "http://www.zalora.com.my" + url;
            
            result.add(new Item("Zalora", title, dPrice, img, url));
        }
        
        return result;
    }

}
