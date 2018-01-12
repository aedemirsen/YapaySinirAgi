/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniragi;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author aedemirsen
 */
public class DosyaIslem {

    public static float[][] girdileriOku(String dosya) throws IOException {
        float[][] girdiler;
        List<String> satirlar;
        satirlar = Files.readAllLines(Paths.get(dosya), StandardCharsets.UTF_8);
        girdiler = new float[satirlar.size()][];
        for (int i = 0; i < satirlar.size(); i++) {
            girdiler[i] = Fonksiyon.stringToFloatArray(satirlar.get(i).split("-"));
        }
        return girdiler;
    }

    public static int[] ciktilariOku(String dosya) throws IOException {
        int[] ciktilar;
        List<String> satirlar;
        satirlar = Files.readAllLines(Paths.get(dosya), StandardCharsets.UTF_8);
        ciktilar = new int[satirlar.size()];
        for (int i = 0; i < satirlar.size(); i++) {
            ciktilar[i] = Integer.parseInt(satirlar.get(i));
        }
        return ciktilar;
    }

}
