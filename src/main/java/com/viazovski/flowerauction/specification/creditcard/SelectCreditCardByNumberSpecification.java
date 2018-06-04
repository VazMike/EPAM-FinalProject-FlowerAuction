package com.viazovski.flowerauction.specification.creditcard;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectCreditCardByNumberSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "SELECT * FROM `credit_card` " +
            "WHERE `number` = ?";

    private String number;

    public SelectCreditCardByNumberSpecification(String number) {
        this.number = number;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        statement.setString(1, number);
        return statement;
    }
}
