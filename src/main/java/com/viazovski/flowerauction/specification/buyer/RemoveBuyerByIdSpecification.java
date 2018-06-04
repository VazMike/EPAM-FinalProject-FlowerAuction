package com.viazovski.flowerauction.specification.buyer;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveBuyerByIdSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY = "DELETE FROM `buyer` WHERE `buyer_id` = ?";

    private int id;

    public RemoveBuyerByIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        PreparedStatement statement = statementGetter.apply(QUERY);
        statement.setInt(1, id);
        return statement;
    }
}
