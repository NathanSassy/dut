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

    public Seance5 egalisation(double[] histoCumul) {

        Seance5 ret = new Seance5(this);

        int tailleLargeur = ret.getWidth();
        int tailleHauteur = ret.getHeight();

        for(int y=0;y<tailleHauteur;y++) {
            for(int x=0;x<tailleLargeur;x++) {

                int val = ret.getValue(x, y);
                if(val < 0 || val>255) {
                    break;
                }
                else {
                    double cumul = histoCumul[val];
                    ret.setValue(x, y, (int)(cumul*255));
                }
            }
        }
        return ret;

    }

    public Seance5 dtf(Seance5 module, Seance5 phase) {
        Seance5 ret = new Seance5(this);
        int hauteur = ret.getHeight();
        int largeur = ret.getWidth();

        Image tmp = new Image(this);
        Image FRe= new Image(tmp.width,tmp.height);
        Image FIm= new Image(tmp.width,tmp.height);
        for(int v = 0; v < FRe.getHeight();v++) {
            for(int u=0; u< FRe.getWidth();u++) {

                double fRe = 0;
                double fIm = 0;

                for (int y = 0; y < ret.getHeight(); y++) {
                    for(int x = 0; x < ret.getWidth(); x++) {

                        fRe = fRe + (double)tmp.getValue(x, y) *
                                Math.cos(2*Math.PI*((double)((double)(x*u)/(double)(largeur))+(double)((double)(y*v)/(double)(hauteur))));
                        fIm = fIm  + (double)tmp.getValue(x, y) *
                                Math.sin(2*Math.PI*((double)((double)(x*u)/(double)(largeur))+(double)((double)(y*v)/(double)(hauteur))));


                    }
                }

                FRe.setValue(u, v, (int) fRe);
                FIm.setValue(u, v, (int) - fIm);
                System.out.println(fRe);
                System.out.println(fIm);
                module.setValue(u, v, (int) Math.log(1+Math.sqrt(Math.pow(fRe,2)+Math.pow(-fIm, 2))));
                phase.setValue(u, v, (int) Math.atan((double)-fIm/fRe));
            }
        }

        module = module.egalisation(module.cumulativeHistogram(module.normalizedHistogram()));
        return ret;
    }

    public Seance5 dwtHaar(int n) {
        Seance5 ret = new Seance5(this);
        Seance5 tmp = new Seance5(this);
        double sk = 0;
        double dk1 = 0;
        double dk2 = 0;
        double dk3 = 0;

        for(int m=1;m<n+1;m++) {
            for(int y=0;y<(tmp.height/(2*m))-1;y++) {
                for(int x=0;x<(tmp.width/(2*m))-1;x++) {
                    sk = (tmp.getValue(2*m*x-1, 2*m*y-1)+tmp.getValue(2*m*x, 2*m*y-1))/2;
                    dk1 = (tmp.getValue(2*m*x-1, 2*m*y-1)-tmp.getValue(2*m*x, 2*m*y-1))/2;
                    dk2 = (tmp.getValue(2*m*x-1, 2*m*y-1)-tmp.getValue(2*m*x-1, 2*m*y))/2;
                    dk3 = (tmp.getValue(2*m*x-1, 2*m*y-1)-tmp.getValue(2*m*x, 2*m*y))/2;

                    ret.setValue(x, y, (int)sk);
                    ret.setValue((int)((double)ret.width/(2*m))+x, y, (int) dk1);
                    ret.setValue(x, (int)((double)ret.height/(2*m)) + y, (int) dk2);
                    ret.setValue((int)((double)ret.width/(2*m))+x, (int)((double)ret.height/(2*m)) + y, (int) dk3);
                }
            }
        }

        return ret;
    }

    public static void main(String args[]) {
        Seance5 image51 = new Seance5("img/coins.png");
        image51.display();
        image51.dwtHaar(4).display();
    }

}
