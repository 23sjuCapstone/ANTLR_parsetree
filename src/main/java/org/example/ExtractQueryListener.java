package org.example;

import org.example.gen.MySqlParserBaseListener;
import org.example.grammer.MySqlParser;

public class ExtractQueryListener extends MySqlParserBaseListener {

    private String[] query = new String[10];
    private int order = 0;
    public int count=0;

    @Override
    public void enterQuerySpecification(MySqlParser.QuerySpecificationContext ctx){
        //System.out.println("ENTER QUERY SPECIFICATION");
        String string = "";
        String[] word = new String[50];

        int idx = 0;
        word[idx++] = ctx.SELECT().getText();
        word[idx++] = ctx.selectElements().selectElement(0).getText();
        word[idx++] = ctx.fromClause().FROM().getText();  // fromClause 안에 Where 절 조작 함수들 포함 되어 있음
        word[idx++] = ctx.fromClause().tableSources().tableSource(0).getText(); // table 혹시 여러개면 tableSource(int i) 함수 말고, List<TableSourceContext> 반환하는 tableSource() 사용 ??
        word[idx++] = ctx.fromClause().WHERE().getText();
        word[idx++] = ctx.fromClause().expression().getText();
//        if(count == 1)
//            word[idx++] = query[1];
//        else
//            word[idx++] = ctx.fromClause().expression().getText();

        // 여기서 쿼리문의 종류에 따라 분기 ?? (지금은 in predicate 인 경우) > 분기가 가능한가 > 일단은 이후 쿼리를 앞 쿼리에 붙이는 방식으로 진행 ..(한정적임)
        //System.out.printf("size of children : " + String.valueOf(ctx.fromClause().expression().children.size()));
//        for(int i = 0;i<pt.size();i++){
//            System.out.printf(pt.get(i).getText());
//            System.out.printf("one word\n");
//        }
//        for(int i = 0;i < children.size();i++){
//            word[idx++] = children.get(i).getText();
//        }
        //word[idx++] = ctx.fromClause().expression().children.toString(); // 더 세부적으로 나눠야 할 것 같은데 > 세부적으로 나누지 않아도 전체 where절 전체 출력
//        System.out.println(idx);
        for(int i=0; i < idx; i++){
            string += word[i]+" ";
        }

        query[order++] = string;         // expression이 띄어쓰기 없이 나오는 거 수정해야 하는데 ...  >> *** 트리 순회하는 방법에 대해서 더 조사 해봐야 할듯..***
//        System.out.println(count);
//        if(count == 0){
//            count = 1;
//            query[1]= string;
//        }
//        else
//            query[0] = string;
    }

    @Override
    public void exitQuerySpecification(MySqlParser.QuerySpecificationContext ctx) {

    }
//    public void giveCountNum(int count){
//        this.count = count;
//        System.out.print(count);
//    }

    public String[] returnQuery(){
        return query;
    }

}
