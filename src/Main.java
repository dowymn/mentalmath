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

        System.out.println();

        String typeExo = typeExercice();
        System.out.println();

        String typeDiff = typeDifficulte();
        System.out.println();

        int nbCal = 10; // si la difficulté choisie est croissante, on fixe le nombre de calculs à 10
        if ( !typeDiff.equals("croissant") ) {
            nbCal = nbCalculs();
        }
        System.out.println();

        calculs( typeExo, typeDiff, nbMin(typeDiff), nbMax(typeDiff), nbCal );
        System.out.println();
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

            // permet d'éviter la NumberFormatException en cas de faute de frappe
            try {
                n = Integer.parseInt(clavier.nextLine());
            }
            catch (NumberFormatException e) {
                n = 0;
            }
        } while ( n <= 0 );

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

        Scanner clavier = new Scanner(System.in); // scanner du clavier

        int nbVrai = 0; // donne le nombre de bonnes réponses pour l'affichage des résultats
        int val1, val2, answer;
        int rep = 0; // prendra la valeur des réponses attendues aux calculs
        char q = '+'; // prend la valeur du type de calcul
        boolean estMix = false; // permet la spécificité du mode mix
        double moyTps = 0; // pemet de calculer le temps moyen mis par le joueur;
        double bestTime = 100;

        int bonusTemps = 0; // score de temps

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
            val1 = (int) ( Math.random() * (nbMax-nbMin) ) + nbMin;     // val1 est défini aléatoirement

            if ( typeExo.equals("addition") && typeDiff.equals("moyen") ) {     // on définit aléatoirement val2 en fonction de la difficulté pour le mode addition
                val2 = (int) ( Math.random() * (99 - 9) + 9 );
            } else if ( typeExo.equals("addition") && typeDiff.equals("difficile") ) {
                val2 = (int) ( Math.random() * (999 - 99) + 99 );
            } else {
                val2 = (int) ( Math.random() * 10 );
            }

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

            // calcul du temps mis par le joueur : heure de début du calcul
            double tps1 = System.currentTimeMillis();

            // le joueur entre sa réponse et a droit à 3 essais pour la trouver
            do {
                System.out.println("Combien font " + val1 + q + val2 + " ?");

                // permet d'éviter la NumberFormatException en cas de faute de frappe
                try {
                    answer = Integer.parseInt(clavier.nextLine());
                }
                catch (NumberFormatException e) {
                    answer = 0;
                }

                if ( answer != rep ) {
                    nbEssai++;
                    System.out.println("Mauvaise réponse.");
                }
            } while ( answer != rep && nbEssai <= 3 );

            // calcul du temps mis par le joueur : heure de fin du calcul
            double tps2 = System.currentTimeMillis();
            double temps = ( tps2 - tps1 ) / 1000;
            moyTps += temps;
            if ( temps < bestTime ) { bestTime = temps; }

            if ( nbEssai == 1 ) {     // calcul du nomnbre de bonnes réponses
                nbVrai ++;
                if ( temps > 15 ) {
                    bonusTemps --;
                } else if ( temps < 5 ) {
                    bonusTemps ++;
                }
            } else if ( nbEssai <= 3 ) {
                nbVrai++;
                if ( temps > 15 ) {
                    bonusTemps --;
                }
            }
            System.out.println();
        }

        // calcul du temps moyen par calcul
        moyTps = moyTps / nbVrai;

        // affichage du score
        double score = ( nbVrai / nbCalculs + (15 - moyTps) ) / bestTime + bonusTemps;
        System.out.println("Votre score est de " + score + " points.");

        System.out.println("Voulez-vous les détails de votre score ?");
        String choixScore;
        do {
            choixScore = clavier.nextLine();
        } while ( !choixScore.equals("oui") && !choixScore.equals("non") );

        if ( choixScore.equals("oui") ) { // affichage étendu des performances du joueur
            System.out.println();
            System.out.println("| Vous avez joué en mode " + typeExo + " en difficulté " + typeDiff + "." );
            System.out.println("| Nombre de calculs réalisés : " + nbCalculs );
            System.out.println("| Nombre de résultats corrects : " + nbVrai );
            System.out.println("| Nombre de résultats non trouvés : " + (nbCalculs - nbVrai) );
            System.out.println("| Temps moyen par calcul : " + moyTps + "s" );
            System.out.println("| Meilleur temps : " + bestTime + "s " );
            System.out.println("| Bonus temps : " + bonusTemps );
            System.out.println("| ");
            System.out.println("| SCORE FINAL : " + score );
            System.out.println();
        }
    }

}