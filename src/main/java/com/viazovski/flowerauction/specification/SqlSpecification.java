package com.viazovski.flowerauction.specification;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * {@code SqlSpecification} is used in repository to make elaborate sql script
 * wrapped into {@link PreparedStatement} which in it's own turn protects
 * against SQL-injection attacks.
 */
public interface SqlSpecification {

    /**
     * Creates {@link PreparedStatement} instance filled with values.
     *
     * @param statementGetter is {@link java.sql.Connection} method to create {@link PreparedStatement} instances.
     * @return sql script wrapped into {@link PreparedStatement}
     * @throws SQLException
     */
    PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException;
}
