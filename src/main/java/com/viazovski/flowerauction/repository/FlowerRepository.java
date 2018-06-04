package com.viazovski.flowerauction.repository;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.model.Flower;
import com.viazovski.flowerauction.model.id.FlowerId;
import com.viazovski.flowerauction.specification.flower.crud.AddFlowerSpecification;
import com.viazovski.flowerauction.specification.flower.crud.RemoveFlowerSpecification;
import com.viazovski.flowerauction.specification.flower.crud.SelectAllFlowersSpecification;
import com.viazovski.flowerauction.specification.flower.crud.UpdateFlowerSpecification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * {@code FlowerRepository} represents a repository working with flower table.
 */
public class FlowerRepository extends AbstractRepository<Flower> {
    
    @Override
    Flower takeItem(ResultSet resultSet) throws SQLException {
        Flower flower = new Flower();
        flower.setFlowerId(resultSet.getInt("flower_id"));
        int auctionId = resultSet.getInt("auction_id");
        flower.setAuctionId(!resultSet.wasNull() ? auctionId : null);
        flower.setOwnerId(resultSet.getInt("owner_id"));
        flower.setFlowerAccepted(resultSet.getBoolean("flower_accepted"));
        flower.setName(resultSet.getString("name"));
        flower.setValue(resultSet.getInt("value"));
        return flower;
    }

    @Override
    FlowerId takeId(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return new FlowerId(resultSet.getInt(1));
    }

    @Override
    public FlowerId add(Flower flower) throws RepositoryException {
        return (FlowerId) nonQueryId(new AddFlowerSpecification(flower));
    }

    @Override
    public void remove(Flower flower) throws RepositoryException {
        nonQuery(new RemoveFlowerSpecification(flower));
    }

    @Override
    public void update(Flower flower) throws RepositoryException {
        nonQuery(new UpdateFlowerSpecification(flower));
    }

    @Override
    public List<Flower> selectAll() throws RepositoryException {
        return query(new SelectAllFlowersSpecification());
    }
}
