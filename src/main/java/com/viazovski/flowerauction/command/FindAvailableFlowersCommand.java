package com.viazovski.flowerauction.command;

import com.google.gson.Gson;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.service.FlowerService;

import javax.servlet.http.HttpServletRequest;

public class FindAvailableFlowersCommand implements Command {

    /**
     * Selects all flowers belonging to the buyer that aren't already in some auction.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the json {@code String} containing flowers.
     * @throws  CommandException if {@link FlowerService#findAllUnassignedFlowersOwnedByBuyer(int)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var id = (int) req.getSession().getAttribute("id");
        var service = new FlowerService();
        try {
            var flowerList = service.findAllUnassignedFlowersOwnedByBuyer(id);
            return new Gson().toJson(flowerList);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
