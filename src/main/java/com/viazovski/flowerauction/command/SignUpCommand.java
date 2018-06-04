package com.viazovski.flowerauction.command;

import com.google.gson.Gson;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.Buyer;
import com.viazovski.flowerauction.service.BuyerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignUpCommand implements Command {

    /**
     * Signs user up and sets his session attributes.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the json {@code String} containing validation message.
     * @throws  CommandException if {@link BuyerService#validateSignUpForm(String, String, String)}
     *          or {@link BuyerService#addUser(String, String)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var login = req.getParameter("login");
        var password = req.getParameter("password");
        var confirmPassword = req.getParameter("confirmPassword");
        var service = new BuyerService();
        try {
            var validationMessage = service.validateSignUpForm(login, password, confirmPassword);
            if (validationMessage.isEmpty()) {
                var buyer = service.addUser(login, password);
                setBuyerSessionAttributes(req, buyer);
            }
            return new Gson().toJson(validationMessage);
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
