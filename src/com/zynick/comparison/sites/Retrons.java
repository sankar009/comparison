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
public class Retrons implements Website {
    
    @Override
    public List<Item> parse(String query, int size) throws IOException {
        
        // request for a page
        Document doc = Jsoup.connect("http://www.retrons.com/index.php?route=product/search&keyword=" + query)
                            .userAgent(Constant.HTTP_USER_AGENT) 
                            .timeout(Constant.HTTP_TIMEOUT).get();

        ArrayList<Item> result = new ArrayList<Item>(size);
        int count = 0;
        
        Elements rowS = doc.select("table.list > tbody").first().children();
        for (Element row : rowS) {
            if (count >= size)
                break;
            
            for (Element col : row.children()) {
                if (count >= size)
                    break;
                
                Element trE = col.child(0).child(0).child(0);  // "td > table > tbody > tr"
                Element aE = trE.child(1).child(0);
                String url = aE.attr("href");
                Element imgE = aE.child(0);
                String img = imgE.attr("src");
                String title = imgE.attr("title");
                
                String price = trE.child(0).child(0).child(0).text();  // "td > div.pricetag > span"
                price = price.substring(3).replaceAll(",", "");
                double dPrice = Double.parseDouble(price);
                
                result.add(new Item("Retrons", title, dPrice, img, url));
                count++;
            }
        }
        
        return result;
    }
}
