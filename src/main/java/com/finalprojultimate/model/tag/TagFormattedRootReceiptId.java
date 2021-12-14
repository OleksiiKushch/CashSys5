package com.finalprojultimate.model.tag;

import com.finalprojultimate.model.service.UserService;
import com.finalprojultimate.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class TagFormattedRootReceiptId extends TagSupport {
    private static final Logger logger = Logger.getLogger(TagFormattedRootReceiptId.class);

    private int rootReceiptId;

    public void setRootReceiptId(int rootReceiptId) {
        this.rootReceiptId = rootReceiptId;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            if (rootReceiptId == 0) {
                pageContext.getOut().println("-");
            } else {
                pageContext.getOut().println(rootReceiptId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
