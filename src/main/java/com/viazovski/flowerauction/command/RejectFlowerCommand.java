package com.viazovski.flowerauction.command;

import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.service.FlowerService;

import javax.servlet.http.HttpServletRequest;

public class RejectFlowerCommand implements Command {

    /**
     * Rejects buyer's request to assign flower to auction.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  empty json {@code String}.
     * @throws  CommandException if {@link FlowerService#rejectFlower(int)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var flowerId = Integer.parseInt(req.getParameter("flowerId"));
        var service = new FlowerService();
        try {
            service.rejectFlower(flowerId);
            return "{}";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
