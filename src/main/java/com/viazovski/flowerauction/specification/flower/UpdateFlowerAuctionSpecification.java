package com.viazovski.flowerauction.specification.flower;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateFlowerAuctionSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "UPDATE `flower` " +
            "SET `auction_id` = ? " +
            "WHERE `flower_id` = ?";

    private int flowerId;

    private int auctionId;

    public UpdateFlowerAuctionSpecification(int flowerId, int auctionId) {
        this.flowerId = flowerId;
        this.auctionId = auctionId;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        statement.setInt(1, auctionId);
        statement.setInt(2, flowerId);
        return statement;
    }
}
