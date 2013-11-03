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
public class MobileMegamall implements Website {
    
    @Override
    public List<Item> parse(String query, int size) throws IOException {
        
        // request for a page
        Document doc = Jsoup.connect("http://mobilemegamall.com/index.php?app=search&keyword=" + query)
                            .userAgent(Constant.HTTP_USER_AGENT) 
                            .timeout(Constant.HTTP_TIMEOUT).get();

        Elements rowS = doc
                .select("ul.list_pic")
                .first().children();

        ArrayList<Item> result = new ArrayList<Item>(size);
        for (int i = 0; i < size && i < rowS.size() - 1; i++) {
            Element row = rowS.get(i);
            Element aE = row.child(0).child(0); // "div.product > a"
            String url = aE.attr("href");
            url = "http://mobilemegamall.com/" + url;
            Element imgE = aE.child(0);
            String img = imgE.attr("src");
            img = "http://mobilemegamall.com/" + img;
            
            Element aTitleE = row.child(1).child(0).child(0).child(0); // "h3 > span.text_link > span.depict > a"
            String title = aTitleE.text();
            Element pE = row.child(1).child(1); // "h3 > span.price"
            String price = pE.html();
            price = price.substring(0, price.indexOf('<')).substring(3).trim().replaceAll(",", "");
            double dPrice = Double.parseDouble(price);
            
            result.add(new Item("MobileMegamall", title, dPrice, img, url));
        }
        
        return result;
    }
}
