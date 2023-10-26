package org.example;

import org.example.gen.MySqlParserBaseVisitor;

public class ExtractQueryVisitor extends MySqlParserBaseVisitor {

//    public String visitSubqueryExpressionAtom(MySqlParser.SubqueryExpressionAtomContext ctx, int num){
//        String string = "";
//        String[] word = new String[50];
//
//        int idx = 0;
//        word[idx++] = ctx.querySpecification().SELECT().getText();
//        word[idx++] = ctx.querySpecification().selectElements().selectElement(0).getText();
//        word[idx++] = ctx.querySpecification().fromClause().FROM().getText();  // fromClause 안에 Where 절 조작 함수들 포함 되어 있음
//        word[idx++] = ctx.querySpecification().fromClause().tableSources().tableSource(0).getText(); // table 혹시 여러개면 tableSource(int i) 함수 말고, List<TableSourceContext> 반환하는 tableSource() 사용 ??
//        word[idx++] = ctx.querySpecification().fromClause().WHERE().getText();
//        return "";
//    }
}
