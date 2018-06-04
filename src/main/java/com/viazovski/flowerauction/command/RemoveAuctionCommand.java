package com.viazovski.flowerauction.command;

import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.service.AuctionService;

import javax.servlet.http.HttpServletRequest;

public class RemoveAuctionCommand implements Command {

    /**
     * Removes auction from auction table.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  empty json {@code String}.
     * @throws  CommandException if {@link AuctionService#removeAuction(int)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var auctionId = Integer.parseInt(req.getParameter("auctionId"));
        var service = new AuctionService();
        try {
            service.removeAuction(auctionId);
            return "{}";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
