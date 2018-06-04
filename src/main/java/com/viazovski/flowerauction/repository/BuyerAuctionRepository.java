package com.viazovski.flowerauction.repository;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.model.BuyerAuction;
import com.viazovski.flowerauction.model.id.BuyerAuctionId;
import com.viazovski.flowerauction.specification.buyerauction.crud.AddBuyerAuctionSpecification;
import com.viazovski.flowerauction.specification.buyerauction.crud.RemoveBuyerAuctionSpecification;
import com.viazovski.flowerauction.specification.buyerauction.crud.SelectAllBuyerAuctionsSpecification;
import com.viazovski.flowerauction.specification.buyerauction.crud.UpdateBuyerAuctionSpecification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * {@code BuyerAuctionRepository} represents a repository working with buyer_auction table.
 */
public class BuyerAuctionRepository extends AbstractRepository<BuyerAuction> {

    @Override
    BuyerAuction takeItem(ResultSet resultSet) throws SQLException {
        BuyerAuction buyerAuction = new BuyerAuction();
        buyerAuction.setBuyerId(resultSet.getInt("buyer_id"));
        buyerAuction.setAuctionId(resultSet.getInt("auction_id"));
        buyerAuction.setBuyerAccepted(resultSet.getBoolean("buyer_accepted"));
        return buyerAuction;
    }

    @Override
    BuyerAuctionId takeId(ResultSet resultSet) {
        return new BuyerAuctionId(0, 0);
    }

    @Override
    public BuyerAuctionId add(BuyerAuction buyerAuction) throws RepositoryException {
        return (BuyerAuctionId) nonQueryId(new AddBuyerAuctionSpecification(buyerAuction));
    }

    @Override
    public void remove(BuyerAuction buyerAuction) throws RepositoryException {
        nonQuery(new RemoveBuyerAuctionSpecification(buyerAuction));
    }

    @Override
    public void update(BuyerAuction buyerAuction) throws RepositoryException {
        nonQuery(new UpdateBuyerAuctionSpecification(buyerAuction));
    }

    @Override
    public List<BuyerAuction> selectAll() throws RepositoryException {
        return query(new SelectAllBuyerAuctionsSpecification());
    }
}
