package com.viazovski.flowerauction.specification.buyer.crud;

import com.viazovski.flowerauction.model.Buyer;
import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateBuyerSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "UPDATE `buyer` " +
            "SET `first_name` = ?, `last_name` = ?, `dob` = ?, " +
            "`login` = ?, `password_hash` = ? " +
            "WHERE `buyer_id` = ?";

    private Buyer buyer;

    public UpdateBuyerSpecification(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        fillWithValues(statement);
        return statement;
    }

    private void fillWithValues(PreparedStatement statement) throws SQLException {
        statement.setString(1, buyer.getFirstName());
        statement.setString(2, buyer.getLastName());
        statement.setDate(3, buyer.getDateOfBirth());
        statement.setString(4, buyer.getLogin());
        statement.setString(5, buyer.getPasswordHash());
        statement.setInt(6, buyer.getBuyerId());
    }
}
