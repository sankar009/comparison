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

public class Superbuy implements Website {

    @Override
    public List<Item> parse(String query, int size) throws IOException {

        // request for a page
        Document doc = Jsoup.connect("http://www.superbuy.my/shop/search.aspx?SearchTerm=" + query)
                            .userAgent(Constant.HTTP_USER_AGENT) 
                            .timeout(Constant.HTTP_TIMEOUT).get();

        Elements rowS = doc
                .select("div#content > table > tbody > tr > td > table > tbody")
                .first().children();
        rowS.remove(0);

        ArrayList<Item> result = new ArrayList<Item>(size);
        int count = 0;
        loop: for (Element row : rowS) {
            if (count >= size)
                break;

            for (Element col : row.children()) {
                if (count >= size)
                    break loop;

                Element aE = col.select("div.MainImage > a").first();
                String title = aE.attr("title");
                String price = col.select("span.listingBigPriceFont").first().text().substring(3).replaceAll(",", "");
                double dPrice = Double.parseDouble(price);
                String img = aE.child(0).attr("src");
                String url = aE.attr("href");
                url = "http://www.superbuy.my/shop/" + url;

                result.add(new Item("Superbuy", title, dPrice, img, url));
                count++;
            }
        }

        return result;
    }

}
