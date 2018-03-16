public class Seance1 extends Image {

    public Seance1(int width, int height) {
        super(width, height);
    }

    public Seance1(Image i) {
        super(i);
    }

    public Seance1(String filename) {
        super(filename);
    }

    /**
     * Permet de changer contraste et luminosité d'une image
     *
     * @param a le contraste de l'image, la valeur du pixel est
     *          multiplié par a, donc l'écart entre les valeurs
     *          s'accentue
     * @param b le gain en luminosité, rend simplement le pixel
     *          plus clair ou plus sombre sans prendre sa
     *          valeur actuel car c'est une simple additon
     * @return la nouvelle image avec les changement effectués
     * @throws IllegalArgumentException si a est négatif
     */
    public Image globalMultiplyAdd(double a, double b) throws IllegalArgumentException {
        if (a < 0)
            throw new IllegalArgumentException("a ne peut pas être négatif");

        Image ret = new Image(this);
        for (int p = 0; p < ret.pixels.length; p++) {
            ret.pixels[p] = (int) (a * ret.pixels[p] + b);
            if (ret.pixels[p] < 0)
                ret.pixels[p] = 0;
            else if (ret.pixels[p] > 255)
                ret.pixels[p] = 255;

        }
        return ret;
    }

    /**
     * Permet de changer contraste et luminosité d'une image à
     * partir de deux autres images
     *
     * @param a image contenant le contraste
     * @param b image contenant la luminosite
     * @return la nouvelle image avec les changement effectués
     * @throws IllegalArgumentException si les images n'ont pas les meme dims
     */
    public Image spatialMultiplyAdd(Image a, Image b) throws IllegalArgumentException {
        if (this.height != a.height || this.height != b.height || a.height != b.height)
            throw new IllegalArgumentException("taille image différente");
        else if (this.width != a.width || this.width != b.width || a.width != b.width)
            throw new IllegalArgumentException("taille image différente");

        Image ret = new Image(this);
        for (int p = 0; p < ret.pixels.length; p++) {
            // cast implicite dans un double pour eviter div par zero
            ret.pixels[p] = (int) ((1.0 * ret.pixels[p] / a.pixels[p]) * ret.pixels[p] + b.pixels[p]);

            if (ret.pixels[p] < 0)
                ret.pixels[p] = 0;
            else if (ret.pixels[p] > 255)
                ret.pixels[p] = 255;
        }
        return ret;
    }

    /**
     * Melange deux images
     *
     * @param alpha  valeur de 0 à 1 representant la presence des
     *               images sur l'image finale. 0 retourne l'image 1
     *               et à 1 retourne l'image 2
     * @param image1 la première image
     * @param image2 la deuxième image
     * @return la fusion des deux images
     * @throws IllegalArgumentException si l'alpha n'est pas compris entre 0 et 1
     */
    public Image alphaBlending(double alpha, Image image1, Image image2) throws IllegalArgumentException {
        if (alpha < 0 || alpha > 1)
            throw new IllegalArgumentException("alpha doit etre compris entre 0 et 1");

        Image ret = new Image(this);
        for (int p = 0; p < ret.pixels.length; p++) {
            ret.pixels[p] = (int) (alpha * image1.pixels[p] + (1 - alpha) * image2.pixels[p]);
            if (ret.pixels[p] < 0)
                ret.pixels[p] = 0;
            else if (ret.pixels[p] > 255)
                ret.pixels[p] = 255;
        }
        return ret;
    }

    /**
     * Melange deux images
     *
     * @param alpha  l'image avec alpha sur chaque pixel
     * @param image1 la première image
     * @param image2 la deuxième image
     * @return la fusion des deux images
     */
    public Image spatialAlphaBlending(Image alpha, Image image1, Image image2) {
        Image ret = new Image(this);

        for (int p = 0; p < ret.pixels.length; p++) {
            ret.pixels[p] = alpha.pixels[p] * image1.pixels[p] + (1 - alpha.pixels[p]) * image2.pixels[p];

            if (ret.pixels[p] < 0)
                ret.pixels[p] = 0;
            else if (ret.pixels[p] > 255)
                ret.pixels[p] = 255;
        }

        return ret;
    }

    /**
     * Expension dynamique d'une image
     *
     * @return L'image avec les couleurs étirées
     */
    public Image dynamicExpansion() {
        Image ret = new Image(this);
        int min = 255;
        int max = 0;

        for (int p = 0; p < ret.pixels.length; p++) {
            if (ret.pixels[p] < min) min = ret.pixels[p]; //min
            if (ret.pixels[p] > max) max = ret.pixels[p]; //max
        }

        double ratio = 1.0 * 255 / (max - min);
        double d = min * ratio;

        int[] tab = new int[256];
        for (int v = 0; v < tab.length; v++)
            tab[v] = (int) (v * ratio - d);

        for (int i = 0; i < ret.pixels.length; i++)
            ret.pixels[i] = tab[ret.pixels[i]];

        return ret;
    }

    /**
     * @return histogramme des niveaux de gris
     */
    public int[] histogram() {
        int[] histogram = new int[256];

        for (int p = 0; p < this.pixels.length; p++)
            histogram[this.pixels[p]]++;

        return histogram;
    }

    /**
     * @return histogramme des niveaux de gris normalisés
     */
    public int[] normalizedHistogram() {
        int histogram[] = this.histogram();
        int[] normalizedHistogram = new int[256];
        int numberOfPixels = this.height * this.width;

        for (int n = 0; n < 256; n++)
            normalizedHistogram[n] = histogram[n] / numberOfPixels;

        return normalizedHistogram;
    }

    /**
     * Implemente l'histogramme à l'image
     *
     * @param histogramme l'histogramme
     * @return l'image modifiée
     */
    public Image dynamicExpansion(int[] histogramme) {
        Image ret = new Image(this);
        int min = 0;
        int max = 0;
        int minVal = 255;
        int maxVal = 0;

        for (int i = 0; i < histogramme.length; i++) {
            if (histogramme[i] < minVal) {
                minVal = histogramme[i];
                min = i;
            }
            if (histogramme[i] > maxVal) {
                maxVal = histogramme[i];
                max = i;
            }
        }

        double ratio = 1.0 * 255 / (max - min);
        double d = min * ratio;

        int[] tab = new int[256];
        for (int v = 0; v < 256; v++)
            tab[v] = (int) (v * ratio - d);


        for (int i = 0; i < ret.pixels.length; i++) {
            ret.pixels[i] = tab[ret.pixels[i]];

            if (ret.pixels[i] < 0)
                ret.pixels[i] = 0;
            else if (ret.pixels[i] > 255)
                ret.pixels[i] = 255;
        }

        return ret;
    }

    /**
     * @param histogram l'histogramme de base
     * @return Histogramme cumulée
     */
    public double[] cumulativeHistogram(int histogram[]) {
        double cumulativeHistogram[] = new double[256];
        int c = 0;

        for (int i = 0; i < cumulativeHistogram.length; i++) {
            c += histogram[i];
            cumulativeHistogram[i] = 1.0 * c / 256;
        }

        return cumulativeHistogram;
    }

    /**
     * @param cumulativeHistogram l'histogramme de base
     * @return Histogramme égalisé
     */
    public int[] equalizedHistogram(double cumulativeHistogram[]) {
        int equalizedHistogram[] = new int[256];

        for (int i = 0; i < equalizedHistogram.length; i++) {
            equalizedHistogram[i] = (int) cumulativeHistogram[i] * 255;
        }

        return equalizedHistogram;
    }
}
