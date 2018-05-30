// methode emprunter de Ouvrage
public void emprunter(Ouvrage ouv, Client, cli) {
    int nb = cli.nbEmprunts();
    int dispo = 0;
    if(nb < 3) {
        int dispo = ouv.nbEnStock();
        if(dispo > 0) {
            Pret unPret = new Pret(ouv,cli);
        }
        else {
            String err2 = "!";
            this.aff.afficher(err2);
        }
    }
    else {
        String err1 = "!";
        this.aff.afficher(err1);
    }
}

// Constructeur de Pret
public Pret(Ouvrage ouv, Client cli) {
    this.ouv = ouv;
    this.cli = cli;
    this.ouv.decrementer();
    this.cli.incrementNbEmprunts();
}
