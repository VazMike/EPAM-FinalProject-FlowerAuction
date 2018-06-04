package com.viazovski.flowerauction.specification.flower.crud;

import com.viazovski.flowerauction.model.Flower;
import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveFlowerSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "DELETE FROM `flower` " +
            "WHERE `flower_id` = ?";

    private Flower flower;

    public RemoveFlowerSpecification(Flower flower) {
        this.flower = flower;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        statement.setInt(1, flower.getFlowerId());
        return statement;
    }
}
