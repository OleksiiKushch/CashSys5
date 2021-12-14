package com.finalprojultimate.model.tag;

import com.finalprojultimate.model.entity.product.Unit;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TagAmount extends TagSupport {
    private static final Logger logger = Logger.getLogger(TagAmount.class);

    private BigDecimal amount;
    private Unit unit;

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public int doStartTag() throws JspException {
        if (unit == Unit.PIECES) {
            amount = amount.setScale(0, RoundingMode.DOWN);
        }
        try {
            pageContext.getOut().println(amount.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
