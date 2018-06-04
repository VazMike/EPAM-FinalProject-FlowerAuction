package com.viazovski.flowerauction.specification.flower;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectFlowerByIdSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "SELECT * FROM `flower` " +
            "WHERE `flower_id` = ?";

    private int flowerId;

    public SelectFlowerByIdSpecification(int flowerId) {
        this.flowerId = flowerId;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        var preparedStatement = statementGetter.apply(QUERY);
        preparedStatement.setInt(1, flowerId);
        return preparedStatement;
    }
}
