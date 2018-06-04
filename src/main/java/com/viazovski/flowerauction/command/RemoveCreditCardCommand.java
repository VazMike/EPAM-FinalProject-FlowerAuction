package com.viazovski.flowerauction.command;

import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.service.CreditCardService;

import javax.servlet.http.HttpServletRequest;

public class RemoveCreditCardCommand implements Command {

    /**
     * Removes credit card from credit card table.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  empty json {@code String}.
     * @throws  CommandException if {@link CreditCardService#removeCreditCard(int)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var creditCardId = Integer.parseInt(req.getParameter("creditCardId"));
        var service = new CreditCardService();
        try {
            service.removeCreditCard(creditCardId);
            return "{}";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
