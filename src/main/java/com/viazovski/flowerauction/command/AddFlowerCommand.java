package com.viazovski.flowerauction.command;

import com.google.gson.Gson;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.Flower;
import com.viazovski.flowerauction.service.FlowerService;
import com.viazovski.flowerauction.validationmessage.ValidationMessage;

import javax.servlet.http.HttpServletRequest;

public class AddFlowerCommand implements Command {

    /**
     * Adds flower to flower table.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the json {@code String} containing flower and validation validationMessage.
     * @throws  CommandException if {@link FlowerService#addFlower(int, String, int)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var id = (int) req.getSession().getAttribute("id");
        var name = req.getParameter("name");
        var value = Integer.parseInt(req.getParameter("value"));
        var service = new FlowerService();
        try {
            var validationMessage = service.validateFlowerForm(name, value);
            var flower = validationMessage.isEmpty() ?
                    service.addFlower(id, name, value) :
                    null;
            var container = new Container(flower, validationMessage);
            return new Gson().toJson(container);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    private class Container {

        private Flower flower;

        private ValidationMessage validationMessage;

        Container(Flower flower, ValidationMessage validationMessage) {
            this.flower = flower;
            this.validationMessage = validationMessage;
        }
    }
}
