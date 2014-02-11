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

public class SengHeng implements Website {
    
    public List<Item> parse(String query, int size) throws IOException {
        
        // request for a page
        Document doc = Jsoup.connect("http://www.senheng.com.my/search?search_query=" + query)
                            .userAgent(Constant.HTTP_USER_AGENT) 
                            .timeout(Constant.HTTP_TIMEOUT).get();

        ArrayList<Item> result = new ArrayList<Item>(size);
        int count = 0;
        
        Elements listS = doc.select("li.ajax_block_product");
        for (Element list : listS) {
            if (count >= size)
                break;
            
            Element aE = list.child(1).child(0); // "div.center_block > a"
            String url = aE.attr("href");
            String title = aE.attr("title");
            String img = aE.child(0).attr("src");
            String price = list.select("span.price").first().text().substring(2).replaceAll(",", "");
            double dPrice = Double.parseDouble(price);
            
            result.add(new Item("SengHeng", title, dPrice, img, url));
            count++;
        }
        
        return result;
    }
}
