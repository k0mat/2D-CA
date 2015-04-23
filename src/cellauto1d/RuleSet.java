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

    private short ruleNumber = 0;

    public RuleSet(short ruleNumber) {
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
        String binS = "0";
        for (int j = i - 1; j < i + 2; ++j) {
            // TODO: add more wrapping options 
            //System.out.println("j" + j);
            if (j < 0 || j >= gen.size()) {
                binS += '0';
            } else {
                binS += gen.get(j);
            }
        }
        //System.out.println("binS: " + binS);
        byte bin = Byte.valueOf(binS, 2);
        //System.out.println("bin: " + bin);
        int child = ((ruleNumber >> bin) % 2);
        //System.out.println("child: " + child);
        if (child == 1) {
            return 1;
        } else {
            return 0;
        }
    }
}
