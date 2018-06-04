package com.viazovski.flowerauction.repository;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.model.Buyer;
import com.viazovski.flowerauction.model.CreditCard;
import com.viazovski.flowerauction.specification.buyer.RemoveBuyerByIdSpecification;
import com.viazovski.flowerauction.specification.creditcard.SelectCreditCardByIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

public class CreditCardRepositoryTest {

    private static Logger logger = LogManager.getLogger();

    private CreditCardRepository repository = new CreditCardRepository();

    private CreditCard creditCard = new CreditCard();

    private int ownerId;

    private static final String DUMMY_LOGIN = "Hopefully unique login credit card repo";

    @BeforeClass(groups = "repository")
    public void addDummyBuyer() throws RepositoryException {
        var buyer = new Buyer();
        buyer.setLogin(DUMMY_LOGIN);
        buyer.setPasswordHash("password hash");
        ownerId = new BuyerRepository().add(buyer).getBuyerId();
    }

    @BeforeClass(groups = "repository", dependsOnMethods = "addDummyBuyer")
    public void fillCreditCardWithValues() {
        creditCard.setOwnerId(ownerId);
        creditCard.setNumber("9999888899998888");
        creditCard.setPassword("0000");
        creditCard.setBalance(1000);
    }

    @AfterClass(groups = "repository")
    public void removeDummyBuyer() throws RepositoryException {
        new BuyerRepository().nonQuery(new RemoveBuyerByIdSpecification(ownerId));
    }

    @Test(groups = "repository")
    public void testAdd() throws RepositoryException {
        var creditCardId = repository.add(creditCard).getCreditCardId();
        creditCard.setCreditCardId(creditCardId);
        Assert.assertTrue(selectCreditCardById(creditCardId).isPresent());
        logger.info("Credit card added successfully");
    }

    @Test(groups = "repository", dependsOnMethods = "testSelectAll")
    public void testRemove() throws RepositoryException {
        repository.remove(creditCard);
        Assert.assertFalse(selectCreditCardById(creditCard.getCreditCardId()).isPresent());
        logger.info("Credit card removed successfully");
    }

    @Test(groups = "repository", dependsOnMethods = "testAdd")
    public void testUpdate() throws RepositoryException {
        var newBalance = 2000;
        creditCard.setBalance(newBalance);
        repository.update(creditCard);
        var queriedCreditCard = selectCreditCardById(creditCard.getCreditCardId()).orElseThrow();
        Assert.assertEquals(newBalance, queriedCreditCard.getBalance());
        logger.info("Credit card updated successfully");
    }

    @Test(groups = "repository", dependsOnMethods = "testUpdate")
    public void testSelectAll() throws RepositoryException {
        var creditCardList = repository.selectAll();
        Assert.assertTrue(creditCardList.contains(creditCard));
        logger.info("Credit card selected successfully");
    }

    private Optional<CreditCard> selectCreditCardById(int creditCardId) throws RepositoryException {
        var creditCardList = repository.query(new SelectCreditCardByIdSpecification(creditCardId));
        return !creditCardList.isEmpty() ?
                Optional.of(creditCardList.get(0)) :
                Optional.empty();
    }
}