package com.viazovski.flowerauction.command;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.service.FlowerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MakeFlowerRequestCommand implements Command {

    /**
     * Send request for flower to be assigned to auction.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  empty json {@code String}.
     * @throws  CommandException if {@link FlowerService#updateAuction(int, int)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var auctionId = Integer.parseInt(req.getParameter("auctionId"));
        var flowerIdsStrings = req.getParameter("flowerIds");
        var flowerIdList = new Gson().<List<Integer>>fromJson(flowerIdsStrings, new TypeToken<List<Integer>>(){}.getType());
        var service = new FlowerService();
        try {
            for (var flowerId : flowerIdList) {
                service.updateAuction(flowerId, auctionId);
            }
            return "{}";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
