package com.viazovski.flowerauction.command;

import com.google.gson.GsonBuilder;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.service.BuyerService;

import javax.servlet.http.HttpServletRequest;

public class ProfileCommand implements Command {

    /**
     * Send info about buyer.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the json {@code String} containing buyer.
     * @throws  CommandException if {@link BuyerService#findBuyerByLogin(String)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var login = (String) req.getSession().getAttribute("login");
        var service = new BuyerService();
        try {
            var buyer = service.findBuyerByLogin(login)
                    .orElseThrow(() -> new CommandException("Login wasn't located in DB"));
            var gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            return gson.toJson(buyer);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
