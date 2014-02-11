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

public class Expansys implements Website {
    
    public List<Item> parse(String query, int size) throws IOException {
        
        // request for a page
        Document doc = Jsoup.connect("http://www.expansys.my/s.aspx?search=" + query)
                            .userAgent(Constant.HTTP_USER_AGENT) 
                            .timeout(Constant.HTTP_TIMEOUT).get();

        Elements rowS = doc
                .select("div#product_listing > table > tbody")
                .first().children();

        ArrayList<Item> result = new ArrayList<Item>(size);
        
        int count = 0;
        for (Element row : rowS) {
            if (count >= size)
                break;
            
            Elements cols = row.children();
            if (cols.size() < 4) // ignore assume it's preorder item (doesn't have td.price)
                continue;
            
            Element aE = cols.get(0).child(0); // "td.image > a"
            String url = aE.attr("href");
            url = "http://www.expansys.my" + url;
            Element imgE = aE.child(0);
            String img = imgE.attr("src");
            String title = imgE.attr("alt");
            String price = cols.get(3).text().substring(3).replaceAll(",", "");
            double dPrice = Double.parseDouble(price);
            
            result.add(new Item("Expansys", title, dPrice, img, url));
            count++;
        }
        
        return result;
    }
}
