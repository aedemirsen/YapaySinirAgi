/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniragi;

import java.util.Random;

/**
 *
 * @author aedemirsen
 */
public class Fonksiyon {

    public static float[] stringToFloatArray(String[] s) {
        if (s != null) {
            float f[] = new float[s.length];
            for (int i = 0; i < s.length; i++) {
                f[i] = Float.parseFloat(s[i]);
            }
            return f;
        }
        return null;
    }
    
    public float randomFloat(float taban,float tavan){
        return new Random().nextFloat() * ((tavan - taban) + taban);
    }

    public float sigmoid(float f) {
        return (float) (1 / (1 + Math.exp(-f)));
    }
    
    float [][] sistemiNormalizeEt(float x[][]){
        float max = sisteminMaxDegeri(x);
        for (int i = 0; i < x[i].length; i++) {
            for (int j = 0; j < x.length; j++) {
                x[j][i] /= max;
            }
        }
        return x;
    }
    
    float sisteminMaxDegeri(float [][] x){
        float max = x[0][0];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                if (max < x[i][j]) {
                    max = x[i][j];
                }
            }
        }
        return max;
    }
    
//    public static void main(String[] args) {
//        System.out.println(randomFloat(0,1));
//    }
}
