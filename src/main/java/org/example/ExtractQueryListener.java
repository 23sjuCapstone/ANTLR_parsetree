package org.example;

import org.example.gen.MySqlParserBaseListener;
import org.example.grammer.MySqlParser;

public class ExtractQueryListener extends MySqlParserBaseListener {

    private String[] query = new String[10];

    @Override
    public void enterQuerySpecification(MySqlParser.QuerySpecificationContext ctx){
        System.out.println("ENTER QUERY SPECIFICATION");

        String string = "";
        String[] word = new String[50];

        int idx = 0;
        word[idx++] = ctx.SELECT().getText();
        word[idx++] = ctx.selectElements().selectElement(0).getText();
        word[idx++] = ctx.fromClause().FROM().getText();  // fromClause 안에 Where 절 조작 함수들 포함 되어 있음
        word[idx++] = ctx.fromClause().tableSources().tableSource(0).getText(); // table 혹시 여러개면 tableSource(int i) 함수 말고, List<TableSourceContext> 반환하는 tableSource() 사용 ??
        word[idx++] = ctx.fromClause().WHERE().getText();
        word[idx++] = ctx.fromClause().expression().getText(); // 더 세부적으로 나눠야 할 것 같은데 > 세부적으로 나누지 않아도 전체 where절 전체 출력

        for(int i=0; i < idx; i++){
            string += word[i]+" ";
        }
        query[0]= string;
    }

    @Override
    public void exitQuerySpecification(MySqlParser.QuerySpecificationContext ctx) {

    }

    public String returnQuery(){
        return query[0];
    }

}
