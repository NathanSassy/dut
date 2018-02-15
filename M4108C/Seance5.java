public class Seance5 extends Seance4 {
    public Seance5(int width, int height) {
        super(width, height);
    }

    public Seance5(Image i) {
        super(i);
    }

    public Seance5(String filename) {
        super(filename);
    }

    public void dft(Image module, Image phase) {

        final int N = this.getWidth();
        final int M = this.getHeight();

        Seance5 fre = new Seance5(N,M);
        Seance5 im = new Seance5(N,M);

        int u, v = 0;
        int valModule = 0;
        int a = 0;

        for(u = 0; u < N; u++) {
            for(v = 0; v < M; v++) {
                a++;

                double valFre = 0;
                double valIm = 0;

                for(int x = 0; x < N; x++) {
                    for(int y = 0; y < M; y++) {

                        valFre += this.getValue(x, y)*(double)Math.cos(2*(double)Math.PI*((double)(x*u)/(double)N + (double)(y*v/M)));
                        System.out.println(valFre);
                        valIm += this.getValue(x, y)*(double)Math.sin(2*(double)Math.PI*((double)(x*u)/(double)N + (double)(y*v/M)));
                    }
                }


                fre.setValue(u, v, (int)valFre);
                im.setValue(u, v, (int)valIm);

                valModule = (int) Math.sqrt(fre.getValue(u, v)*fre.getValue(u, v) + im.getValue(u, v)*im.getValue(u, v));

                module.setValue(u, v, valModule);
                System.out.println(a+" --> "+valModule);


            }
        }

    }

    public static void main(String args[]) {

        Seance5 image51 = new Seance5("img/lenna.png");
        image51.display();
        Seance5 module = new Seance5(image51.width, image51.height);
        image51.dft(module, null);
        module.display();
    }


}
