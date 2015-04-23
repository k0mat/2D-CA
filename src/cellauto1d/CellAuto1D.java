/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellauto1d;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 *
 * @author mateusz
 */
public class CellAuto1D {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int timeLimit = 51;
        int size = 51;
        short rule = 30;

        // position of 1 value bits, relative to the middle
        // TODO: load from file
        Integer[] start = {0};

        //read variables from arguments
        for (int i = 0; i < args.length; ++i) {
            String var = args[i];
            switch (var) {
                case "-t":
                    timeLimit = Integer.valueOf(args[++i]);
                    break;
                case "-s":
                    size = Integer.valueOf(args[++i]);
                    break;
                case "-rule":
                    rule = Short.valueOf(args[++i]);
                    break;
            }
        }

        RuleSet rules = new RuleSet(rule);

        //calculate absolute positions
        int middle = size / 2;
        for (int i = 0; i < start.length; ++i) {
            start[i] += middle;
        }

        ArrayList<ArrayList<Integer>> generations = new ArrayList<>(timeLimit);

        ArrayList<Integer> firstGeneration = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            if (Arrays.asList(start).contains(i)) {
                firstGeneration.add(1);
            } else {
                firstGeneration.add(0);
            }
        }

        generations.add(firstGeneration);

        for (int i = 1; i < timeLimit; ++i) {
            ArrayList<Integer> last = generations.get(generations.size() - 1);
            generations.add(rules.nextGeneration(last));
        }

        saveImage(generations, size, timeLimit);
    }

    private static void saveImage(ArrayList<ArrayList<Integer>> generations, int width, int height) {
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_BYTE_BINARY);

        for(int i = 0; i < height; ++i){
            ArrayList<Integer> gen = generations.get(i);
            for(int j = 0; j < width; ++j){
                image.setRGB(j, i, Integer.MAX_VALUE - gen.get(j) * Integer.MAX_VALUE);
            }
        }
        
        try {
            File outputfile = new File("saved.png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            System.err.println("error");
        }
    }

}