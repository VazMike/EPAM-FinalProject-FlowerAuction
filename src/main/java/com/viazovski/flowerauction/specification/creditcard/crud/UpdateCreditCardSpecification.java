package com.viazovski.flowerauction.specification.creditcard.crud;

import com.viazovski.flowerauction.model.CreditCard;
import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCreditCardSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "UPDATE `credit_card` SET " +
            "`owner_id` = ?, `number` = ?, " +
            "`password` = ?, `balance` = ? " +
            "WHERE `credit_card_id` = ?";

    private CreditCard creditCard;

    public UpdateCreditCardSpecification(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        fillWithValues(statement);
        return statement;
    }

    private void fillWithValues(PreparedStatement statement) throws SQLException {
        statement.setInt(1, creditCard.getOwnerId());
        statement.setString(2, creditCard.getNumber());
        statement.setString(3, creditCard.getPassword());
        statement.setInt(4, creditCard.getBalance());
        statement.setInt(5, creditCard.getCreditCardId());
    }
}
