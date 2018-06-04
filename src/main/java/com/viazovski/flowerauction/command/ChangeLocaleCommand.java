package com.viazovski.flowerauction.command;

import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.service.BuyerService;

import javax.servlet.http.HttpServletRequest;

public class ChangeLocaleCommand implements Command {

    /**
     * Changes user locale.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the empty json {@code String}.
     * @throws  CommandException if {@link BuyerService#updateLocale(String, String)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var login = (String) req.getSession().getAttribute("login");
        var newLocale = req.getParameter("locale");
        var service = new BuyerService();
        try {
            service.updateLocale(login, newLocale);
            req.getSession().setAttribute("lang", newLocale);
            return "{}";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
