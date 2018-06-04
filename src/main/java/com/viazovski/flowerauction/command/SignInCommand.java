package com.viazovski.flowerauction.command;

import com.google.gson.Gson;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.Buyer;
import com.viazovski.flowerauction.service.BuyerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignInCommand implements Command {

    /**
     * Signs user in by validating user input and setting session attributes on success.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the json {@code String} containing user input validation message.
     * @throws  CommandException if {@link BuyerService#validateSignInForm(String, String)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var login = req.getParameter("login");
        var password = req.getParameter("password");
        var service = new BuyerService();
        try {
            var form = service.validateSignInForm(login, password);
            if (form.signInValidationMessage.isEmpty()) {
                setBuyerSessionAttributes(req, form.buyer);
            }
            return new Gson().toJson(form.signInValidationMessage);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    private void setBuyerSessionAttributes(HttpServletRequest req, Buyer buyer) {
        HttpSession session = req.getSession();
        session.setAttribute("id", buyer.getBuyerId());
        session.setAttribute("login", buyer.getLogin());
        session.setAttribute("lang", buyer.getLanguage());
        session.setAttribute("role", buyer.getRole());
    }
}
