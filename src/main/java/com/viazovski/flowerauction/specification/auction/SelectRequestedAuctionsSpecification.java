package com.viazovski.flowerauction.specification.auction;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectRequestedAuctionsSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "SELECT * FROM `auction` " +
            "WHERE `auction_id` IN (" +
                "SELECT DISTINCT `auction_id` FROM `buyer_auction` " +
                "WHERE `buyer_accepted` = FALSE) " +
            "OR `auction_id` IN (" +
                "SELECT DISTINCT `auction_id` FROM `flower` " +
                "WHERE `auction_id` IS NOT NULL AND `flower_accepted` = FALSE)";

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        return statementGetter.apply(QUERY);
    }
}
