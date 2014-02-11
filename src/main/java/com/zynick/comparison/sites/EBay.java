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

public class EBay implements Website {
    
    public List<Item> parse(String query, int size) throws IOException {
        
        // request for a page
        Document doc = Jsoup.connect("http://www.ebay.com.my/sch/i.html?LH_BIN=1&_nkw=" + query)
                            .userAgent(Constant.HTTP_USER_AGENT) 
                            .timeout(Constant.HTTP_TIMEOUT).get();

        ArrayList<Item> result = new ArrayList<Item>(size);
        
        Elements itemS = doc.select("div#ResultSetItems > table");
        for (int i = 0; i < size && i < itemS.size(); i++) {
            Element item = itemS.get(i).child(0).child(0);  // "table > tbody > tr"

            Element aE = item.child(0).child(0).child(0).child(0).child(1);  // "tr > td > div > div.picW > div > a"
            String url = aE.attr("href");
            String img = aE.child(0).attr("src");
            String title = aE.child(0).attr("alt");
            
            String price = item.child(3).child(0).child(0).text();  // "tr > td > div > div"
            price = price.substring(price.lastIndexOf(' '));
            double dPrice = Double.parseDouble(price);
            
            result.add(new Item("EBay", title, dPrice, img, url));
        }

        return result;
    }
}
