#include <stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <linux/byteorder/little_endian.h>

int i2adr(int i)
{
    return (i-1)*0x20 + 0x1000;
}

int ua2adr(int ua)
{
    return ua*0x400;
}

void error(char *message)
{
    printf("Erreur : %s\n", message);
    exit(0);
}

int main (int argc, char *argv[])
{
    if(argc < 2)
        error("Parametre invalide");

    FILE *file = NULL;
    if(! (file = fopen(argv[1], "r")))
        error("Fichier invalide\n");

    for(int i = 2; i < argc; i++)
    {
        int adr;
        if(!sscanf(argv[i], "%x", &adr))
            error("Adresse pas lisible\n");


        if(fseek(file, adr, SEEK_SET) < 0)
            error("Impossible de se placer dans le fichier");

        int value  = 0;
        if(fread(&value, sizeof(char), 1, file) < 1)
            error("Impossible de lire");

        if(fprintf(stdout, "0x%06x:0x%x\n", adr, value) < 0)
            error("Impssible d'afficher l'inode");
    }



    fclose(file);
    return EXIT_SUCCESS;
}
