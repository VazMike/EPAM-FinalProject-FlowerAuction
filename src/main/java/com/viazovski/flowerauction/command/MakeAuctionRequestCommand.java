package com.viazovski.flowerauction.command;

import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.service.BuyerAuctionService;

import javax.servlet.http.HttpServletRequest;

public class MakeAuctionRequestCommand implements Command {

    /**
     * Makes auction request by adding an entity to buyer_auction table.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  empty json {@code String}.
     * @throws  CommandException if {@link BuyerAuctionService#addBuyerAuction(int, int)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var auctionId = Integer.parseInt(req.getParameter("auctionId"));
        var buyerId = (int) req.getSession().getAttribute("id");
        var service = new BuyerAuctionService();
        try {
            service.addBuyerAuction(auctionId, buyerId);
            return "{}";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
