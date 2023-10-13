package org.example;

// (Youtube) Antlr Beginner Tutorial 2: Integrating Antrlr in Java Project

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

import org.gen.grammer.MySqlLexer;

// Lexer 파일 생성 -> Tokenize -> Parser 파일 생성
// 생성된 파스트리를 객체로 사용하기 위해서는 antlr4-tools를 설치해야 하는데
// https://github.com/antlr/antlr4/blob/master/doc/getting-started.md
// 위의 링크 참조(윈도우는 파이썬 3.11 버전을 설치해야 함
// gradle 파일에 antlr dependency 추가

//gen 디렉토리 refactor -> lexer class 생성 되는지
// github에 파싱트리 레포 파기

public class Main {
    public static void main(String[] args) throws IOException {
        // #1. (Youtube) Antlr Beginner Tutorial 2: Integrating Antrlr in Java Project

        // creating ANTLR related objects
        // input : Charstream  >> static helper methods where we can create a char stream from a file

//        CharStream charStream = CharStreams.fromString("SELECT branch_name, customer_name FROM depositor, account WHERE depositor.account_number = account.account_number UNION SELECT branch_name, customer_name FROM borrower, loan WHERE borrower.loan_number = loan.loan_number;");
//        MySqlLexer mySqlLexer = new MySqlLexer(charStream);
//        CommonTokenStream commonTokenStream = new CommonTokenStream(mySqlLexer);
//        MySqlParser mySqlParser = new MySqlParser(commonTokenStream);

        // 이제 파서에서 translationunit을 부를 수 있다 ! > parsetree를 반환값으로 준다
//        ParseTree parseTree = mySqlParser.translationunit();
        System.out.println("done");

        // #2. (Stack overflow) Is there a simple example of using the ParseTreeWalker?
        ParseTreeWalker walker = new ParseTreeWalker();  // Antlr4의 새로운 클래스

    }
}