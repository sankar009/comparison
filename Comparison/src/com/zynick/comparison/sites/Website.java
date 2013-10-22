package com.zynick.comparison.sites;

import java.io.IOException;
import java.util.List;

import com.zynick.comparison.Item;

public interface Website {
    
    public List<Item> parse(String query, int size) throws IOException;
    
}