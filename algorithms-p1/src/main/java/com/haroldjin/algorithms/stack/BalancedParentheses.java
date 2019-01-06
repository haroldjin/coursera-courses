package com.haroldjin.algorithms.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BalancedParentheses {

    private static Map<Character, Character> parenPairs = new HashMap<>();

    static {
        parenPairs.put('{', '}');
        parenPairs.put('[', ']');
        parenPairs.put('(', ')');
    }

    private  char[] parens;

    public BalancedParentheses(char[] parens){
        this.parens = parens;
    }

    public boolean isBalanced(){
        Stack<Character> openParenStack = new Stack<>();

        for(Character character : this.parens){
            if (parenPairs.keySet().contains(character)){
                openParenStack.push(character);
            } else {
                Character openParen = openParenStack.pop();
                if (parenPairs.get(openParen) != character){
                    return false;
                }
            }
        }

        if (openParenStack.size() == 0)
            return true;
        else
            return false;
    }
}
