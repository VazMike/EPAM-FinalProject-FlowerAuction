package com.viazovski.flowerauction.repository;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.model.CreditCard;
import com.viazovski.flowerauction.model.id.CreditCardId;
import com.viazovski.flowerauction.specification.creditcard.crud.AddCreditCardSpecification;
import com.viazovski.flowerauction.specification.creditcard.crud.RemoveCreditCardSpecification;
import com.viazovski.flowerauction.specification.creditcard.crud.SelectAllCreditCardsSpecification;
import com.viazovski.flowerauction.specification.creditcard.crud.UpdateCreditCardSpecification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * {@code CreditCardRepository} represents a repository working with credit_card table.
 */
public class CreditCardRepository extends AbstractRepository<CreditCard> {

    @Override
    CreditCard takeItem(ResultSet resultSet) throws SQLException {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardId(resultSet.getInt("credit_card_id"));
        creditCard.setOwnerId(resultSet.getInt("owner_id"));
        creditCard.setNumber(resultSet.getString("number"));
        creditCard.setPassword(resultSet.getString("password"));
        creditCard.setBalance(resultSet.getInt("balance"));
        return creditCard;
    }

    @Override
    CreditCardId takeId(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return new CreditCardId(resultSet.getInt(1));
    }

    @Override
    public CreditCardId add(CreditCard creditCard) throws RepositoryException {
        return (CreditCardId) nonQueryId(new AddCreditCardSpecification(creditCard));
    }

    @Override
    public void remove(CreditCard creditCard) throws RepositoryException {
        nonQuery(new RemoveCreditCardSpecification(creditCard));
    }

    @Override
    public void update(CreditCard creditCard) throws RepositoryException {
        nonQuery(new UpdateCreditCardSpecification(creditCard));
    }

    @Override
    public List<CreditCard> selectAll() throws RepositoryException {
        return query(new SelectAllCreditCardsSpecification());
    }
}
