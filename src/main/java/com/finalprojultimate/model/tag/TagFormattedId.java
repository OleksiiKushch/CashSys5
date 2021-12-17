package com.finalprojultimate.model.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class TagFormattedId extends TagSupport {
    private static final Logger logger = Logger.getLogger(TagFormattedId.class);

    private long idValue;

    public void setIdValue(long idValue) {
        this.idValue = idValue;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            if (idValue == 0) {
                pageContext.getOut().println("-");
            } else {
                pageContext.getOut().println(idValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
