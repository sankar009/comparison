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
public class Shashinki implements Website {
    
    @Override
    public List<Item> parse(String query, int size) throws IOException {
        
        // request for a page
        Document doc = Jsoup.connect("http://shashinki.com/shop/advanced_search_result.php?keywords=" + query)
                            .userAgent(Constant.HTTP_USER_AGENT) 
                            .timeout(Constant.HTTP_TIMEOUT).get();

        Elements rowS = doc.select("table.productListing > tbody ").first().children();
        rowS.remove(0);
        
        ArrayList<Item> result = new ArrayList<Item>(size);
        for (int i = 0; i < size && i < rowS.size(); i++) {
            Element row = rowS.get(i);
            Element aE = row.child(0).child(0).child(0).child(0).child(0).child(0);  // "tr > td > table > tbody > tr > td > a"
            String url = aE.attr("href");
            Element imgE = aE.child(0);
            String img = "http://shashinki.com/shop/" + imgE.attr("src");
            String title = imgE.attr("title");
            
            Element priceE = row.child(2);
            String price = (priceE.children().size() > 0) 
                            ? priceE.child(1).text() 
                            : priceE.text();
            price = price.substring(price.indexOf('M') + 1).replaceAll(",", "")
                    .replaceAll("\u00A0", "");  // this is tricky, to remove &nbsp; cause trim() doesn't work
            double dPrice = Double.parseDouble(price);
            
            result.add(new Item("Shashinki", title, dPrice, img, url));
        }

        return result;
    }
}
