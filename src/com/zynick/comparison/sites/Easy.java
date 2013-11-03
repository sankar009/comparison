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

// parsing code works as of 2013-11-03
public class Easy implements Website {
    
    @Override
    public List<Item> parse(String query, int size) throws IOException {
        
        // request for a page
        Document doc = Jsoup.connect("http://www.easy.my/search.html?q=" + query)
                            .userAgent(Constant.HTTP_USER_AGENT) 
                            .timeout(Constant.HTTP_TIMEOUT).get();

        ArrayList<Item> result = new ArrayList<Item>(size);
        
        Elements itemS = doc.select("div#category-maindiv > ul > li");
        
        for (int i = 0; i < size && i < itemS.size(); i++) {
            Element aE = itemS.get(i).child(0);
            String url = "http://www.easy.my" + aE.attr("href");
            String img = aE.child(0).attr("data-original");
            aE = itemS.get(i).child(1);
            String price = aE.child(0).html();
            if (price.indexOf('<') != -1)
                price = price.substring(0, price.indexOf(' '));
            price = price.substring(2);
            double dPrice = Double.parseDouble(price);
            String title = aE.child(1).text();
            
            result.add(new Item("Easy", title, dPrice, img, url));
        }

        return result;
    }
}
