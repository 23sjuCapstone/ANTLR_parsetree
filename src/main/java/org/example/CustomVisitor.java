package org.example;

import org.example.gen.MySqlParserBaseVisitor;
import org.example.grammer.MySqlParser;

import java.util.HashMap;
import java.util.Map;

public class CustomVisitor extends MySqlParserBaseVisitor {

    @Override
    public Object visitRoot(MySqlParser.RootContext ctx){
        return visitChildren(ctx.parent);
    }

    @Override
    public Map<String, String> visitQuerySpecification(MySqlParser.QuerySpecificationContext ctx){
        Map<String, String> keyValue = new HashMap<>();

        String keyword = ctx.SELECT().getText();
        String colname = ctx.selectElements().selectElement(0).getText();
        String tablename = ctx.fromClause().tableSources().tableSource(0).getText();

        keyValue.put("keyword", keyword);
        keyValue.put("column", colname);
        keyValue.put("table", tablename);

        return keyValue;
    }


}
