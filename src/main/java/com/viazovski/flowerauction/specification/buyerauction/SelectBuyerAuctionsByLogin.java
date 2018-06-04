package com.viazovski.flowerauction.specification.buyerauction;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectBuyerAuctionsByLogin implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "SELECT * FROM `buyer_auction` " +
            "WHERE `buyer_id` IN (" +
                "SELECT `buyer_id` FROM `buyer` " +
                "WHERE `login` = ?)";

    private String login;

    public SelectBuyerAuctionsByLogin(String login) {
        this.login = login;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        statement.setString(1, login);
        return statement;
    }
}
