package com.viazovski.flowerauction.specification.buyer.crud;

import com.viazovski.flowerauction.model.Buyer;
import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveBuyerSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "DELETE FROM `buyer` " +
            "WHERE `buyer_id` = ?";

    private Buyer buyer;

    public RemoveBuyerSpecification(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        statement.setInt(1, buyer.getBuyerId());
        return statement;
    }
}
