package com.viazovski.flowerauction.command;

import com.google.gson.Gson;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.CreditCard;
import com.viazovski.flowerauction.service.CreditCardService;
import com.viazovski.flowerauction.validationmessage.ValidationMessage;

import javax.servlet.http.HttpServletRequest;

public class AddCreditCardCommand implements Command {

    /**
     * Adds credit card to credit card table.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the json {@code String} containing credit card and validation validationMessage.
     * @throws  CommandException if {@link CreditCardService#addCreditCard(int, String, String, int)}
     *          or {@link CreditCardService#validateCreditCardForm(String, String, int)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var ownerId = (int) req.getSession().getAttribute("id");
        var number = req.getParameter("number");
        var password = req.getParameter("password");
        var balance = Integer.parseInt(req.getParameter("balance"));
        var service = new CreditCardService();
        try {
            var validationMessage = service.validateCreditCardForm(number, password, balance);
            var creditCard = validationMessage.isEmpty() ?
                    service.addCreditCard(ownerId, number, password, balance) :
                    null;
            var container = new Container(creditCard, validationMessage);
            return new Gson().toJson(container);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    private class Container {

        private CreditCard creditCard;

        private ValidationMessage validationMessage;

        Container(CreditCard creditCard, ValidationMessage validationMessage) {
            this.creditCard = creditCard;
            this.validationMessage = validationMessage;
        }
    }
}
