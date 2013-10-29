package com.zynick.comparison.sites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zynick.comparison.Item;

//parsing code works as of 2013-07-06
public class Mudah implements Website {
    
    /**
     * Mudah is not standardized, result will be messy if crawl them
     */
    @Override
    public List<Item> parse(String query, int size) throws IOException {

        // request for a page
        Document doc = Jsoup.connect("http://www.mudah.my/li?q=" + query)
                .timeout(10*1000).get();
        
        Elements listS = doc.select("div.listing_thumbs").first().select("div.list_ads");
        
        ArrayList<Item> result = new ArrayList<Item> (size);
        for (int i = 0; i < listS.size(); i++) {
            Element list = listS.get(i);
            
            String img = "";
            list.select("div.image_thumb");
            Elements imgS = list.select("div.image_thumb > a + img");
            if (imgS.size() < 0) { // some may not have images
                img = imgS.first().attr("href");
            }
            
            Element listE = list.select("li.listing_ads_title").first();
            String title = listE.child(0).text();
            String link = listE.child(0).attr("href");
            String price = listE.text();
            price = price.substring(price.lastIndexOf("RM") + 2).trim().replaceAll(" ", "");
            int dPrice = Integer.parseInt(price);
            
            result.add(new Item("mudah", title, dPrice, img, link));
        }
        
        return result;
    }

}
