package com.zynick.util;

import java.text.DecimalFormat;

/**
 * Singleton
 * @author zynick
 */
public enum DecimalFormatter {
    INSTANCE;
    
    DecimalFormat df = new DecimalFormat("0.00");
    
    public String format(double value) {
        return df.format(value);
    }
}
