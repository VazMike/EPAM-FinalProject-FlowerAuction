package com.viazovski.flowerauction.specification.flower.crud;

import com.viazovski.flowerauction.model.Flower;
import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class AddFlowerSpecification implements SqlSpecification {

    private static Logger logger = LogManager.getLogger();

    @Language("SQL")
    private static final String QUERY =
            "INSERT INTO `flower` " +
            "(`auction_id`, `owner_id`, `flower_accepted`, `name`, `value`) " +
            "VALUES (?, ?, ?, ?, ?)";

    private Flower flower;

    public AddFlowerSpecification(Flower flower) {
        this.flower = flower;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        fillWithValues(statement);
        return statement;
    }

    private void fillWithValues(PreparedStatement statement) throws SQLException {
        if (flower.getAuctionId() != null) {
            statement.setInt(1, flower.getAuctionId());
        } else {
            statement.setNull(1, Types.INTEGER);
        }
        statement.setInt(2, flower.getOwnerId());
        statement.setBoolean(3, flower.isFlowerAccepted());
        statement.setString(4, flower.getName());
        statement.setInt(5, flower.getValue());
    }
}
