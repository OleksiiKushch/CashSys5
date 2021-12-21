package com.finalprojultimate.model.view.tag.supplying;

import com.finalprojultimate.model.service.ReceiptService;
import com.finalprojultimate.model.service.impl.ReceiptServiceImpl;
import com.finalprojultimate.model.view.tag.formatting.TagFormattedUserName;
import org.apache.log4j.Logger;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.RoundingMode;

public class TagTotalReceiptSum extends TagSupport {
    private static final Logger logger = Logger.getLogger(TagFormattedUserName.class);

    private int receiptId;

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    @Override
    public int doStartTag() {
        ReceiptService receiptService = ReceiptServiceImpl.getInstance();
        try {
            pageContext.getOut().println(receiptService.getSumReceiptById(receiptId).setScale(2, RoundingMode.DOWN));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
