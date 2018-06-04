package com.viazovski.flowerauction.specification.buyerauction.crud;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectAllBuyerAuctionsSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY = "SELECT * FROM `buyer_auction`";

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        return statementGetter.apply(QUERY);
    }
}
