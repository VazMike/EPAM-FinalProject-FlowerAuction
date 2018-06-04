package com.viazovski.flowerauction.repository;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.model.Buyer;
import com.viazovski.flowerauction.model.Language;
import com.viazovski.flowerauction.model.Role;
import com.viazovski.flowerauction.model.id.BuyerId;
import com.viazovski.flowerauction.specification.buyer.crud.AddBuyerSpecification;
import com.viazovski.flowerauction.specification.buyer.crud.RemoveBuyerSpecification;
import com.viazovski.flowerauction.specification.buyer.crud.SelectAllBuyersSpecification;
import com.viazovski.flowerauction.specification.buyer.crud.UpdateBuyerSpecification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * {@code BuyerRepository} represents a repository working with buyer table.
 */
public class BuyerRepository extends AbstractRepository<Buyer> {

    @Override
    Buyer takeItem(ResultSet resultSet) throws SQLException {
        Buyer buyer = new Buyer();
        buyer.setBuyerId(resultSet.getInt("buyer_id"));
        buyer.setFirstName(resultSet.getString("first_name"));
        buyer.setLastName(resultSet.getString("last_name"));
        buyer.setDateOfBirth(resultSet.getDate("dob"));
        buyer.setLogin(resultSet.getString("login"));
        buyer.setPasswordHash(resultSet.getString("password_hash"));
        buyer.setPasswordSalt(resultSet.getString("password_salt"));
        String langString = resultSet.getString("lang");
        buyer.setLanguage(Language.valueOf(langString));
        String roleString = resultSet.getString("role");
        buyer.setRole(Role.valueOf(roleString));
        return buyer;
    }

    @Override
    BuyerId takeId(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return new BuyerId(resultSet.getInt(1));
    }

    @Override
    public BuyerId add(Buyer buyer) throws RepositoryException {
        return (BuyerId) nonQueryId(new AddBuyerSpecification(buyer));
    }

    @Override
    public void remove(Buyer buyer) throws RepositoryException {
        nonQuery(new RemoveBuyerSpecification(buyer));
    }

    @Override
    public void update(Buyer buyer) throws RepositoryException {
        nonQuery(new UpdateBuyerSpecification(buyer));
    }

    @Override
    public List<Buyer> selectAll() throws RepositoryException {
        return query(new SelectAllBuyersSpecification());
    }
}
