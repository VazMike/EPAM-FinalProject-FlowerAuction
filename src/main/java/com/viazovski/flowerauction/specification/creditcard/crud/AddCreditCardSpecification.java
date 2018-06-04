package com.viazovski.flowerauction.specification.creditcard.crud;

import com.viazovski.flowerauction.model.CreditCard;
import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCreditCardSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "INSERT INTO `credit_card` " +
            "(`owner_id`, `number`, `password`, `balance`) " +
            "VALUES (?, ?, ?, ?)";

    private CreditCard creditCard;

    public AddCreditCardSpecification(CreditCard creditCard) {
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
    }
}
