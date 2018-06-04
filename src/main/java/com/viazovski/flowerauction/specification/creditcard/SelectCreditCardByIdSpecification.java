package com.viazovski.flowerauction.specification.creditcard;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectCreditCardByIdSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "SELECT * FROM `credit_card` " +
            "WHERE `credit_card_id` = ?";

    private int creditCardId;

    public SelectCreditCardByIdSpecification(int creditCardId) {
        this.creditCardId = creditCardId;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        var preparedStatement = statementGetter.apply(QUERY);
        preparedStatement.setInt(1, creditCardId);
        return preparedStatement;
    }
}
