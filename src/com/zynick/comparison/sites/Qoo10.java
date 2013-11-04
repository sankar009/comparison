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

public class Qoo10 implements Website {
    
    @Override
    public List<Item> parse(String query, int size) throws IOException {
        
        // request for a page
        Document doc = Jsoup.connect("http://list.qoo10.my/s/s?keyword=" + query)
                            .userAgent(Constant.HTTP_USER_AGENT) 
                            .timeout(Constant.HTTP_TIMEOUT).get();

        ArrayList<Item> result = new ArrayList<Item>(size);
        
        Elements itemS = doc.select("dl#search_result_item_list > dd");
        for (int i = 0; i < size && i < itemS.size(); i++) {
            Element trE = itemS.get(i).child(2).child(1).child(0);  // "dl > dd > table > tbody > tr"
            Element aE = trE.child(0).child(0).child(0);  // "tr > td.thumb > div.thumb_area > a"
            String url = aE.attr("href");
            String title = aE.attr("title");
            String img = aE.child(0).attr("gd_src");
            
            Element priceE = trE.child(2);  // "tr > td.price"
            String price = priceE.getElementsByTag("strong").first().text();  // "td > strong" for discounted price
            if (price.length() == 0)
                price = priceE.getElementsByTag("em").first().text();  // "td > span.dc > em"
            price = price.substring(2).replaceAll(",", "");
            double dPrice = Double.parseDouble(price);
            
            result.add(new Item("Qoo10", title, dPrice, img, url));
        }
        
        return result;
    }
}
