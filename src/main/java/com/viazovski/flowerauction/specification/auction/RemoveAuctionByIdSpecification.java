package com.viazovski.flowerauction.specification.auction;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveAuctionByIdSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "DELETE FROM `auction` " +
            "WHERE `auction_id` = ?";

    private int auctionId;

    public RemoveAuctionByIdSpecification(int auctionId) {
        this.auctionId = auctionId;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        statement.setInt(1, auctionId);
        return statement;
    }
}
