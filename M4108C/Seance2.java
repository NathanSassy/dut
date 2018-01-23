import java.lang.reflect.Array;
import java.util.Scanner;

public class Seance2 extends Seance1 {

    public Seance2(int width, int height) {
        super(width, height);
    }

    public Seance2(Image i) {
        super(i);
    }

    public Seance2(String filename) {
        super(filename);
    }

    public Image seuillage(int t) {
        Image ret = new Image(this);
        for(int i = 0; i < ret.pixels.length; i++) {
            if(ret.pixels[i] < t)
                ret.pixels[i] = 0;
            else
                ret.pixels[i] = 255;
        }
        return ret;
    }

    public int getOtsuScale() {
        Seance2 ret = new Seance2(this);
        double normalizedHistogram[] = ret.normalizedHistogram();
        int n = ret.width * ret.height;
        double bestVariance = 0;
        int t = 0;

        for(int i = 0; i < normalizedHistogram.length; i++) {
            double moyA = 0;
            double freqA = 0;
            double moyB = 0;
            double freqB = 0;

            for(int j = 0; j < ret.pixels.length; j++) {
                if(ret.pixels[j] < 0 || ret.pixels[j] > 255) continue;

                if(ret.pixels[j] < i)
                    moyA += ret.pixels[j];
                else
                    moyB += ret.pixels[j];
            };
            moyA /= n;
            moyB /= n;

            for(int j = 0; j < normalizedHistogram.length; j++) {
                if(j < i)
                    freqA += normalizedHistogram[j];
                else
                    freqB += normalizedHistogram[j];
            }

            double variance = freqA * freqB * Math.sqrt(Math.abs(moyB - moyA));
            if(variance > bestVariance) {
                bestVariance = variance;
                t = i;
            }
        }

        return t;
    }

    public Image otsu() {
        Seance2 ret = new Seance2(this);
        return ret.seuillage(ret.getOtsuScale());
    }

    public Image iterativeSelectionThresholding() {
        Seance2 ret = new Seance2(this);
        double normalizedHistogram[] = ret.normalizedHistogram();
        int intScaleNew = normalizedHistogram.length / 2;
        int intScale = 0;
        int n = ret.width * ret.height;
        double moyA = 0;
        double moyB = 0;

        do {
            intScale = intScaleNew;
            for(int i = 0; i < n; i++) {
                if(ret.pixels[i] < intScale)
                    moyA += ret.pixels[i];
                else
                    moyB += ret.pixels[i];
            }
            moyA /= n;
            moyB /= n;

            intScaleNew = (int) (moyA + moyB) / 2;
        } while (intScaleNew != intScale);

        return ret.seuillage(intScale);
    }

    public Image otsuZone(int pixel, int size) {
        Seance2 image = new Seance2(size * 2, size * 2);
        int fp = pixel - (size/2) - (this.width * (size / 2));
        int lp = pixel + (size/2) + (this.width * (size / 2));

        System.out.println("image size = " + image.pixels.length);
        System.out.println("this size = " + this.pixels.length);

        System.out.println("fp = " + fp);
        System.out.println("lp = " + lp);


        for(int i = 0; i < image.pixels.length; i++) {
            image.pixels[i] = fp
        }


        /*for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                int p = fp + i + j * this.width;
                if(p > 0 && p < this.pixels.length)
                    image.pixels[i + image.width * j] = this.pixels[j];
                else
                    image.pixels[i + image.width * j] = -1;
            }
        }*/



        /*for(int i = fp; (i-fp) < image.pixels.length; i++) {
            System.out.println("i = " + i);
            System.out.println("i - fp = " + (i - fp));

            if(i > 0 && i < this.pixels.length)
                image.pixels[i - fp] = this.pixels[i];
            else
                image.pixels[i - fp] = -1;
        }*/

        image.display();

        return new Seance2(this).seuillage(image.getOtsuScale());
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Entrez le n° de l'exercice à tester (1-9): ");
        int n = reader.nextInt();
        reader.close();

        switch (n) {
            case 1:
                Seance2 image11 = new Seance2("img/coins.png");
                image11.display();
                image11.seuillage(120).display();
                break;
            case 2:
                Seance2 image21 = new Seance2("img/coins.png");
                image21.otsu().display();
                Seance2 image22 = new Seance2("img/golfe2ndg.jpg");
                image22.otsu().display();
                Seance2 image23 = new Seance2("img/damier.jpg");
                image23.iterativeSelectionThresholding().display();
                Seance2 image24 = new Seance2("img/question2.png");
                image24.iterativeSelectionThresholding().display();
                break;
            case 3:
                Seance2 image31 = new Seance2("img/coins.png");
                image31.otsuZone(image31.pixels.length/2, 50).display();
                break;
        }
    }
}
