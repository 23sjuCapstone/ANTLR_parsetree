package org.example;

import org.example.gen.MySqlParserBaseListener;
import org.example.grammer.MySqlParser;

public class CountQueryListener extends MySqlParserBaseListener {
    private int startRuleCount = 0;
    @Override
    public void enterQuerySpecification(MySqlParser.QuerySpecificationContext ctx){
        startRuleCount++;
        System.out.println("enterQuerySpecification");
    }
    @Override
    public void enterQuerySpecificationNointo(MySqlParser.QuerySpecificationNointoContext ctx){
        startRuleCount++;
        System.out.println("enterQuerySpecificationNOINTO");
    }

    @Override
    public void enterUnionStatement(MySqlParser.UnionStatementContext ctx){
        startRuleCount++;
        System.out.println("enterUnionStatement");
    }

    public int getStartRuleCount(){
        return startRuleCount;
    }
}
