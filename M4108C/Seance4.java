public class Seance4 extends Seance3 {
    public Seance4(int width, int height) {
        super(width, height);
    }

    public Seance4(Image i) {
        super(i);
    }

    public Seance4(String filename) {
        super(filename);
    }

    public Seance4 convolution(int[][] masque) {

        Seance4 ret = new Seance4(this.width, this.height);

        for(int y = 0; y < ret.getHeight(); y++){
            for(int x = 0; x < ret.getWidth(); x++){

                int[] retValue = this.convolutionPixel(this, x, y, masque);
                int newValue = retValue[0];

                ret.setValue(x, y, newValue);

                if(ret.getValue(x, y) > 255)
                    ret.setValue(x, y, 255);
                else if(ret.getValue(x, y) < 0)
                    ret.setValue(x, y, 0);

            }
        }

        return ret;

    }

    private int[] convolutionPixel(Seance4 img, int x, int y, int[][] masque) {

        int masqueHeight = masque.length;
        int masqueWidth = masque[0].length;
        int ret[] = new int[2];

        int i = 0;
        int j = 0;

        for(int ty = y - masqueHeight/2; ty <= y + masqueHeight/2; ty++){
            i = 0;
            for(int tx = x - masqueWidth/2; tx <= x + masqueWidth/2; tx++){

                // on vérifie que l'on est pas en dehors de l'image
                if(ty >= 0 && ty < img.getHeight() && tx >= 0 && tx < img.getWidth() && i<masqueWidth && j<masqueHeight){
                    ret[0] = ret[0]+img.getValue(tx, ty)*masque[i][j];
                    ret[1] = ret[1]+masque[i][j];

                }
                i++;
            }
            j++;
        }

        return ret;
    }

    public Seance4 filtreMoyenneur(int masque) {

        Seance4 ret = new Seance4(this.width, this.height);

        int[][] kernel = new int[masque][masque];

        for(int i = 0; i < masque; i++)
            for(int j = 0; j < masque; j++)
                kernel[i][j] = 1;

        for(int y = 0; y < ret.getHeight(); y++){
            for(int x = 0; x < ret.getWidth(); x++){

                int[] retValue = this.convolutionPixel(this, x, y, kernel);
                int newValue = retValue[0]/retValue[1];

                ret.setValue(x, y, newValue);

                if(ret.getValue(x, y) > 255)
                    ret.setValue(x, y, 255);
                else if(ret.getValue(x, y) < 0)
                    ret.setValue(x, y, 0);

            }
        }

        return ret;

    }

    public Seance4 filtreMoyenneurPondere(int[][] masque) {

        Seance4 ret = new Seance4(this.width, this.height);

        for(int y = 0; y < ret.getHeight(); y++){
            for(int x = 0; x < ret.getWidth(); x++){

                int[] retValue = this.convolutionPixel(this, x, y, masque);
                int newValue = retValue[0]/retValue[1];

                ret.setValue(x, y, newValue);

                if(ret.getValue(x, y) > 255)
                    ret.setValue(x, y, 255);
                else if(ret.getValue(x, y) < 0)
                    ret.setValue(x, y, 0);

            }
        }

        return ret;


    }

    public static void main(String args[]) {


		/*
		Seance4 image41 = new Seance4("img/coins.png");
		image41.display();
		int[][] masque = new int[3][3];

		image41.convolution(masque).display("Convolution masque 3*3, pixel central 0");

		masque[1][1] = 1;
		image41.convolution(masque).display("Convolution masque 3*3, pixel central 1");

		masque = new int[5][5];

		image41.convolution(masque).display("Convolution masque 5*5, pixel central 0");

		masque[2][2] = 1;
		image41.convolution(masque).display("Convolution masque 5*5, pixel central 1");
		*/
		/*
		Seance4 image43 = new Seance4("img/coins.png");
		image43.display();
		image43.filtreMoyenneur(100).display();
		*/

        Seance4 image45 = new Seance4("img/coins.png");
        image45.display();
        int[][] masque = new int[5][5];

        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                masque[i][j] = 1;

        for(int i = 1; i < 4; i++)
            for(int j = 1; j < 4; j++)
                masque[i][j] = 2;

        Seance4 ima = null;
        for(int i = 0; i<100; i++)
            ima= image45.filtreMoyenneurPondere(masque);

        ima.display();


    }
}
