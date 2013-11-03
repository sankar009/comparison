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

// parsing code works as of 2013-07-06
public class Lazada implements Website {

    @Override
    public List<Item> parse(String query, int size) throws IOException {

        // request for a page
        Document doc = Jsoup.connect("http://www.lazada.com.my/catalog/?q=" + query)
                            .userAgent(Constant.HTTP_USER_AGENT) 
                            .timeout(Constant.HTTP_TIMEOUT).get();

        // grab the list
        Elements listS = doc.getElementById("productsCatalog").child(0).getElementsByTag("li");
        size = (size < listS.size()) ? size : listS.size(); // size or listS.size, which ever is smaller (max 4)
        ArrayList<Item> result = new ArrayList<Item>(size);

        for (int i = 0; i < size; i++) {
            Element listE = listS.get(i);
            String title = listE.select("em.itm-title").text().trim();
            Element priceE = listE.getElementsByClass("itm-price").not(".old").get(0);
            String price = priceE.childNodes().get(0).toString().trim().substring(3).replaceAll(",", "");
            double dPrice = Double.parseDouble(price);
            String img = listE.getElementsByTag("img").get(0).attr("src");
            String url = listE.select("a.itm-link").first().attr("href");
            url = "http://www.lazada.com.my" + url;

            result.add(new Item("Lazada", title, dPrice, img, url));
        }

        return result;
    }

}
