package com.viazovski.flowerauction.specification.flower;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateFlowerAcceptedSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "UPDATE `flower` " +
            "SET `flower_accepted` = ? " +
            "WHERE `flower_id` = ?";

    private int flowerId;

    private boolean isAccepted;

    public UpdateFlowerAcceptedSpecification(int flowerId, boolean isAccepted) {
        this.flowerId = flowerId;
        this.isAccepted = isAccepted;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        statement.setBoolean(1, isAccepted);
        statement.setInt(2, flowerId);
        return statement;
    }
}
