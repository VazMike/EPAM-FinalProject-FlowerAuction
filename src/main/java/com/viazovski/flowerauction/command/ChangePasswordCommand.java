package com.viazovski.flowerauction.command;

import com.google.gson.Gson;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.service.BuyerService;

import javax.servlet.http.HttpServletRequest;

public class ChangePasswordCommand implements Command {

    /**
     * Changes user password.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the json {@code String} containing submit errors.
     * @throws  CommandException if {@link BuyerService#validateChangePasswordForm(String, String, String, String)}
     *          or {@link BuyerService#updatePassword(String, String)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var login = (String) req.getSession().getAttribute("login");
        var oldPassword = req.getParameter("oldPassword");
        var newPassword = req.getParameter("newPassword");
        var confirmNewPassword = req.getParameter("confirmNewPassword");
        var service = new BuyerService();
        try {
            var validationMessage = service.validateChangePasswordForm(login, oldPassword, newPassword, confirmNewPassword);
            if (validationMessage.isEmpty()) {
                service.updatePassword(login, newPassword);
            }
            return new Gson().toJson(validationMessage);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
