package com.niceshot.jdk.spel;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;

public class SpringELTest {
    @Test
    public void test(){
        ExpressionParser parser = new SpelExpressionParser();
        /*List numbers = (List) parser.parseExpression("{1,2,3,4}").getValue();
        User user = (User) parser.parseExpression("new com.niceshot.jdk.spel.SpringELTest.User('陈俊',30)").getValue();
        Expression expression = parser.parseExpression("");
        Object value = expression.getValue();
        System.out.println();*/

        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("newName", "Mike Tesla");

        String value1 = parser.parseExpression("#newName").getValue(context, String.class);
        System.out.println();


        String name = parser.parseExpression("false ? 'trueExp' : 'falseExp'").getValue(String.class);

    }

    public static class User{
        private String name;
        private Integer age;

        public User(String name,Integer age) {
            this.name = name;
            this.age = age;
        }
    }
}
