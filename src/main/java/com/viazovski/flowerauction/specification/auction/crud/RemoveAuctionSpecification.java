package com.viazovski.flowerauction.specification.auction.crud;

import com.viazovski.flowerauction.model.Auction;
import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveAuctionSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "DELETE FROM `auction` " +
            "WHERE `auction_id` = ?";

    private Auction auction;

    public RemoveAuctionSpecification(Auction auction) {
        this.auction = auction;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        statement.setInt(1, auction.getAuctionId());
        return statement;
    }
}
