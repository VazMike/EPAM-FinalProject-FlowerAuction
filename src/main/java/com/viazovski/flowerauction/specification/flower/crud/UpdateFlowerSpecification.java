package com.viazovski.flowerauction.specification.flower.crud;

import com.viazovski.flowerauction.model.Flower;
import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class UpdateFlowerSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "UPDATE `flower` SET " +
            "`auction_id` = ?, `owner_id` = ?, " +
            "`flower_accepted` = ?, `name` = ?, `value` = ? " +
            "WHERE `flower_id` = ?";

    private Flower flower;

    public UpdateFlowerSpecification(Flower flower) {
        this.flower = flower;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        var preparedStatement = statementGetter.apply(QUERY);
        fillWithValues(preparedStatement);
        return preparedStatement;
    }

    private void fillWithValues(PreparedStatement preparedStatement) throws SQLException {
        if (flower.getAuctionId() != null) {
            preparedStatement.setInt(1, flower.getAuctionId());
        } else {
            preparedStatement.setNull(1, Types.INTEGER);
        }
        preparedStatement.setInt(2, flower.getOwnerId());
        preparedStatement.setBoolean(3, flower.isFlowerAccepted());
        preparedStatement.setString(4, flower.getName());
        preparedStatement.setInt(5, flower.getValue());
        preparedStatement.setInt(6, flower.getFlowerId());
    }
}
