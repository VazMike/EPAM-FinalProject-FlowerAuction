package com.viazovski.flowerauction.service;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.CreditCard;
import com.viazovski.flowerauction.repository.CreditCardRepository;
import com.viazovski.flowerauction.specification.creditcard.RemoveCreditCardByIdSpecification;
import com.viazovski.flowerauction.specification.creditcard.SelectAllCreditCardsOwnedByBuyerSpecification;
import com.viazovski.flowerauction.specification.creditcard.SelectCreditCardByNumberSpecification;
import com.viazovski.flowerauction.validationmessage.AddCreditCardValidationMessage;
import com.viazovski.flowerauction.validationmessage.ValidationMessage;

import java.util.List;

/**
 * {@code CreditCardService} serves as a bridge between command and
 * repository layer. It receives input regarding credit cards from a client
 * validates it and relays responsibility for database operations to
 * repository layer.
 */
public class CreditCardService {

    private CreditCardRepository repository = new CreditCardRepository();

    public CreditCard addCreditCard(int ownerId, String number, String password, int balance) throws ServiceException {
        var creditCard = new CreditCard(ownerId, number, password, balance);
        var creditCardId = addCreditCardAndGetId(creditCard);
        creditCard.setCreditCardId(creditCardId);
        return creditCard;
    }

    public void removeCreditCard(int creditCardId) throws ServiceException {
        try {
            repository.nonQuery(new RemoveCreditCardByIdSpecification(creditCardId));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<CreditCard> findAllCreditCardsOwnedByBuyer(int buyerId) throws ServiceException {
        try {
            return repository.query(new SelectAllCreditCardsOwnedByBuyerSpecification(buyerId));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Number must be a unique 16-digit integer.
     * Password must be a 4-digit integer.
     * Balance must be positive.
     *
     * @param number client's number input.
     * @param password client's password input.
     * @param balance client's balance input.
     * @return validation message.
     * @throws ServiceException {@link #alreadyExists(String)} does.
     */
    public ValidationMessage validateCreditCardForm(String number, String password, int balance) throws ServiceException {
        var validationMessage = new AddCreditCardValidationMessage();
        if (!number.matches("\\d{16}")) {
            validationMessage.setAddCreditCardNumberError("Number must consist of 16 digits");
        }
        if (alreadyExists(number)) {
            validationMessage.setAddCreditCardNumberError("Such number belongs to other buyer");
        }
        if (!password.matches("\\d{4}")) {
            validationMessage.setAddCreditCardPasswordError("Password must consist of 4 digits");
        }
        if (balance < 0) {
            validationMessage.setAddCreditCardBalanceError("Money mustn't be negative");
        }
        return validationMessage;
    }

    private boolean alreadyExists(String number) throws ServiceException {
        try {
            var creditCardList = repository.query(new SelectCreditCardByNumberSpecification(number));
            return !creditCardList.isEmpty();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    private int addCreditCardAndGetId(CreditCard creditCard) throws ServiceException {
        try {
            return repository.add(creditCard).getCreditCardId();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}