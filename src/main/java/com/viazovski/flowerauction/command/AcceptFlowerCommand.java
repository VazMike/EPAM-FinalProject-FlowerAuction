package com.viazovski.flowerauction.command;

import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.service.FlowerService;

import javax.servlet.http.HttpServletRequest;

public class AcceptFlowerCommand implements Command {

    /**
     * Accepts flower to the requested auction.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  empty json {@code String}.
     * @throws  CommandException if {@link FlowerService#acceptFlower(int)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var flowerId = Integer.parseInt(req.getParameter("flowerId"));
        var service = new FlowerService();
        try {
            service.acceptFlower(flowerId);
            return "{}";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
