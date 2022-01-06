package com.finalprojultimate.model.view.tag.formatting;

import com.finalprojultimate.model.entity.product.Unit;
import org.apache.log4j.Logger;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TagFormattedAmount extends TagSupport {
    private static final Logger logger = Logger.getLogger(TagFormattedAmount.class);

    private BigDecimal amount;
    private Unit unit;

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public int doStartTag() {
        if (unit == Unit.PIECES) {
            amount = amount.setScale(0, RoundingMode.DOWN);
        } else {
            amount = amount.setScale(3, RoundingMode.DOWN);
        }
        try {
            pageContext.getOut().println(amount);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
