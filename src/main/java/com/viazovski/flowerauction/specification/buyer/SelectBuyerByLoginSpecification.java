package com.viazovski.flowerauction.specification.buyer;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectBuyerByLoginSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY = "SELECT * FROM `buyer` WHERE `login` = ?";

    private String login;

    public SelectBuyerByLoginSpecification(String login) {
        this.login = login;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        statement.setString(1, login);
        return statement;
    }
}
