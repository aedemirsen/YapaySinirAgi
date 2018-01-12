/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniragi;

import java.io.IOException;

/**
 *
 * @author aedemirsen
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        Fonksiyon f = new Fonksiyon();
        
        //girdi
        float[][] girdiler = f.sistemiNormalizeEt(DosyaIslem.girdileriOku("data.txt"));
        System.out.println("Girdi satır sayısı = " + girdiler.length);
        System.out.println("Girdi sütun sayısı = " + girdiler[0].length);
        System.out.println("Toplam girdi sayısı = " + girdiler.length * girdiler[0].length);
        //cikti
        int[] sonuclar = DosyaIslem.ciktilariOku("result.txt");
        
        SinirAgi sinirAgi = new SinirAgi(girdiler, sonuclar);
        sinirAgi.setIterasyon(10000);
        sinirAgi.ogren();
        
                
        Sonuc sonuc = new Sonuc(sinirAgi);

        //test datası
        float[][] a = f.sistemiNormalizeEt(DosyaIslem.girdileriOku("test.txt"));
        System.out.println("Test datası satır sayısı = " + a.length);
        System.out.println("Test datası sütun sayısı = " + a[0].length);
        System.out.println("Toplam test data sayısı = " + a.length * a[0].length);
        
        sonuc.testEt(a);
        
       
        

    }

}
