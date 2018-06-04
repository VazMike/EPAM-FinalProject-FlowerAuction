package com.viazovski.flowerauction.service;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.Buyer;
import com.viazovski.flowerauction.model.CreditCard;
import com.viazovski.flowerauction.repository.BuyerRepository;
import com.viazovski.flowerauction.repository.CreditCardRepository;
import com.viazovski.flowerauction.specification.buyer.RemoveBuyerByIdSpecification;
import com.viazovski.flowerauction.specification.creditcard.RemoveCreditCardByIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreditCardServiceTest {

    private static Logger logger = LogManager.getLogger();

    private CreditCardService service = new CreditCardService();

    private int creditCardId;

    private int ownerId;

    private static final String DUMMY_LOGIN = "Hopefully unique login credit card service";

    private static final String DUMMY_NUMBER = "9999888899998887";

    private static final String UNUSED_NUMBER = "9999888899998888";

    @BeforeClass(groups = {"service", "validation"})
    public void addDummyBuyer() throws RepositoryException {
        var buyer = new Buyer();
        buyer.setLogin(DUMMY_LOGIN);
        buyer.setPasswordHash("password hash");
        ownerId = new BuyerRepository().add(buyer).getBuyerId();
    }

    @BeforeClass(groups = {"service", "validation"})
    public void addDummyCreditCard() throws RepositoryException {
        var creditCard = new CreditCard();
        creditCard.setOwnerId(ownerId);
        creditCard.setNumber(DUMMY_NUMBER);
        creditCard.setPassword("0000");
        creditCard.setBalance(1000);
        creditCardId = new CreditCardRepository().add(creditCard).getCreditCardId();
    }

    @AfterClass(groups = {"service", "validation"})
    public void removeDummyBuyer() throws RepositoryException {
        new BuyerRepository().nonQuery(new RemoveBuyerByIdSpecification(ownerId));
        new CreditCardRepository().nonQuery(new RemoveCreditCardByIdSpecification(creditCardId));
    }

    @Test(groups = {"service", "validation"})
    public void testPositiveValidateCreditCardForm() throws ServiceException {
        var isValid = service.validateCreditCardForm(UNUSED_NUMBER, "1234", 1000).isEmpty();
        Assert.assertTrue(isValid);
        logger.info("Positive credit card validation test passed");
    }

    @Test(groups = {"service", "validation"})
    public void testNegativeNumberAlreadyExistsValidateCreditCardForm() throws ServiceException {
        var isValid = service.validateCreditCardForm(DUMMY_NUMBER, "1234", 1000).isEmpty();
        Assert.assertFalse(isValid);
        logger.info("Negative number already exists credit card validation test passed");
    }

    @Test(groups = {"service", "validation"})
    public void testNegativeNon4DigitValidateCreditCardForm() throws ServiceException {
        var isValid = service.validateCreditCardForm(UNUSED_NUMBER, "01234", 1000).isEmpty();
        Assert.assertFalse(isValid);
        logger.info("Negative non 4 digit credit card validation test passed");
    }

    @Test(groups = {"service", "validation"})
    public void testNegativeBalanceLessThen0ValidateCreditCardForm() throws ServiceException {
        var isValid = service.validateCreditCardForm(UNUSED_NUMBER, "1234", -1000).isEmpty();
        Assert.assertFalse(isValid);
        logger.info("Negative balance < 0 credit card validation test passed");
    }
}