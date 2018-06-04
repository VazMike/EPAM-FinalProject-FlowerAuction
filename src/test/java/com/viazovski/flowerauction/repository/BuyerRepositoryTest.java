package com.viazovski.flowerauction.repository;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.model.Buyer;
import com.viazovski.flowerauction.specification.buyer.SelectBuyerByIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

public class BuyerRepositoryTest {

    private static Logger logger = LogManager.getLogger();

    private BuyerRepository repository = new BuyerRepository();

    private Buyer buyer = new Buyer();

    private static final String UNIQUE_LOGIN = "Hopefully unique login buyer repo";

    @BeforeClass(groups = "repository")
    public void fillBuyerWithValues() {
        buyer.setLogin(UNIQUE_LOGIN);
        buyer.setPasswordHash("hash password");
    }

    @Test(groups = "repository")
    public void testAdd() throws RepositoryException {
        var buyerId = repository.add(buyer).getBuyerId();
        buyer.setBuyerId(buyerId);
        Assert.assertTrue(selectBuyerById(buyerId).isPresent());
        logger.info("Buyer added successfully");
    }

    @Test(groups = "repository", dependsOnMethods = "testSelectAll")
    public void testRemove() throws RepositoryException {
        repository.remove(buyer);
        Assert.assertFalse(selectBuyerById(buyer.getBuyerId()).isPresent());
        logger.info("Buyer removed successfully");
    }

    @Test(groups = "repository", dependsOnMethods = "testAdd")
    public void testUpdate() throws RepositoryException {
        var firstName = "First";
        var lastName = "Last";
        buyer.setFirstName(firstName);
        buyer.setLastName(lastName);
        repository.update(buyer);
        var queriedBuyer = selectBuyerById(buyer.getBuyerId()).orElseThrow();
        Assert.assertEquals(firstName, queriedBuyer.getFirstName());
        Assert.assertEquals(lastName, queriedBuyer.getLastName());
        logger.info("Buyer updated successfully");
    }

    @Test(groups = "repository", dependsOnMethods = "testUpdate")
    public void testSelectAll() throws RepositoryException {
        var buyerList = repository.selectAll();
        Assert.assertTrue(buyerList.contains(buyer));
        logger.info("Buyer selected successfully");
    }

    private Optional<Buyer> selectBuyerById(int buyerId) throws RepositoryException {
        var buyerList = repository.query(new SelectBuyerByIdSpecification(buyerId));
        return !buyerList.isEmpty() ?
                Optional.of(buyerList.get(0)) :
                Optional.empty();
    }
}