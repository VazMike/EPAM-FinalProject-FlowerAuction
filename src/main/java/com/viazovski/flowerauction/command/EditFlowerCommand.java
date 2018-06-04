package com.viazovski.flowerauction.command;

import com.google.gson.Gson;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.service.FlowerService;

import javax.servlet.http.HttpServletRequest;

public class EditFlowerCommand implements Command {

    /**
     * Updates flower table row classified by flower id.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the json {@code String} containing validation message.
     * @throws  CommandException if {@link FlowerService#updateFlower(int, String, int)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var flowerId = Integer.parseInt(req.getParameter("flowerId"));
        var name = req.getParameter("name");
        var value = Integer.parseInt(req.getParameter("value"));
        var service = new FlowerService();
        try {
            var validationMessage = service.validateFlowerForm(name, value);
            if (validationMessage.isEmpty()) {
                service.updateFlower(flowerId, name, value);
            }
            return new Gson().toJson(validationMessage);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
