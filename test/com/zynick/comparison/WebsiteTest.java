package com.zynick.comparison;

import org.junit.Test;

import com.zynick.comparison.sites.Lazada;
import com.zynick.comparison.sites.Lelong;
import com.zynick.comparison.sites.Rakuten;
import com.zynick.comparison.sites.Superbuy;
import com.zynick.comparison.sites.Zalora;

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

    /**
     * As long as it doesn't throw exception, the test is consider success.
     * 
     * No Assertion is required :p
     */
    @Test
    public void testLazada() throws Exception {
        new Lazada().parse("iphone", 5);
    }

    @Test
    public void testLelong() throws Exception {
        new Lelong().parse("iphone", 5);
    }

    @Test
    public void testRakuten() throws Exception {
        new Rakuten().parse("iphone", 5);
    }

    @Test
    public void testSuperbuy() throws Exception {
        new Superbuy().parse("iphone", 5);
    }

    @Test
    public void testZalora() throws Exception {
        new Zalora().parse("iphone", 5);
    }
    
    
    
    

}
