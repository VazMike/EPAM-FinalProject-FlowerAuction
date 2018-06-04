package com.viazovski.flowerauction.command;

import com.google.gson.Gson;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.CreditCard;
import com.viazovski.flowerauction.model.Flower;
import com.viazovski.flowerauction.service.CreditCardService;
import com.viazovski.flowerauction.service.FlowerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BusinessProfileCommand implements Command {

    /**
     * Selects flowers credit cards owned by buyer.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  the json {@code String} containing flowers and credit cards owned by buyer.
     * @throws  CommandException if {@link FlowerService#findAllFlowersOwnedByBuyer(int)}
     *          or {@link CreditCardService#findAllCreditCardsOwnedByBuyer(int)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var id = (int) req.getSession().getAttribute("id");
        var flowerService = new FlowerService();
        var creditCardService = new CreditCardService();
        try {
            var flowerList = flowerService.findAllFlowersOwnedByBuyer(id);
            var creditCardList = creditCardService.findAllCreditCardsOwnedByBuyer(id);
            var container = new Container(flowerList, creditCardList);
            return new Gson().toJson(container);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    private class Container {

        private List<Flower> flowers;

        private List<CreditCard> creditCards;

        Container(List<Flower> flowers, List<CreditCard> creditCards) {
            this.flowers = flowers;
            this.creditCards = creditCards;
        }
    }
}
