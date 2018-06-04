package com.viazovski.flowerauction.repository;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.model.Auction;
import com.viazovski.flowerauction.specification.auction.SelectAuctionByIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.Optional;

public class AuctionRepositoryTest {

    private static Logger logger = LogManager.getLogger();

    private AuctionRepository repository = new AuctionRepository();

    private Auction auction = new Auction();

    @BeforeClass(groups = "repository")
    public void fillAuctionWithValues() {
        auction.setName("auction name");
        var time = 1000 * (System.currentTimeMillis() / 1000) + 1_000_000_000;
        auction.setEventDate(new Timestamp(time));
    }

    @Test(groups = "repository")
    public void testAdd() throws RepositoryException {
        var auctionId = repository.add(auction).getAuctionId();
        auction.setAuctionId(auctionId);
        Assert.assertTrue(selectAuctionById(auctionId).isPresent());
        logger.info("Auction added successfully");
    }

    @Test(groups = "repository", dependsOnMethods = "testSelectAll")
    public void testRemove() throws RepositoryException {
        repository.remove(auction);
        Assert.assertFalse(selectAuctionById(auction.getAuctionId()).isPresent());
        logger.info("Auction removed successfully");
    }

    @Test(groups = "repository", dependsOnMethods = "testAdd")
    public void testUpdate() throws RepositoryException {
        var newName = "new auction name";
        auction.setName(newName);
        repository.update(auction);
        var queriedAuction = selectAuctionById(auction.getAuctionId()).orElseThrow();
        Assert.assertEquals(newName, queriedAuction.getName());
        logger.info("Auction updated successfully");
    }

    @Test(groups = "repository", dependsOnMethods = "testUpdate")
    public void testSelectAll() throws RepositoryException {
        var auctionList = repository.selectAll();
        Assert.assertTrue(auctionList.contains(auction));
        logger.info("Auction selected successfully");
    }

    private Optional<Auction> selectAuctionById(int auctionId) throws RepositoryException {
        var auctionList = repository.query(new SelectAuctionByIdSpecification(auctionId));
        return !auctionList.isEmpty() ?
                Optional.of(auctionList.get(0)) :
                Optional.empty();
    }
}