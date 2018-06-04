package com.viazovski.flowerauction.repository;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.model.Auction;
import com.viazovski.flowerauction.model.id.AuctionId;
import com.viazovski.flowerauction.specification.auction.crud.AddAuctionSpecification;
import com.viazovski.flowerauction.specification.auction.crud.RemoveAuctionSpecification;
import com.viazovski.flowerauction.specification.auction.crud.SelectAllAuctionsSpecification;
import com.viazovski.flowerauction.specification.auction.crud.UpdateAuctionSpecification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * {@code AuctionRepository} represents a repository working with auction table.
 */
public class AuctionRepository extends AbstractRepository<Auction> {

    @Override
    Auction takeItem(ResultSet resultSet) throws SQLException {
        Auction auction = new Auction();
        auction.setAuctionId(resultSet.getInt("auction_id"));
        auction.setName(resultSet.getString("name"));
        auction.setEventDate(resultSet.getTimestamp("event_date"));
        return auction;
    }

    @Override
    AuctionId takeId(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return new AuctionId(resultSet.getInt(1));
    }

    @Override
    public AuctionId add(Auction auction) throws RepositoryException {
        return (AuctionId) nonQueryId(new AddAuctionSpecification(auction));
    }

    @Override
    public void remove(Auction auction) throws RepositoryException {
        nonQuery(new RemoveAuctionSpecification(auction));
    }

    @Override
    public void update(Auction auction) throws RepositoryException {
        nonQuery(new UpdateAuctionSpecification(auction));
    }

    @Override
    public List<Auction> selectAll() throws RepositoryException {
        return query(new SelectAllAuctionsSpecification());
    }
}
