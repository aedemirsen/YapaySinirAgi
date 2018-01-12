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
public class SinirAgi {

    private final Fonksiyon fonksiyon;
    private final float[][] girdiler;
    private final int[] sonuclar;

    private final int ciktiSayisi;
    private final int dimension;

    private final float[] bias;
    private final float[] vWeights;
    private final float[][] wWeights;
    private float fOut;
    private float[] fOutArray;
    private final int neurons;
    private float bOut;

    private int iterationsLimit;
    

    public SinirAgi(float[][] girdiler, int[] sonuclar) {

        fonksiyon = new Fonksiyon();
        this.girdiler = girdiler;
        this.sonuclar = sonuclar;
        
        iterationsLimit = 10000;//default

        this.dimension = girdiler[0].length;
        this.neurons = dimension;

        this.bias = new float[neurons];
        this.vWeights = new float[neurons];
        this.wWeights = new float[dimension][neurons];
        this.fOutArray = new float[neurons];

        agirliklariBelirle(neurons, dimension);

        bOut = fonksiyon.randomFloat(0f, 1f);
        // Default transfer function
        // this.transferFunction = new SigmoidFunction();
        // Default result parser

        this.ciktiSayisi = sonuclar.length;
    }

    private void agirliklariBelirle(int n, int b) {
        if (n == 0 || b == 0) {
            return;
        }
        for (int i = 0; i < n; i++) {
            this.bias[i] = fonksiyon.randomFloat(0f, 1f);
            this.vWeights[i] = fonksiyon.randomFloat(0f, 1f);
            for (int j = 0; j < b; j++) {
                this.wWeights[j][i] = fonksiyon.randomFloat(0f, 1f);
            }
        }
    }

    void ogren() {
        float quadraticError = 0;
        float[] f;
        int basari = 0;
        for (int i = 0; i < iterationsLimit; i++) {
            basari = 0;
            for (int z = 0; z < ciktiSayisi; z++) {
                calculateFOut(satirDegerleri(z));
                // f = fOutArray;
                hazirlan(sonuclar[z], satirDegerleri(z));
                //learner = new Learner(sonuclar[z], fOut, fOutArray, vWeights, wWeights, bias, bOut, neurons, getRowElements(z), dimension);
//                vWeights = learner.getVWeights();
//                wWeights = learner.getWWeights();
//                bias = learner.getBias();
//                bOut = learner.getBOut();
                if ((fOut < 0.5 && sonuclar[z] == 0) || (fOut >= 0.5 && sonuclar[z] == 1))
                    basari += 1;                
               // basari = resultParser.countSuccesses(basari, fOut, sonuclar[z]);
                quadraticError += Math.pow(((sonuclar[z] - fOut)), 2);
            }
            quadraticError *= 0.5f;
        }
        float basariOrani = (basari / (float) ciktiSayisi) * 100;
        
        //Sonuc sonuc = new Sonuc(basariOrani, quadraticError);
       // neuralNetworkCallback.success(result);
    }

    private void hazirlan(float f, float[] x) {
        float error = f - fOut;
        float n = 0.05f;
        float dv;
        float[] dwi = new float[neurons];
        float[][] dw = new float[dimension][neurons];
        float[] dbi = new float[neurons];
        float[] db = new float[neurons];

        // Modify v weights
        dv = fOut * (1 - fOut) * error;
        for (int i = 0; i < neurons; i++) {
            this.vWeights[i] = vWeights[i] + n * dv * fOutArray[i];
        }

        // Modify bias out
        float dbOut = n * dv * 1;
        this.bOut = (bOut + dbOut);

        // Modify w weights
        for (int i = 0; i < neurons; i++) {
            dwi[i] = fOutArray[i] * (1 - fOutArray[i]) * vWeights[i] * dv;
            for (int j = 0; j < dimension; j++) {
                dw[j][i] = n * dwi[i] * x[j];
                this.wWeights[j][i] = wWeights[j][i] + dw[j][i];
            }
        }

        // Modify bias
        for (int i = 0; i < neurons; i++) {
            dbi[i] = fOutArray[i] * (1 - fOutArray[i]) * vWeights[i] * dv;
            db[i] = n * dbi[i] * 1;
            this.bias[i] = bias[i] + db[i];
        }

    }

    float calculateFOut(float[] x) {
        for (int i = 0; i < neurons; i++) {
            float sum = 0;
            for (int j = 0; j < dimension; j++) {
                sum = sum + (x[j] * wWeights[j][i]);
            }
            this.fOutArray[i] = fonksiyon.sigmoid(sum + bias[i]);
        }
        this.fOut = 0;
        for (int i = 0; i < neurons; i++) {
            this.fOut += fOutArray[i] * vWeights[i];
        }
        return fonksiyon.sigmoid(fOut + bOut);
    }
    

    private float[] satirDegerleri(int satirNo) {
        float[] f = new float[dimension];
        for (int i = 0; i < dimension; i++) {
            f[i] = this.girdiler[satirNo][i];
        }
        return f;
    }
    
    public void setIterasyon(int i){
        this.iterationsLimit = i;
        System.out.println("İterasyon sayısı = " + i);
    }


}
