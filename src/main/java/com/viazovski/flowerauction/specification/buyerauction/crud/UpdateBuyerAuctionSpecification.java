package com.viazovski.flowerauction.specification.buyerauction.crud;

import com.viazovski.flowerauction.model.BuyerAuction;
import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateBuyerAuctionSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "UPDATE `buyer_auction` SET `buyer_accepted` = ? " +
            "WHERE `buyer_id` = ? AND `auction_id` = ?";

    private BuyerAuction buyerAuction;

    public UpdateBuyerAuctionSpecification(BuyerAuction buyerAuction) {
        this.buyerAuction = buyerAuction;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        fillWithValues(statement);
        return statement;
    }

    private void fillWithValues(PreparedStatement statement) throws SQLException {
        statement.setBoolean(1, buyerAuction.isBuyerAccepted());
        statement.setInt(2, buyerAuction.getBuyerId());
        statement.setInt(3, buyerAuction.getAuctionId());
    }
}
