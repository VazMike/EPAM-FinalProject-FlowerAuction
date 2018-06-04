package com.viazovski.flowerauction.command;

import com.google.gson.GsonBuilder;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.Auction;
import com.viazovski.flowerauction.service.AuctionService;
import com.viazovski.flowerauction.validationmessage.ValidationMessage;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class AddAuctionCommand implements Command {

    /**
     * Adds auction to auction table.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the json {@code String} containing auction and validation validationMessage.
     * @throws  CommandException if {@link AuctionService#addAuction(String, Timestamp)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var name = req.getParameter("name");
        var eventDate = convertToTimestamp(req.getParameter("eventDate"));
        var service = new AuctionService();
        try {
            var validationMessage = service.validateAuctionForm(name, eventDate);
            var auction = validationMessage.isEmpty() ?
                    service.addAuction(name, eventDate) :
                    null;
            var container = new Container(auction, validationMessage);
            var gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            return gson.toJson(container);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    private Timestamp convertToTimestamp(String date) {
        var time = LocalDateTime.from(ISO_LOCAL_DATE_TIME.parse(date));
        return Timestamp.valueOf(time);
    }

    private class Container {

        private Auction auction;

        private ValidationMessage validationMessage;

        Container(Auction auction, ValidationMessage validationMessage) {
            this.auction = auction;
            this.validationMessage = validationMessage;
        }
    }
}
