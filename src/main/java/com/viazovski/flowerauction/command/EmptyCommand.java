package com.viazovski.flowerauction.command;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        return "index.jsp";
    }
}
