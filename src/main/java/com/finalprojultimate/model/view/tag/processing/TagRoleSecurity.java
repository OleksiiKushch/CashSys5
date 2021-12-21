package com.finalprojultimate.model.view.tag.processing;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

import static com.finalprojultimate.util.Page.PAGE_NOT_FOUND_ERROR_PAGE;

public class TagRoleSecurity extends TagSupport {
    private static final Logger logger = Logger.getLogger(TagRoleSecurity.class);

    private String role;
    private String loggedUserRole;

    public void setRole(String role) {
        this.role = role;
    }

    public void setLoggedUserRole(String loggedUserRole) {
        this.loggedUserRole = loggedUserRole;
    }

    @Override
    public int doStartTag() {
        if (!role.equals(loggedUserRole)) {
            try {
                pageContext.forward(PAGE_NOT_FOUND_ERROR_PAGE);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }

        return SKIP_BODY;
    }
}
