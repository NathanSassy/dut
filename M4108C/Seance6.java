public class Seance6 extends Seance5 {

    public Seance6(int width, int height) {
        super(width, height);
    }

    public Seance6(Image i) {
        super(i);
    }

    public Seance6(String filename) {
        super(filename);
    }

    public double[] des1st() {
        int hist[] = this.normalizedHistogram();
        double m = 0, v = 0, ener = 0, entr = 0;
        double e = 10^-6;

        for(int i = 0; i < 255; i++) {

        }

        double des[] = {m, v, ener};
        return des;

    }
}
