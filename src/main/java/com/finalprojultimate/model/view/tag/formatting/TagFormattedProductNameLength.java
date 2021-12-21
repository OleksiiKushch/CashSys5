package com.finalprojultimate.model.view.tag.formatting;

import org.apache.log4j.Logger;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class TagFormattedProductNameLength extends TagSupport {
    private static final Logger logger = Logger.getLogger(TagFormattedProductNameLength.class);

    private String productName;
    private int length;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public int doStartTag() {
        if (productName.length() > length + 1) {
            productName = productName.substring(0, length) + "...";
        }
        try {
            pageContext.getOut().println(productName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
