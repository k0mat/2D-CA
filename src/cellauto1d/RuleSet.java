/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellauto1d;

import java.util.ArrayList;
/**
 *
 * @author mateusz
 */
public class RuleSet {

    private int ruleNumber = 0;
    
    public RuleSet(int ruleNumber) {
        this.ruleNumber = ruleNumber;
    }

    public ArrayList<Integer> nextGeneration(ArrayList<Integer> gen) {
        ArrayList<Integer> next = new ArrayList<>(gen.size());
        for (int i = 0; i < gen.size(); ++i) {
            next.add(getChildValue(gen, i));
        }
        return next;
    }

    private int getChildValue(ArrayList<Integer> gen, int i) {
        int sum = 0;
        for (int j = i - 1; j < i + 2; ++j) {
            if (j < 0 || j >= gen.size()) {
                //binS += '0';
            } else {
                sum += gen.get(j);
            }
        }
        String c = "" + Integer.toString(ruleNumber, 3).charAt(6 - sum);;
 
        return Integer.valueOf(c);
    }
}
