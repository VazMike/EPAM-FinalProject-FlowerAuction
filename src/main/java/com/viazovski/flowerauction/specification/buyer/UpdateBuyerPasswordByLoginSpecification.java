package com.viazovski.flowerauction.specification.buyer;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateBuyerPasswordByLoginSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "UPDATE `buyer` " +
            "SET `password_hash` = ? " +
            "WHERE `login` = ?";

    private String login;

    private String passwordHash;

    public UpdateBuyerPasswordByLoginSpecification(String login, String passwordHash) {
        this.login = login;
        this.passwordHash = passwordHash;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        var preparedStatement = statementGetter.apply(QUERY);
        preparedStatement.setString(1, passwordHash);
        preparedStatement.setString(2, login);
        return preparedStatement;
    }
}
