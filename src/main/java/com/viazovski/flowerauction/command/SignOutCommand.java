package com.viazovski.flowerauction.command;

import javax.servlet.http.HttpServletRequest;

public class SignOutCommand implements Command {

    /**
     * Signs user out.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  empty json {@code String}.
     */
    @Override
    public String execute(HttpServletRequest req) {
        var session = req.getSession();
        session.invalidate();
        return "{}";
    }
}
