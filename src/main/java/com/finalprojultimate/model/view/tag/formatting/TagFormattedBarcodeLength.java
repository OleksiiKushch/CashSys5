package com.finalprojultimate.model.view.tag.formatting;

import org.apache.log4j.Logger;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class TagFormattedBarcodeLength extends TagSupport {
    private static final Logger logger = Logger.getLogger(TagFormattedBarcodeLength.class);

    private String barcode;

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public int doStartTag() {
        if (barcode.length() > 16) {
            barcode = barcode.substring(0, 13) + "...";
        }
        try {
            pageContext.getOut().println(barcode);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
