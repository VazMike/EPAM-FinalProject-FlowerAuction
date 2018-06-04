package com.viazovski.flowerauction.specification.flower;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectBuyersUnassignedFlowersSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "SELECT * FROM `flower` " +
            "WHERE `owner_id` = ? " +
            "AND `auction_id` IS NULL";

    private int buyerId;

    public SelectBuyersUnassignedFlowersSpecification(int buyerId) {
        this.buyerId = buyerId;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        statement.setInt(1, buyerId);
        return statement;
    }
}
