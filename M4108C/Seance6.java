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

    private Image imRescale(int p) {
        Image ret = new Image(this);
        int max = ret.getValue(0, 0);
        for (int y = 0; y < ret.getHeight(); y++)
            for (int x = 0; x < ret.getWidth(); x++)
                if (ret.getValue(x, y) > max) max = ret.getValue(x, y);
        for (int y = 0; y < ret.getHeight(); y++)
            for (int x = 0; x < ret.getWidth(); x++)
                ret.setValue(x, y, (ret.getValue(x, y) * p) / max);
        return ret;
    }


    public int[][] glcm(int p, int dx, int dy) {
        Image img = this.imRescale(p - 1);
        int[][] glcm = new int[p][p];
        for (int y = 0; y < img.height - dy; y++) {
            for (int x = 0; x < img.width - dx; x++) {
                glcm[img.getValue(x, y)][img.getValue(x + dx, y + dy)]++;
            }
        }
        return glcm;
    }
}
