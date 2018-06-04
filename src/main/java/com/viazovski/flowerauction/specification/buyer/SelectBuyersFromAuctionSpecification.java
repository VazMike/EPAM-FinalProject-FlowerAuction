package com.viazovski.flowerauction.specification.buyer;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectBuyersFromAuctionSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "SELECT * FROM `buyer` " +
            "WHERE `buyer_id` IN (" +
                "SELECT DISTINCT `buyer_id` FROM `buyer_auction` " +
                "WHERE auction_id = ? AND `buyer_accepted` = TRUE)";

    private int auctionId;

    public SelectBuyersFromAuctionSpecification(int auctionId) {
        this.auctionId = auctionId;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        statement.setInt(1, auctionId);
        return statement;
    }
}
