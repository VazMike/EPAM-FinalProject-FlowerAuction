package com.viazovski.flowerauction.repository;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.model.Buyer;
import com.viazovski.flowerauction.model.Flower;
import com.viazovski.flowerauction.specification.buyer.RemoveBuyerByIdSpecification;
import com.viazovski.flowerauction.specification.flower.SelectFlowerByIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

public class FlowerRepositoryTest {

    private static Logger logger = LogManager.getLogger();

    private FlowerRepository repository = new FlowerRepository();

    private Flower flower = new Flower();

    private int ownerId;

    private static final String DUMMY_LOGIN = "Hopefully unique login flower repo";

    @BeforeClass(groups = "repository")
    public void addDummyBuyer() throws RepositoryException {
        var buyer = new Buyer();
        buyer.setLogin(DUMMY_LOGIN);
        buyer.setPasswordHash("password hash");
        ownerId = new BuyerRepository().add(buyer).getBuyerId();
    }

    @BeforeClass(groups = "repository", dependsOnMethods = "addDummyBuyer")
    public void fillCreditCardWithValues() {
        flower.setOwnerId(ownerId);
        flower.setName("Flower Name");
        flower.setValue(5);
    }

    @AfterClass(groups = "repository")
    public void removeDummyBuyer() throws RepositoryException {
        new BuyerRepository().nonQuery(new RemoveBuyerByIdSpecification(ownerId));
    }

    @Test(groups = "repository")
    public void testAdd() throws RepositoryException {
        var flowerId = repository.add(flower).getFlowerId();
        flower.setFlowerId(flowerId);
        Assert.assertTrue(selectFlowerById(flowerId).isPresent());
        logger.info("Flower added successfully");
    }

    @Test(groups = "repository", dependsOnMethods = "testSelectAll")
    public void testRemove() throws RepositoryException {
        repository.remove(flower);
        Assert.assertFalse(selectFlowerById(flower.getFlowerId()).isPresent());
        logger.info("Flower removed successfully");
    }

    @Test(groups = "repository", dependsOnMethods = "testAdd")
    public void testUpdate() throws RepositoryException {
        var newValue = 6;
        flower.setValue(newValue);
        repository.update(flower);
        var queriedFlower = selectFlowerById(flower.getFlowerId()).orElseThrow();
        Assert.assertEquals(newValue, queriedFlower.getValue());
        logger.info("Flower updated successfully");
    }

    @Test(groups = "repository", dependsOnMethods = "testUpdate")
    public void testSelectAll() throws RepositoryException {
        var flowerList = repository.selectAll();
        Assert.assertTrue(flowerList.contains(flower));
        logger.info("Flower selected successfully");
    }

    private Optional<Flower> selectFlowerById(int flowerId) throws RepositoryException {
        var flowerList = repository.query(new SelectFlowerByIdSpecification(flowerId));
        return !flowerList.isEmpty() ?
                Optional.of(flowerList.get(0)) :
                Optional.empty();
    }
}