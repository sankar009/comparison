package com.zynick.comparison;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import com.zynick.comparison.sites.Lazada;
import com.zynick.comparison.sites.Lelong;
import com.zynick.comparison.sites.Mudah;
import com.zynick.comparison.sites.Rakuten;
import com.zynick.comparison.sites.Superbuy;
import com.zynick.comparison.sites.Website;
import com.zynick.comparison.sites.Zalora;

// parsing code works as of 2013-07-06
public class Search extends HttpServlet {

    private static final long serialVersionUID = -6785660763248229508L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // get parameters
        String query = request.getParameter("query");
        String sizeStr = request.getParameter("size");
        String source = request.getParameter("source");
        String callback = request.getParameter("callback");
        
        // prepare list
        List<Item> result = new ArrayList<Item>();
        
        // get website
        Website site = getSite(source);
        
        if (site != null) {
            try {
                query = URLEncoder.encode(query, "UTF-8");
                int size = Integer.parseInt(sizeStr);
                
                // get result
                result = site.parse(query, size);
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        // response back
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        String resp = callback + "(" + result.toString() + ")";
        out.println(resp);
        out.flush();
    }
    
    private static final Website getSite(String source) {
        if ("lazada".equals(source)) {
            return new Lazada();
        } else if ("lelong".equals(source)) {
            return new Lelong();
        } else if ("rakuten".equals(source)) {
            return new Rakuten();
        } else if ("superbuy".equals(source)) {
            return new Superbuy();
        } else if ("zalora".equals(source)) {
            return new Zalora();
        } else {
            return null;
        }
    }
    
    public static void main(String[] args) {
        String query = "iphone";
        int size = 5;
        try { System.out.println(new Lazada().parse(query, size)); } catch (Exception e) { e.printStackTrace(); }
        try { System.out.println(new Lelong().parse(query, size)); } catch (Exception e) { e.printStackTrace(); }
//        try { System.out.println(new Mudah().parse(query, size)); } catch (Exception e) { e.printStackTrace(); }
        try { System.out.println(new Rakuten().parse(query, size)); } catch (Exception e) { e.printStackTrace(); }
        try { System.out.println(new Superbuy().parse(query, size)); } catch (Exception e) { e.printStackTrace(); }
        try { System.out.println(new Zalora().parse(query, size)); } catch (Exception e) { e.printStackTrace(); }
    }
}


