package com.viazovski.flowerauction.service;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.Auction;
import com.viazovski.flowerauction.repository.AuctionRepository;
import com.viazovski.flowerauction.specification.auction.RemoveAuctionByIdSpecification;
import com.viazovski.flowerauction.specification.auction.SelectRequestedAuctionsSpecification;
import com.viazovski.flowerauction.validationmessage.AddAuctionValidationMessage;

import java.sql.Timestamp;
import java.util.List;

/**
 * {@code AuctionService} serves as a bridge between command and
 * repository layer. It receives input regarding auctions from a client
 * validates it and relays responsibility for database operations to
 * repository layer.
 */
public class AuctionService {

    private AuctionRepository repository = new AuctionRepository();

    public Auction addAuction(String name, Timestamp eventDate) throws ServiceException {
        var auction = new Auction(name, eventDate);
        var id = addAuctionAndGetId(auction);
        auction.setAuctionId(id);
        return auction;
    }

    public void removeAuction(int auctionId) throws ServiceException {
        try {
            repository.nonQuery(new RemoveAuctionByIdSpecification(auctionId));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void updateAuction(int auctionId, String name, Timestamp eventDate) throws ServiceException {
        var auction = new Auction(auctionId, name, eventDate);
        try {
            repository.update(auction);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Auction> findAllAuctions() throws ServiceException {
        try {
            return repository.selectAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Auction> findRequestedAuctions() throws ServiceException {
        try {
            return repository.query(new SelectRequestedAuctionsSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * The name must be present and eventDate must relate to time in future.
     *
     * @param name client's name input.
     * @param eventDate client's eventDate input.
     * @return validation message.
     */
    public AddAuctionValidationMessage validateAuctionForm(String name, Timestamp eventDate) {
        var validationMessage = new AddAuctionValidationMessage();
        if (name.isEmpty()) {
            validationMessage.setAddAuctionNameError("Name is empty");
        }
        if (new Timestamp(System.currentTimeMillis()).after(eventDate)) {
            validationMessage.setAddAuctionEventDateError("The time has passed");
        }
        return validationMessage;
    }

    private int addAuctionAndGetId(Auction auction) throws ServiceException {
        try {
            return repository.add(auction).getAuctionId();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
