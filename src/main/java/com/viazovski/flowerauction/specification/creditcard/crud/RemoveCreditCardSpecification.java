package com.viazovski.flowerauction.specification.creditcard.crud;

import com.viazovski.flowerauction.model.CreditCard;
import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveCreditCardSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "DELETE FROM `credit_card` " +
            "WHERE `credit_card_id` = ?";

    private CreditCard creditCard;

    public RemoveCreditCardSpecification(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        statement.setInt(1, creditCard.getCreditCardId());
        return statement;
    }
}
