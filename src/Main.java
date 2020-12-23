import java.util.Scanner;

/**
 * MentalMath - Jeu de calcul mental
 * @author Dowymn
 */
public class Main {

    //---[ MÉTHODE PRINCIPALE ]---//
    
    public static void main(String[] args) {
        lanceur();
    }


    //---[ MÉTHODES ]---//

    /**
     * Permet de lancer le jeu.
     */
    public static void lanceur () {
        String typeExo = typeExercice();
        String typeDiff = typeDifficulte();

        int nbCal = 10; // si la difficulté choisie est croissante, on fixe le nombre de calculs à 10
        if ( !typeDiff.equals("croissant") ) {
            nbCal = nbCalculs();
        }

        calculs( typeExo, typeDiff, nbMin(typeDiff), nbMax(typeDiff), nbCal );
    }

    /**
     * Permet au joueur de choisir le type d'exercice entre l'addition, la multiplication ou un mélange des deux.
     * Tant que le type entré par le joueur est différent des types proposés, le choix lui est redemandé.
     * @return typeExo le type d'exercice choisi
     */
    public static String typeExercice () {

        Scanner clavier = new Scanner(System.in);

        String typeExo;

        System.out.println("Vous avez le choix entre deux types d'opérations :");
        System.out.println("- addition");
        System.out.println("- multiplication");
        System.out.println("- mix");

        do {
            System.out.println("Choisissez le type d'exercice : ");
            typeExo = clavier.nextLine();
        } while ( !typeExo.equals("addition") && !typeExo.equals("multiplication") && !typeExo.equals("mix") );

        System.out.println("Vous avez choisi de faire des calculs de type " + typeExo + ".");
        return typeExo;
    }

    /**
     * Permet au joueur de choisir la difficulté de l'exercice entre facile, moyen, difficile ou croissante.
     * Tant que la difficulté entrée est != des difficultés proposées, le choix est redemandé au joueur.
     * @return typeDiff la difficulté choisie par le joueur
     */
    public static String typeDifficulte () {

        String typeDiff;
        Scanner clavier = new Scanner(System.in);

        System.out.println("Vous avez le choix entre différents modes :");
        System.out.println("- facile");
        System.out.println("- moyen");
        System.out.println("- difficile");
        System.out.println("- croissant");

        do {
            System.out.println("Choisissez la difficulté : ");
            typeDiff = clavier.nextLine();
        } while ( !typeDiff.equals("facile") && !typeDiff.equals("moyen") && !typeDiff.equals("difficile") && !typeDiff.equals("croissant") );

        System.out.println("Vous avez choisi le mode " + typeDiff + ".");
        return typeDiff;
    }

    /**
     * Permet au joueur de choisir le nombre de calculs qu'il veut faire.
     * Tant qu'il entre un nombre <= 0, il lui est redemandé d'entrer un nombre.
     * @return n le nombre de calculs
     */
    public static int nbCalculs() {

        Scanner clavier = new Scanner(System.in);
        int n;

        do {
            System.out.println("Combien de calculs voulez-vous faire ?");
            n = Integer.parseInt( clavier.nextLine() );
        } while ( n < 0 );

        System.out.println("Vous avez choisi de faire " + n + " calculs.");
        return n;
    }

    /**
     * Définit la valeur minimale du premier nombre des calculs.
     * @param typeDiff le type de difficulté choisi par le joueur
     * @return min la valeur minimale en fonction de la difficulté choisie.
     */
    public static int nbMin( String typeDiff ) {
        int min = 0;
        if ( typeDiff.equals ("moyen") ) { min = 10; }
        else if ( typeDiff.equals ("difficile") ) { min = 100; }

        return min;
    }

    /**
     * Définit la valeur maximale du premier nombre des calculs.
     * @param typeDiff le type de difficulté choisi par le joueur
     * @return min la valeur maximale en fonction de la difficulté choisie.
     */
    public static int nbMax( String typeDiff ) {
        int max = 10;
        if ( typeDiff.equals ("moyen") ) { max = 99; }
        else if ( typeDiff.equals ("difficile") ) { max = 999; }

        return max;
    }

