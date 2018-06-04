package com.viazovski.flowerauction.command;

import com.google.gson.Gson;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.service.BuyerService;

import javax.servlet.http.HttpServletRequest;

public class ManageUsersCommand implements Command {

    /**
     * Send request for flower to be assigned to auction.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the json {@code String} containing buyers.
     * @throws  CommandException if {@link BuyerService#findAllBuyers()}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var service = new BuyerService();
        try {
            var buyerList = service.findAllBuyers();
            return new Gson().toJson(buyerList);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
