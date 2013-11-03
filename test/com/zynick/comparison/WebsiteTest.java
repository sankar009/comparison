package com.zynick.comparison;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.zynick.comparison.sites.*;

/**
 * This doesn't test Website interface, instead it test all the sites and make
 * sure the value returned can be parsed into json.
 * 
 * The only condition is to make sure the value queried exist in the specified site.
 * 
 * @author zynick
 * 
 */
public class WebsiteTest {
    
    public static final String QUERY = "iphone";
    public static final int SIZE = 3;

    @Test
    public void testExpansys() throws Exception {
        List<Item> list = new Expansys().parse(QUERY, SIZE);
        assertTrue(validateUrlAndImg(list));
    }
    
    @Test
    public void testIPMart() throws Exception {
        List<Item> list = new IPMart().parse(QUERY, SIZE);
        assertTrue(validateUrlAndImg(list));
    }
    
    @Test
    public void testLazada() throws Exception {
        List<Item> list = new Lazada().parse(QUERY, SIZE);
        assertTrue(validateUrlAndImg(list));
    }

    @Test
    public void testLelong() throws Exception {
        List<Item> list = new Lelong().parse(QUERY, SIZE);
        assertTrue(validateUrlAndImg(list));
    }

    @Test
    public void testMobileMegamall() throws Exception {
        List<Item> list = new MobileMegamall().parse(QUERY, SIZE);
        assertTrue(validateUrlAndImg(list));
    }

    @Test
    public void testRakuten() throws Exception {
        List<Item> list = new Rakuten().parse(QUERY, SIZE);
        assertTrue(validateUrlAndImg(list));
    }

    @Test
    public void testRetrons() throws Exception {
        List<Item> list = new Retrons().parse(QUERY, SIZE);
        assertTrue(validateUrlAndImg(list));
    }

    @Test
    public void testSengHeng() throws Exception {
        List<Item> list = new SengHeng().parse(QUERY, SIZE);
        assertTrue(validateUrlAndImg(list));
    }

    @Test
    public void testShashinki() throws Exception {
        List<Item> list = new Shashinki().parse(QUERY, SIZE);
        assertTrue(validateUrlAndImg(list));
    }

    @Test
    public void testSuperbuy() throws Exception {
        List<Item> list = new Superbuy().parse(QUERY, SIZE);
        assertTrue(validateUrlAndImg(list));
    }

    @Test
    public void testYouBeli() throws Exception {
        List<Item> list = new YouBeli().parse(QUERY, SIZE);
        assertTrue(validateUrlAndImg(list));
    }

    @Test
    public void testZalora() throws Exception {
        List<Item> list = new Zalora().parse(QUERY, SIZE);
        assertTrue(validateUrlAndImg(list));
    }
    
    
    private static boolean validateUrlAndImg(List<Item> list) {
        if (list.size() != 0) {
            Item item = list.get(0);
            return item.getUrl().startsWith("http")
                    && item.getImg().startsWith("http");
        } else {
            return true; // nothing to validate
        }
    }
}
