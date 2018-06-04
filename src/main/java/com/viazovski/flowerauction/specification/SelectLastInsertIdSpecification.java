package com.viazovski.flowerauction.specification;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectLastInsertIdSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY = "SELECT LAST_INSERT_ID() AS last_id";

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        return statementGetter.apply(QUERY);
    }
}
