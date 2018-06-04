package com.viazovski.flowerauction.specification.buyer;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateBuyerLocaleSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "UPDATE `buyer` " +
            "SET `lang` = ? " +
            "WHERE `login` = ?";

    private String locale;

    private String login;

    public UpdateBuyerLocaleSpecification(String locale, String login) {
        this.locale = locale;
        this.login = login;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        var preparedStatement = statementGetter.apply(QUERY);
        preparedStatement.setString(1, locale);
        preparedStatement.setString(2, login);
        return preparedStatement;
    }
}
