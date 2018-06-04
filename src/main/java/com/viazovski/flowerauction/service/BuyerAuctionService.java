package com.viazovski.flowerauction.service;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.BuyerAuction;
import com.viazovski.flowerauction.repository.BuyerAuctionRepository;
import com.viazovski.flowerauction.specification.buyerauction.SelectBuyerAuctionsByLogin;
import com.viazovski.flowerauction.specification.buyerauction.SelectUnacceptedBuyerAuctionsSpecification;

import java.util.List;

/**
 * {@code BuyerAuctionService} serves as a bridge between command and
 * repository layer. It receives input regarding buyer auction pairs from a client
 * validates it and relays responsibility for database operations to
 * repository layer.
 */
public class BuyerAuctionService {

    private BuyerAuctionRepository repository = new BuyerAuctionRepository();

    public void addBuyerAuction(int auctionId, int buyerId) throws ServiceException {
        var buyerAuction = new BuyerAuction(auctionId, buyerId, false);
        try {
            repository.add(buyerAuction);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void removeBuyerFromBuyerAuction(int auctionId, int buyerId) throws ServiceException {
        var buyerAuction = new BuyerAuction(auctionId, buyerId, false);
        try {
            repository.remove(buyerAuction);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<BuyerAuction> findAllBuyerAuctions() throws ServiceException {
        try {
            return repository.selectAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<BuyerAuction> findAllUnacceptedBuyerAuctions() throws ServiceException {
        try {
            return repository.query(new SelectUnacceptedBuyerAuctionsSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<BuyerAuction> findBuyerAuctionsByLogin(String login) throws ServiceException {
        try {
            return repository.query(new SelectBuyerAuctionsByLogin(login));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void acceptBuyer(int auctionId, int buyerId) throws ServiceException {
        var buyerAuction = new BuyerAuction(auctionId, buyerId, true);
        try {
            repository.update(buyerAuction);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
