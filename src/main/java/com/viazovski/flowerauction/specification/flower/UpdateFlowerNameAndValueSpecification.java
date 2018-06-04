package com.viazovski.flowerauction.specification.flower;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateFlowerNameAndValueSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "UPDATE `flower` " +
            "SET `name` = ?, `value` = ? " +
            "WHERE `flower_id` = ?";

    private String name;

    private int value;

    private int flowerId;

    public UpdateFlowerNameAndValueSpecification(String name, int value, int flowerId) {
        this.name = name;
        this.value = value;
        this.flowerId = flowerId;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        fillWithValues(statement);
        return statement;
    }

    private void fillWithValues(PreparedStatement statement) throws SQLException {
        statement.setString(1, name);
        statement.setInt(2, value);
        statement.setInt(3, flowerId);
    }


}
