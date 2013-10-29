package com.zynick.comparison;

import com.zynick.comparison.sites.Lazada;
import com.zynick.comparison.sites.Lelong;
import com.zynick.comparison.sites.Rakuten;
import com.zynick.comparison.sites.Superbuy;
import com.zynick.comparison.sites.Zalora;

public class SearchTest {
    
    public static void main(String[] args) {
        String query = "iphone";
        int size = 5;
        try { System.out.println(new Lazada().parse(query, size)); } catch (Exception e) { e.printStackTrace(); }
        try { System.out.println(new Lelong().parse(query, size)); } catch (Exception e) { e.printStackTrace(); }
        try { System.out.println(new Rakuten().parse(query, size)); } catch (Exception e) { e.printStackTrace(); }
        try { System.out.println(new Superbuy().parse(query, size)); } catch (Exception e) { e.printStackTrace(); }
        try { System.out.println(new Zalora().parse(query, size)); } catch (Exception e) { e.printStackTrace(); }
    }
    
}
