/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniragi;

/**
 *
 * @author aedemirsen
 */
public class Sonuc {

    private SinirAgi sinirAgi;

    Sonuc(SinirAgi sinirAgi) {
        this.sinirAgi = sinirAgi;
    }

    public void testEt(float[][] f) {
        for (int i = 0; i < f.length; i++) {
            float[] valueToPredict = f[i];
            //System.out.println("Başarı Oranı: " + getBasariOrani());
            System.out.println("Predicted result: " + parseSonuc(sinirAgi.calculateFOut(valueToPredict)));
        }
    }

    public Integer parseSonuc(float sonuc) {
        return (sonuc < 0.5) ? 0 : 1;
    }

}
