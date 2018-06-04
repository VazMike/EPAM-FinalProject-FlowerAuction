package com.viazovski.flowerauction.command;

import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.service.BuyerService;

import javax.servlet.http.HttpServletRequest;

public class RemoveUserCommand implements Command {

    /**
     * Removes buyer from buyer table.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  empty json {@code String}.
     * @throws  CommandException if {@link BuyerService#removeUser(int)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var userId = Integer.parseInt(req.getParameter("userId"));
        var service = new BuyerService();
        try {
            service.removeUser(userId);
            return "{}";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
