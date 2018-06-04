package com.viazovski.flowerauction.specification.auction.crud;

import com.viazovski.flowerauction.model.Auction;
import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddAuctionSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "INSERT INTO `auction` " +
            "(`name`, `event_date`) " +
            "VALUES (?, ?)";

    private Auction auction;

    public AddAuctionSpecification(Auction auction) {
        this.auction = auction;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        fillWithValues(statement);
        return statement;
    }

    private void fillWithValues(PreparedStatement statement) throws SQLException {
        statement.setString(1, auction.getName());
        statement.setTimestamp(2, auction.getEventDate());
    }
}
