package org.example;

// (Youtube) Antlr Beginner Tutorial 2: Integrating Antrlr in Java Project

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.example.gen.MySqlLexer;
import org.example.gen.MySqlParser;
import org.json.JSONObject;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // 쿼리문이 단순할 때  >  서브 쿼리문이 1개일 때(총 쿼리문이 2개 일 때)  >  서브 쿼리문이 2개 일 때 (Select ~ Union Select ~)
        // 일단은 쿼리문이 단순한 SELECT 문인 경우에 대해 3 단계를 완성해보자 !

        // <필요한 3단계>
        // 1. 쿼리가 몇 개인지 파악 V
        // 2. 쿼리 단계별로 output 만들기 (단계별로 만들어야 하는데 순회는
        // 3. 각 쿼리의 구성 요소(column, table, 명령어(?) . . .)를 key value 형식으로 output 만들기


        // 1. 쿼리가 몇 개 인지 파악
        System.out.println("<<step 1>>");
        CharStream charStream = CharStreams.fromString("SELECT loan_number FROM borrower WHERE customer_name = (SELECT customer_name FROM depositor WHERE account_number = \"A-215\");");
        MySqlLexer mySqlLexer = new MySqlLexer(charStream);  // 구조 자동으로 변경해 주는 노란선 어떡할까
        CommonTokenStream commonTokenStream = new CommonTokenStream(mySqlLexer);
        MySqlParser mySqlParser = new MySqlParser(commonTokenStream);
//        // count steps.. subquery의 개수 출력
//        MySqlParser.QuerySpecificationContext parseTree = mySqlParser.querySpecification();  // mysqlParser.시작룰(enterRule함수)
//        MySqlParser.SqlStatementsContext tree = mySqlParser.sqlStatements();
//        MySqlParser.SqlStatementContext tree = mySqlParser.sqlStatement();
//        MySqlParser.UnionStatementContext tree = mySqlParser.unionStatement();  // Union 쿼리문은 unionStatement로 startrule 함수 부분 설정하니까 워닝 안생김
//        MySqlParser.SelectStatementContext tree = mySqlParser.selectStatement();
        MySqlParser.RootContext tree = mySqlParser.root();

        CountQueryListener listener = new CountQueryListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        int startRuleCount = listener.getStartRuleCount();  // 내가 전체 트리를 순회한다고 생각하면서 짠 코드가 트리 전체를 순회하는 코드가 아닌지 다시 확인
        System.out.println(startRuleCount);

        // 위에 요소에 접근하는 코드와 관련이 있는 것이었다 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!***
        // 단계별 요구 사항을 실행할 때마다 매번 파스 트리를 생성하는 과정을 거쳐야 하는가 > 그런가보다 (사실 더 효율적인 방법이 있을 거 같긴 함;)




        // 2. 쿼리 단계 별로 output string 만들기 (이거는 쿼리 2개인 경우부터 생각해 보는게 나을 듯)

        System.out.println("\n<<step 2>>");
        CharStream charStream1 = CharStreams.fromString("SELECT loan_number FROM borrower WHERE customer_name = (SELECT customer_name FROM depositor WHERE account_number = \"A-215\");");
        MySqlLexer mySqlLexer1 = new MySqlLexer(charStream1);
        CommonTokenStream commonTokenStream1 = new CommonTokenStream(mySqlLexer1);
        MySqlParser mySqlParser1= new MySqlParser(commonTokenStream1);

        //MySqlParser.RootContext tree2 = mySqlParser.root();  // **** startRule 정확하지 않을 수 있음 !!


        // 그냥 트리 상단부터 생짜 구현
//        MySqlParser.RootContext tree2 = mySqlParser1.root();
////        MySqlParser.QuerySpecificationContext tree2 = mySqlParser1.querySpecification();
//        ExtractQueryVisitor visitor = new ExtractQueryVisitor();
//        MySqlParser.ExpressionAtomContext tree_2 = tree2.getRuleContext(MySqlParser.ExpressionAtomContext.class, 0);
//        String st = visitor.visitExpressionAtomPredicate(tree_2);
////        String st = visitor.visitExpressionAtomPredicate(tree2);


        // ExtractQuery Visitor로 시도한 버전 (VisitExpressionAtom 시도한 버전.. StartRule 때문인지 뭐 떄문에 안되는 걸까 !!!!)
        // MySqlParser.ExpressionAtomContext tree3 = mySqlParser1.expressionAtom();   // [error] : line 1:7 mismatched input 'loan_number' expecting {'.', DOT_ID}
//        MySqlParser.QuerySpecificationContext tree3 = mySqlParser1.querySpecification();  // 윗줄의 에러는 해소됨, ; 출력 안됌
        //MySqlParser.RootContext tree3 = mySqlParser1.root();  // 출력 끝에 ; 랑 <EOF> 가 같이 출력
        //MySqlParser.RootContext tree3 = mySqlParser1.root();
//        MySqlParser.SelectStatementContext tree3 = mySqlParser1.selectStatement();

//        tree3.getRuleContext(MySqlParser.SubqueryExpressionAtomContext.class, 0);
          // mysqlParser.시작룰  *** 이걸 적당하게 인자로 가져다 줘야 하는데 . . . .
//        ExtractQueryVisitor visitor = eac.getR  // getParent해서 다시 내려오는 방법??
//        ExtractQueryVisitor visitor = new ExtractQueryVisitor();

//        ParseTree pt = tree3.getChild(1);
//        System.out.println(pt.getText());
//        ParseTree subtree = pt.getChild(1);
//        System.out.println(subtree.getText());

//        String str = visitor.visitSubqueryExpressionAtom(tree3.getRuleContext(MySqlParser.SubqueryExpressionAtomContext.class, 0));
//        System.out.println(str);



        // ExtractQuery Listener로 시도한 버전
        ExtractQueryListener listener1 = new ExtractQueryListener();
//        ParseTreeWalker walker1 = new ParseTreeWalker();
//        walker1.walk(listener1, tree2);
        walker.walk(listener1, tree);  // tree는 1단계에서 사용한 tree를 사용해도 됨(새로 만들면 오류 남 ;; 왜 그런건지는 모르겠음 ;;)// /  listener는 새로 만들기
//        listener1.giveCountNum(listener.startRuleCount);
        String[] query = listener1.returnQuery();

        for(int i=0;i<query.length;i++){
            if (query[i]!= null)
                System.out.println(query[i]);
        }


        // 3. 쿼리의 구성 요소 key value 형식으로 뽑아내기
        System.out.println("\n<<step 3>>");
        CharStream charStream2 = CharStreams.fromString("SELECT row1 FROM depositor;");
        MySqlLexer mySqlLexer2 = new MySqlLexer(charStream2);
        CommonTokenStream commonTokenStream2 = new CommonTokenStream(mySqlLexer2);
        MySqlParser mySqlParser2 = new MySqlParser(commonTokenStream2);

        ComponentListener listener2 = new ComponentListener();
        walker.walk(listener2, tree);

        JSONObject[] component = listener2.returnComponents();

        for(int i=0;i<component.length;i++){
            if (component[i]!= null)
                System.out.println(component[i]);
        }
    }
}