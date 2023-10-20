package org.example;

import org.example.gen.MySqlParserBaseListener;
import org.example.grammer.MySqlParser;

public class CountSqlSubquery extends MySqlParserBaseListener {
    private int startRuleCount = 0;
    @Override
    public void enterQuerySpecification(MySqlParser.QuerySpecificationContext ctx){
        startRuleCount++;
    }
    @Override
    public void enterQuerySpecificationNointo(MySqlParser.QuerySpecificationNointoContext ctx){
        startRuleCount++;
    }

    public int getStartRuleCount(){
        return startRuleCount;
    }
}
