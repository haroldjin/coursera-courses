package com.haroldjin.algorithms.stack;

import org.junit.Assert;
import org.junit.Test;

public class BalancedParenthesesTest {
    @Test
    public void balancedParentheses_shouldReturnTrue(){
        char[] parens = {'{','(',')','}','[',']'};
        BalancedParentheses balancedParenthesis = new BalancedParentheses(parens);
        Assert.assertTrue(balancedParenthesis.isBalanced());
    }

    @Test
    public void unbalancedParentheses_shouldReturnFalse(){
        char[] parens = {'{', '[', ']'};
        BalancedParentheses balancedParenthesis = new BalancedParentheses(parens);
        Assert.assertFalse(balancedParenthesis.isBalanced());
    }
}
