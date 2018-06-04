package com.viazovski.flowerauction.command;

import com.google.gson.Gson;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.Auction;
import com.viazovski.flowerauction.model.Buyer;
import com.viazovski.flowerauction.model.BuyerAuction;
import com.viazovski.flowerauction.model.Flower;
import com.viazovski.flowerauction.service.AuctionService;
import com.viazovski.flowerauction.service.BuyerAuctionService;
import com.viazovski.flowerauction.service.BuyerService;
import com.viazovski.flowerauction.service.FlowerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ManageRequestsCommand implements Command {

    /**
     * Sends all relevant information for managing requests.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the json {@code String} containing auctions, buyer-auction pairs, flowers, buyers.
     * @throws  CommandException if any find method from service layer
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var buyerAuctionService = new BuyerAuctionService();
        var auctionService = new AuctionService();
        var buyerService = new BuyerService();
        var flowerService = new FlowerService();
        try {
            var buyerAuctionList = buyerAuctionService.findAllUnacceptedBuyerAuctions();
            var auctionList = auctionService.findRequestedAuctions();
            var buyerList = buyerService.findRequestedBuyers();
            var flowerList = flowerService.findRequestedFlowers();
            var container = new Container(buyerAuctionList, auctionList, buyerList, flowerList);
            return new Gson().toJson(container);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    private class Container {

        private List<BuyerAuction> buyerAuctions;

        private List<Auction> auctions;

        private List<Buyer> buyers;

        private List<Flower> flowers;

        Container(List<BuyerAuction> buyerAuctions, List<Auction> auctions, List<Buyer> buyers, List<Flower> flowers) {
            this.buyerAuctions = buyerAuctions;
            this.auctions = auctions;
            this.buyers = buyers;
            this.flowers = flowers;
        }
    }
}
