package com.viazovski.flowerauction.command;

import com.google.gson.GsonBuilder;
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

public class ManageAuctionsCommand implements Command {

    /**
     * Sends all relevant information for managing auctions.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the json {@code String} containing auctions, buyer-auction pairs, flowers, buyers.
     * @throws  CommandException if any find method from service layer
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var auctionService = new AuctionService();
        var buyerAuctionService = new BuyerAuctionService();
        var flowerService = new FlowerService();
        var buyerService = new BuyerService();
        try {
            var auctionList = auctionService.findAllAuctions();
            var buyerAuctionList = buyerAuctionService.findAllBuyerAuctions();
            var flowerList = flowerService.findAllAcceptedFlowers();
            var buyerList = buyerService.findAllAcceptedBuyers();
            var container = new Container(auctionList, buyerAuctionList, flowerList, buyerList);
            var gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            return gson.toJson(container);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    private class Container {

        private List<Auction> auctions;

        private List<BuyerAuction> buyerAuctions;

        private List<Flower> flowers;

        private List<Buyer> buyers;

        Container(List<Auction> auctions, List<BuyerAuction> buyerAuctions, List<Flower> flowers, List<Buyer> buyers) {
            this.auctions = auctions;
            this.buyerAuctions = buyerAuctions;
            this.flowers = flowers;
            this.buyers = buyers;
        }
    }
}
