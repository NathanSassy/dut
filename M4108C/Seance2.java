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

    public Seance2 seuillage(int t) {
        Seance2 ret = new Seance2(this);
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

    public Seance2 otsu() {
        Seance2 ret = new Seance2(this);
        return ret.seuillage(ret.getOtsuScale());
    }

    public Seance2 iterativeSelectionThresholding() {
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

    public Seance2 otsu(int s) {
        Seance2 image = new Seance2(this.width, this.height);
        Seance2 input = new Seance2(this);
        Seance2 ret = new Seance2(this);
        long sum = 0;
        int x1, y1, x2, y2;
        int count = 0;
        int index = 0;
        int s2 = s/2;

        for(int i = 0; i < image.width; i++) {
            sum = 0;

            for(int j = 0; j < image.height; j++) {
                index = j*image.width+i;

                sum += input.pixels[index];
                if(i==0)
                    image.pixels[index] = (int) sum;
                else
                    image.pixels[index] = (int) (image.pixels[index-1] + sum);


            }
        }

        for (int i=0; i<image.width; i++) {
            for (int j=0; j<image.height; j++) {
                index = j*image.width+i;

                // set the SxS region
                x1=i-s2; x2=i+s2;
                y1=j-s2; y2=j+s2;

                // check the border
                if (x1 < 0) x1 = 0;
                if (x2 >= image.width) x2 = image.width-1;
                if (y1 < 0) y1 = 0;
                if (y2 >= image.height) y2 = image.height-1;

                count = (x2-x1)*(y2-y1);

                // I(x,y)=s(x2,y2)-s(x1,y2)-s(x2,y1)+s(x1,x1)
                sum = image.pixels[y2*image.width+x2] -
                        image.pixels[y1*image.width+x2] -
                        image.pixels[y2*image.width+x1] +
                        image.pixels[y1*image.width+x1];

                if ((long)(input.pixels[index]*count) < (long)(sum))
                    ret.pixels[index] = 0;
                else
                    ret.pixels[index] = 255;
            }
        }

        return ret;
    }

    public Seance2 erosion(int n) {
        Seance2 ret = new Seance2(this);
        int map[] = new int[ret.pixels.length];

        for(int y = 0; y < ret.getHeight(); y++){
            for(int x = 0; x < ret.getWidth(); x++){

                map[x+y*ret.width] = ret.getValue(x, y);

                for(int ty = y - n; ty <= y + n; ty++) {
                    for (int tx = x - n; tx <= x + n; tx++) {
                        if(
                            ty >= 0 && ty < ret.getHeight() // y
                            && tx >= 0 && tx < ret.getWidth() // x
                            && ret.getValue(tx, ty) < map[x+y*ret.width]) { // min
                            map[x+y*ret.width] = ret.getValue(tx, ty);
                        }
                    }
                }
            }
        }

        System.arraycopy(map, 0, ret.pixels, 0, ret.pixels.length);
        return ret;
    }

    public Seance2 dilatation(int n) {
        Seance2 ret = new Seance2(this);
        int map[] = new int[ret.pixels.length];

        for(int y = 0; y < ret.getHeight(); y++){
            for(int x = 0; x < ret.getWidth(); x++){
                map[x+y*ret.width] = ret.getValue(x, y);

                for(int ty = y - n; ty <= y + n; ty++) {
                    for (int tx = x - n; tx <= x + n; tx++) {
                        if(
                            ty >= 0 && ty < ret.getHeight() // y
                            && tx >= 0 && tx < ret.getWidth() // x
                            && ret.getValue(tx, ty) > map[x+y*ret.width]) { // max
                            map[x+y*ret.width] = ret.getValue(tx, ty);
                        }
                    }
                }
            }
        }

        System.arraycopy(map, 0, ret.pixels, 0, ret.pixels.length);
        return ret;
    }

    public Seance2 erosion3x3() {
        return new Seance2(this).erosion(3);
    }

    public Seance2 dilatation3x3() {
        return new Seance2(this).dilatation(3);
    }

    public Seance2 ouverture() {
        return new Seance2(this).erosion3x3().dilatation3x3();
    }

    public Seance2 fermeture() {
        return new Seance2(this).dilatation3x3().erosion3x3();
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
                image31.otsu(10).display();
                image31.otsu().display();
                break;
            case 4:
                Seance2 image41 = new Seance2("img/golfe2ndg.jpg");
                image41.otsu().display();
                image41.otsu().erosion3x3().display();
                image41.otsu().dilatation3x3().display();
                break;
            case 5:
                Seance2 image51 = new Seance2("img/golfe2ndg.jpg");
                image51.otsu().display();
                image51.otsu().erosion(6).display();
                image51.otsu().dilatation(6).display();
                break;
            case 7:
                Seance2 imgae71 = new Seance2("img/golfe2ndg.jpg");
                imgae71.display();
                imgae71.ouverture().display();
                imgae71.fermeture().display();
                break;
        }
    }
}
