package com.viazovski.flowerauction.specification.buyerauction.crud;

import com.viazovski.flowerauction.model.BuyerAuction;
import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveBuyerAuctionSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "DELETE FROM `buyer_auction`" +
            " WHERE `buyer_id` = ? AND `auction_id` = ?";

    private BuyerAuction buyerAuction;

    public RemoveBuyerAuctionSpecification(BuyerAuction buyerAuction) {
        this.buyerAuction = buyerAuction;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        fillWithValues(statement);
        return statement;
    }

    private void fillWithValues(PreparedStatement statement) throws SQLException {
        statement.setInt(1, buyerAuction.getBuyerId());
        statement.setInt(2, buyerAuction.getAuctionId());
    }
}
