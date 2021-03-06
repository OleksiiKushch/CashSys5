package com.finalprojultimate.model.view.tag.processing;

import org.apache.log4j.Logger;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TagTotalSum extends TagSupport {
    private static final Logger logger = Logger.getLogger(TagTotalSum.class);

    private BigDecimal price;
    private BigDecimal amount;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public int doStartTag() {
        BigDecimal result = price.multiply(amount).setScale(2, RoundingMode.DOWN);
        try {
            pageContext.getOut().println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