    // déroulement des calsuls

    /**
     * Déroulement du nombre de calculs demandés.
     * Si le type choisi est mix, le type de calcul est tiré aléatoirement à chaque tour de boucle.
     * Pour chaque calcul, le joueur a droit à 3 essais.
     * Une fois tous les calculs finis, le nombre de bonnes réponses est affiché.
     * @param typeExo   le type d'exercice choisi par le joueur
     * @param typeDiff  le type de difficulté choisie par le joueur
     * @param nbMin     la valeur minimale des premiers nombres
     * @param nbMax     la valeur maximale des premiers nombres
     * @param nbCalculs le nombre de calculs à effectuer
     */
    public static void calculs ( String typeExo, String typeDiff, int nbMin, int nbMax, int nbCalculs ) {

        int nbVrai = 0; // donne le nombre de bonnes réponses pour l'affichage des résultats
        int val1, val2, answer;
        int rep = 0; // prendra la valeur des réponses attendues aux calculs
        char q = '+'; // prend la valeur du type de calcul
        boolean estMix = false; // permet la spécificité du mode mix
        Scanner clavier = new Scanner(System.in);

        if ( typeExo.equals("mix") ) { estMix = true; } // on regarde si le mode mix a été choisi

        // déroulement des calculs
        for ( int i = 1 ; i <= nbCalculs ; i++ ) {

            if (estMix) {
                if ( (int) ( Math.random() * 2 ) == 0 ) {   // si le mode est mix, à chaque tour il y a un choix aléatoire
                    typeExo = "addition";                   // entre addition et multiplication
                } else {
                    typeExo = "multiplication";
                }
            }

            int nbEssai = 1;  // compteur du nombre d'essais pour chaque calcul
            val1 = (int) ( Math.random() * (nbMax-nbMin) ) + nbMin;     // val1 et val2 sont définies aléatoirement
            val2 = (int) ( Math.random() * 10 );

            if ( typeDiff.equals("croissant") ) {               // si le mode croissant est choisi, la difficulté augmente à chaque tour
                if ( typeExo.equals("multiplication") ) {       // de manière différente si le mode effectif est addition ou multiplication
                    val1 = (val1 + i) * i;                      // en cas de mode mix, ça s'adapte à chaque fois
                    val2 = val2 + i;
                }
                else if ( typeExo.equals("addition") ) {
                    val1 = (val1 + i) * i;
                    val2 = (val2 + i) * i;
                }
            }

            // définition de la réponse en fonction du type d'exo, de val1 et de val2
            if ( typeExo.equals("addition") ) {
                rep = val1 + val2;
                q = '+';
            } else if ( typeExo.equals("multiplication") ) {
                rep = val1 * val2;
                q = 'x';
            }

            // le joueur entre sa réponse et a droit à 3 essais pour la trouver
            do {
                System.out.println("Combien font " + val1 + q + val2 + " ?");
                answer = Integer.parseInt(clavier.nextLine());
                if ( answer != rep ) {
                    nbEssai++;
                    System.out.println("Mauvaise réponse.");
                }
            } while ( answer != rep && nbEssai <= 3 );

            if ( nbEssai == 1 ) {     // calcul du nomnbre de bonnes réponses
                nbVrai ++;
                System.out.println("Bravo, vous avez trouvé la bonne réponse du premier coup !");
            } else if ( nbEssai <= 3 ) {
                nbVrai++;
                System.out.println("Vous avez trouvé la bonne réponse en " + (nbEssai) + " essais. Bravo !");
            } else {
                System.out.println("Vous n'avez pas trouvé la réponse malgré les 5 essais. C'est pas grave, ça arrive !");
            }
        }

        // c'est juste une histoire de pluriel ou de singulier, en soi ça sert à rien
        if ( nbVrai == 1 ) { System.out.println("Votre score est de " + nbVrai + " bonne réponse sur " + nbCalculs + " calculs."); }
        else { System.out.println("Votre score est de " + nbVrai + " bonnes réponses sur " + nbCalculs + " calculs."); }
    }

}
