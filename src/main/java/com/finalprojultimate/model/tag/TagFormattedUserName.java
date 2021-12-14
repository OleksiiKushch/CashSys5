package com.finalprojultimate.model.tag;

import com.finalprojultimate.model.service.UserService;
import com.finalprojultimate.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class TagFormattedUserName extends TagSupport {
    private static final Logger logger = Logger.getLogger(TagFormattedUserName.class);

    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int doStartTag() throws JspException {
        UserService userService = UserServiceImpl.getInstance();
        try {
            pageContext.getOut().println(userService.getFormattedNameById(userId));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
