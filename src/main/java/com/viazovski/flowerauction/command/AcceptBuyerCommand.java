package com.viazovski.flowerauction.command;

import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.service.BuyerAuctionService;

import javax.servlet.http.HttpServletRequest;

public class AcceptBuyerCommand implements Command {

    /**
     * Accepts buyer to the requested auction.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  empty json {@code String}.
     * @throws  CommandException if {@link BuyerAuctionService#acceptBuyer(int, int)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var auctionId = Integer.parseInt(req.getParameter("auctionId"));
        var buyerId = Integer.parseInt(req.getParameter("buyerId"));
        var service = new BuyerAuctionService();
        try {
            service.acceptBuyer(auctionId, buyerId);
            return "{}";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
