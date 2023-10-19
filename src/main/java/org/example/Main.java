package org.example;

// (Youtube) Antlr Beginner Tutorial 2: Integrating Antrlr in Java Project

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.example.gen.MySqlLexer;
import org.example.grammer.MySqlParser;

import java.io.IOException;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws IOException {


        CharStream charStream = CharStreams.fromString("SELECT row1 FROM depositor;");
        MySqlLexer mySqlLexer = new MySqlLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(mySqlLexer);
        MySqlParser mySqlParser = new MySqlParser(commonTokenStream);

        MySqlParser.QuerySpecificationContext parseTree = mySqlParser.querySpecification();
        CustomVisitor visitor = new CustomVisitor();

        Map<String, String> map = visitor.visitQuerySpecification(parseTree);
        map.forEach((key, value) -> {System.out.println(key + " : " + value);});


    }
}