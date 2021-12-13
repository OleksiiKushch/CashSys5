package com.finalprojultimate.controller.command.get.general;

import com.finalprojultimate.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.finalprojultimate.util.Page.MY_PROFILE_PAGE;

public class GetMyProfileCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetMyProfileCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        return MY_PROFILE_PAGE;
    }
}
