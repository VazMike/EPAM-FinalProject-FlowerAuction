package com.viazovski.flowerauction.command;

import com.google.gson.Gson;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.Auction;
import com.viazovski.flowerauction.model.BuyerAuction;
import com.viazovski.flowerauction.service.AuctionService;
import com.viazovski.flowerauction.service.BuyerAuctionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RequestCommand implements Command {

    /**
     * Sends all relevant information for request page.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the json {@code String} containing auctions and buyer-auction pairs.
     * @throws  CommandException if {@link BuyerAuctionService#findBuyerAuctionsByLogin(String)}
     *          or {@link AuctionService#findAllAuctions()}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var login = (String) req.getSession().getAttribute("login");
        var auctionService = new AuctionService();
        var buyerAuctionService = new BuyerAuctionService();
        try {
            var auctions = auctionService.findAllAuctions();
            var buyerAuctions = buyerAuctionService.findBuyerAuctionsByLogin(login);
            var container = new Container(auctions, buyerAuctions);
            return new Gson().toJson(container);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    private class Container {

        private List<Auction> auctions;

        private List<BuyerAuction> buyerAuctions;

        Container(List<Auction> auctions, List<BuyerAuction> buyerAuctions) {
            this.auctions = auctions;
            this.buyerAuctions = buyerAuctions;
        }
    }
}
