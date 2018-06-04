package com.viazovski.flowerauction.command;

import com.google.gson.Gson;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.Auction;
import com.viazovski.flowerauction.service.AuctionService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class EditAuctionCommand implements Command {

    /**
     * Updates auction table row classified by auction id.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the json {@code String} containing validation message.
     * @throws  CommandException if {@link AuctionService#updateAuction(Auction)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var auctionId = Integer.parseInt(req.getParameter("auctionId"));
        var name = req.getParameter("name");
        var eventDate = convertToTimestamp(req.getParameter("eventDate"));
        var service = new AuctionService();
        try {
            var validationMessage = service.validateAuctionForm(name, eventDate);
            if (validationMessage.isEmpty()) {
                service.updateAuction(auctionId, name, eventDate);
            }
            return new Gson().toJson(validationMessage);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    private Timestamp convertToTimestamp(String date) {
        var time = LocalDateTime.from(ISO_LOCAL_DATE_TIME.parse(date));
        return Timestamp.valueOf(time);
    }
}
