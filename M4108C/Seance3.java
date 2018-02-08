import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Seance3 extends Seance2 {

    public Seance3(int width, int height) {
        super(width, height);
    }

    public Seance3(Image i) {
        super(i);
    }

    public Seance3(String filename) {
        super(filename);
    }

    public Seance3 segmentationParSeillage() {
        return new Seance3(this.otsu().etiquetteObjet2());
    }

    private List<Integer[]> watershedList;

    public Seance3 watershed() {
        watershedList = new ArrayList<>();
        Seance3 ret = new Seance3(this);
        Seance3 labeled = new Seance3(this);
        Arrays.fill(labeled.pixels, 0);
        Seance3 minima = new Seance3(this);

        int label = 1;
        int e = 30;

        for(int y = 0; y < minima.getHeight(); y++) {
            for(int x = 0; x < minima.getWidth(); x++) {
                int min = 255;
                int max = 0;
                int pixel = 0;

                for(int ty = y - 1; ty <= y + 1; ty++) {
                    for (int tx = x - 1; tx <= x + 1; tx++) {
                        if(ty >= 0 && ty < ret.getHeight() && tx >= 0 && tx < ret.getWidth()) {
                            if(ret.getValue(tx, ty) > max) {
                                max = ret.getValue(tx, ty);
                                pixel = ty * ret.getWidth() + tx;
                            }
                            if(ret.getValue(tx, ty) < min) {
                                min = ret.getValue(tx, ty);
                            }
                        }
                    }
                }
                minima.setValue(x, y, max - min);
                if(max >= 200) {
                    ajouter_file(pixel, min, label);
                    label++;
                }
            }
        }

        while (nonvide_file()) {
            int pixel[] = enlever_file();
            int x = pixel[0] % ret.getWidth();
            int y = pixel[0] / ret.getWidth();
            int lab = pixel[2];

            labeled.setValue(x, y, lab);

            for(int ty = y - 1; ty <= y + 1; ty++) {
                for (int tx = x - 1; tx <= x + 1; tx++) {
                    if(ty >= 0 && ty < ret.getHeight() && tx >= 0 && tx < ret.getWidth() && labeled.getValue(tx, ty) != 0) {
                        labeled.setValue(tx, ty, lab);
                        ajouter_file(ty * labeled.getWidth() + tx, ret.getValue(tx, ty), lab);
                    }
                }
            }
        }

        watershedList = null;
        return ret;
    }

    private boolean nonvide_file() {
        return !watershedList.isEmpty();
    }

    private void ajouter_file(int pixel, int valeur, int label) {
        Integer val[] = {pixel, valeur, label};

        if(watershedList.isEmpty()) {
            watershedList.add(val);
        }
        else {
            int pos = 0;
            while (pos < watershedList.size() && valeur >= watershedList.get(pos)[1]) {
                pos++;
            }
            watershedList.add(pos, val);
        }
    }

    private int[] enlever_file() {
        Integer[] pixel = watershedList.get(0);
        watershedList.remove(0);
        return Arrays.stream(pixel).mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Entrez le n° de l'exercice à tester (1-7): ");
        int n = reader.nextInt();
        reader.close();

        switch (n) {
            case 1:
                Seance3 image11 = new Seance3("img/golfe2ndg.jpg");
                image11.segmentationParSeillage().displayColor();
                break;
            case 2:
                Seance3 image21 = new Seance3("img/golfe2ndg.jpg");
                image21.watershed().display();
                break;
        }
    }
}
