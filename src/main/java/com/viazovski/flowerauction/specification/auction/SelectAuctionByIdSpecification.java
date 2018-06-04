package com.viazovski.flowerauction.specification.auction;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectAuctionByIdSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "SELECT * FROM `auction` " +
            "WHERE `auction_id` = ?";

    private int auctionId;

    public SelectAuctionByIdSpecification(int auctionId) {
        this.auctionId = auctionId;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        var preparedStatement = statementGetter.apply(QUERY);
        preparedStatement.setInt(1, auctionId);
        return preparedStatement;
    }
}
