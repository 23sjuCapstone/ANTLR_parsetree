package org.example;

// (Youtube) Antlr Beginner Tutorial 2: Integrating Antrlr in Java Project

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.example.gen.MySqlLexer;
import org.example.grammer.MySqlParser;

import java.io.IOException;
import java.util.Map;


public class Main {

    public static String[] makeSteps(MySqlParser mySqlparser, CustomVisitor visitor){
        String[] subquery = null;


        return subquery;
    }


    public static void main(String[] args) throws IOException {


        CharStream charStream = CharStreams.fromString("SELECT branch_name FROM SELECT branch_name FROM depositor;");
        MySqlLexer mySqlLexer = new MySqlLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(mySqlLexer);
        MySqlParser mySqlParser = new MySqlParser(commonTokenStream);

        // 1. 서브쿼리가 몇 개인지 파악  :: 1개이면 그냥 한개에 대한 구성요소 파악으로 넘어가게
        // 2. 서브쿼리 순서대로 output 만들기  ::  1) 2) 3)
        // 3. 각 서브쿼리의 구성요소를 key value 형식으로 output 만들기

        // 어떻게 모듈을 나눌 것인지 (유지 보수가 용이한 단위로 나누기)
        // 우리 백엔드에 합치기 수월한 방향으로 .. ?



        MySqlParser.QuerySpecificationContext parseTree = mySqlParser.querySpecification();  // mysqlParser.시작룰
        CustomVisitor visitor = new CustomVisitor();

        Map<String, String> map = visitor.visitQuerySpecification(parseTree);
        map.forEach((key, value) -> {System.out.println(key + " : " + value);});


        // count steps.. subquery의 개수 출력
//        MySqlParser.QuerySpecificationContext parseTree = mySqlParser.querySpecification();  // mysqlParser.시작룰

        MySqlParser.RootContext parser = mySqlParser.root();
        ParseTree tree = parser.getParent();
        CountSqlSubquery visitor2 = new CountSqlSubquery();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(visitor2, parseTree);
        int startRuleCount = visitor2.getStartRuleCount();
        System.out.println(startRuleCount);

    }
}