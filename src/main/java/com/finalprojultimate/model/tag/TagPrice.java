package com.finalprojultimate.model.tag;

import com.finalprojultimate.model.entity.product.Unit;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TagPrice extends TagSupport {
    private static final Logger logger = Logger.getLogger(TagPrice.class);

    private BigDecimal price;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().println(price.setScale(2, RoundingMode.DOWN));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
