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

public class Lelong implements Website {
    
    @Override
    public List<Item> parse(String query, int size) throws IOException {

        // request for a page
        Document doc = Jsoup.connect("http://list.lelong.com.my/Auc/List/List.asp?TheKeyword=" + query)
                            .userAgent(Constant.HTTP_USER_AGENT) 
                            .timeout(Constant.HTTP_TIMEOUT).get();
        
        ArrayList<Item> result = new ArrayList<Item> (size);
        
        Elements rowS = doc.select("div#divProductListing > table > tbody > tr");
        int count = 0;
        
        for (Element row : rowS) {
            if (count >= size)
                break;
            
            if (row.children().size() < 6)
                continue;  // not a valid item row
            
            String price = row.child(2).child(0).text();
            if (!price.startsWith("RM"))
                continue;  // item no price displayed
            
            price = price.substring(price.indexOf(' ') + 1).replaceAll(",", "");
            double dPrice = Double.parseDouble(price);
            
            Element aE = row.child(0).child(0).child(0);  // "tr > td > div.listimage > a
            String url = aE.attr("href");
            String title = aE.attr("title");
            String img = "http:" + aE.child(0).attr("data-original");
            
            result.add(new Item("Lelong", title, dPrice, img, url));
            count++;
        }

        return result;
    }
}
