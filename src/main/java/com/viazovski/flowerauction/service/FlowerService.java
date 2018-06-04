package com.viazovski.flowerauction.service;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.Flower;
import com.viazovski.flowerauction.repository.FlowerRepository;
import com.viazovski.flowerauction.specification.flower.*;
import com.viazovski.flowerauction.validationmessage.AddFlowerValidationMessage;
import com.viazovski.flowerauction.validationmessage.ValidationMessage;

import java.util.List;

/**
 * {@code FlowerService} serves as a bridge between command and
 * repository layer. It receives input regarding flowers from a client
 * validates it and relays responsibility for database operations to
 * repository layer.
 */
public class FlowerService {

    private FlowerRepository repository = new FlowerRepository();

    public Flower addFlower(int ownerId, String name, int value) throws ServiceException {
        var flower = new Flower(ownerId, name, value);
        var flowerId = addFlowerAndGetId(flower);
        flower.setFlowerId(flowerId);
        return flower;
    }

    public void removeFlower(int flowerId) throws ServiceException {
        try {
            repository.nonQuery(new RemoveFlowerByIdSpecification(flowerId));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void updateFlower(int flowerId, String name, int value) throws ServiceException {
        try {
            repository.nonQuery(new UpdateFlowerNameAndValueSpecification(name, value, flowerId));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void acceptFlower(int flowerId) throws ServiceException {
        try {
            repository.nonQuery(new UpdateFlowerAcceptedSpecification(flowerId, true));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void rejectFlower(int flowerId) throws ServiceException {
        try {
            repository.nonQuery(new UpdateFlowerAuctionIdAndAcceptedSpecification(flowerId, null, false));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Flower> findAllAcceptedFlowers() throws ServiceException {
        try {
            return repository.query(new SelectAllAcceptedFlowersSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Flower> findRequestedFlowers() throws ServiceException {
        try {
            return repository.query(new SelectRequestedFlowersSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Flower> findAllUnassignedFlowersOwnedByBuyer(int buyerId) throws ServiceException {
        try {
            return repository.query(new SelectBuyersUnassignedFlowersSpecification(buyerId));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Flower> findAllFlowersOwnedByBuyer(int buyerId) throws ServiceException {
        try {
            return repository.query(new SelectAllFlowersOwnedByBuyer(buyerId));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void updateAuction(int flowerId, int auctionId) throws ServiceException {
        try {
            repository.nonQuery(new UpdateFlowerAuctionSpecification(flowerId, auctionId));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Name must be present and value must be an integer from 1 to 10.
     *
     * @param name client's name input.
     * @param value client's value input.
     * @return validation message.
     */
    public ValidationMessage validateFlowerForm(String name, int value) {
        var validationMessage = new AddFlowerValidationMessage();
        if (name.isEmpty()) {
            validationMessage.setAddFlowerNameError("Name mustn't be empty");
        }
        if (value < 1 || value > 10) {
            validationMessage.setAddFlowerValueError("Value must be integer from 1 to 10");
        }
        return validationMessage;
    }

    private int addFlowerAndGetId(Flower flower) throws ServiceException {
        try {
            return repository.add(flower).getFlowerId();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
