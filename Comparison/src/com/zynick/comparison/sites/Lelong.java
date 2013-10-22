package com.zynick.comparison.sites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zynick.comparison.Item;

public class Lelong implements Website {
    
    @Override
    public List<Item> parse(String query, int size) throws IOException {

        // request for a page
        Document doc = Jsoup.connect("http://list.lelong.com.my/Auc/List/List.asp?TheKeyword=" + query).get();
        
        Element table = doc.getElementById("ListingTable");
        Elements imgListS = table.getElementsByClass("listimage");
        Elements titleListS = table.getElementsByClass("ListNorm");
        Elements priceListS = table.getElementsByClass("ListPrice");
        if (priceListS.size() == 0)
            priceListS = table.getElementsByClass("ListPriceBlack");
        
        // grab the list
        size = (size < imgListS.size()) ? size : imgListS.size(); // size or listS.size, which ever is smaller
        ArrayList<Item> result = new ArrayList<Item> (size);
        for (int i = 0; i < size; i++) {
            String img = imgListS.get(i).getElementsByTag("img").get(0).attr("data-original");
            img = img.substring(2); // remove "//"
            img = "http://" + img;
            Element aE = titleListS.get(i).getElementsByTag("a").first();
            String title = aE.attr("title");
            String price = priceListS.get(i).text().substring(3).replaceAll(",", "");
            double dPrice = Double.parseDouble(price);
            String url = aE.attr("href"); 
            
            result.add(new Item("lelong", title, dPrice, img, url));
        }
        
        return result;
    }

}
