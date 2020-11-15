import java.util.Scanner;

public class Main {

    //---[ MÉTHODE PRINCIPALE ]---//
    public static void main(String[] args) {

        String typeExo = typeExercice();
        String typeDiff = typeDifficulte();

        calculs( typeExo, nbMin(typeDiff), nbMax(typeDiff), nbCalculs() );


    }


    //---[ MÉTHODES ]---//

    public static String typeExercice () {

        Scanner clavier = new Scanner(System.in);

        String typeExo;

        System.out.println("Vous avez le choix entre deux types d'opérations :");
        System.out.println("- addition");
        System.out.println("- multiplication");

        do {
            System.out.println("Choisissez le type d'exercice : ");
            typeExo = clavier.nextLine();
        } while ( typeExo.equals("addition")==false && typeExo.equals("multiplication")==false );

        System.out.println("Vous avez choisi de faire des calculs de type " + typeExo + ".");
        return typeExo;
    }

    public static String typeDifficulte () {

        String typeDiff;
        Scanner clavier = new Scanner(System.in);

        System.out.println("Vous avez le choix entre différents modes :");
        System.out.println("- facile");
        System.out.println("- moyen");
        System.out.println("- difficile");

        do {
            System.out.println("Choisissez la difficulté : ");
            typeDiff = clavier.nextLine();
        } while ( typeDiff.equals("facile")==false && typeDiff.equals("moyen")==false && typeDiff.equals("difficile")==false );

        System.out.println("Vous avez choisi le mode " + typeDiff + ".");
        return typeDiff;
    }

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

    public static int nbMin( String typeDiff ) {
        int min = 0;
        if ( typeDiff.equals ("moyen") ) { min = 10; }
        else if ( typeDiff.equals ("difficile") ) { min = 100; }

        return min;
    }

    public static int nbMax( String typeDiff ) {
        int max = 10;
        if ( typeDiff.equals ("moyen") ) { max = 99; }
        else if ( typeDiff.equals ("difficile") ) { max = 999; }

        return max;
    }

    public static void calculs ( String typeExo, int nbMin, int nbMax, int nbCalculs ) {

        int nbVrai = 0, nbVraiPlus;
        int val1, val2, answer ;
        int rep = 0;
        char q = '+';
        Scanner clavier = new Scanner(System.in);

        for ( int i = 0 ; i < nbCalculs ; i++ ) {

            int j = 0;
            val1 = (int) ( Math.random() * (nbMax-nbMin) ) + nbMin;
            val2 = (int) ( Math.random() * 10);

            if ( typeExo.equals("addition") ) { rep = val1 + val2; }
            else if ( typeExo.equals("multiplication") ) { rep = val1 * val2; q = 'x'; }

            do {
                System.out.println("Combien font " + val1 + q + val2 + " ?");
                answer = Integer.parseInt(clavier.nextLine());
                if ( answer != rep ) { j++; }
            } while ( answer != rep && j < 5 );

            if ( j == 0 ) {
                nbVrai ++;
                System.out.println("Bravo, vous avez trouvé la bonne réponse du premier coup !");
            }
            else if ( j < 5 ) {
                nbVrai++;
                System.out.println("Vous avez trouvé la bonne réponse en " + (j+1) + " essais. Bravo !");
            }
            else { System.out.println("Vous n'avez pas trouvé la réponse malgré les 5 essais. C'est pas grave, ça arrive !"); }

        }

        if ( nbVrai == 1 ) { System.out.println("Votre score est de " + nbVrai + " bonne réponse sur " + nbCalculs + " calculs."); }
        else { System.out.println("Votre score est de " + nbVrai + " bonnes réponses sur " + nbCalculs + " calculs."); }
    }

}
