package com.viazovski.flowerauction.specification.creditcard;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectAllCreditCardsOwnedByBuyerSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "SELECT * FROM `credit_card` " +
            "WHERE `owner_id` = ?";

    private int buyerId;

    public SelectAllCreditCardsOwnedByBuyerSpecification(int buyerId) {
        this.buyerId = buyerId;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        statement.setInt(1, buyerId);
        return statement;
    }
}
