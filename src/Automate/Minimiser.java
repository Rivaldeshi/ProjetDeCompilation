package Automate;

import Utils.Constans;
import Utils.ValidationException;
import java.util.ArrayList;
import java.util.List;
import Utils.Constans;
import Utils.ValidationException;

public class Minimiser {

    /**
     * cette fonction nous retourne l'automate minimiser d'un AFD
     * 
     * @param AFD
     * 
     */
    public static Automate minimisation(Automate aut) throws ValidationException {
        // creation du nouveau automate minimiser
        Automate autMinimiser = new Automate(Constans.APHABET);

        // liste des partitons apres avoir applique la theorie du congossa
        List<List<Etat>> MesPartitions = new ArrayList<List<Etat>>();

        // creation de la partition 1 , qui est lensemble des etats finaux de l'automate
        List<Etat> partition1 = aut.getFinalStates();

        // creation de la partition 2, ensembles des autres etats sauf les finaux
        List<Etat> partition2 = aut.getStates();
        for (Etat e : aut.getFinalStates()) {
            if (partition2.contains(e)) {
                partition2.remove(e);
            }
        }

        // utilisons la theorie du congossa pour essayer de creer d'autre partitions a
        // partie des partitions 1
        // chaque etat se trouvant dans la partition 1
        for (Etat e : partition1) {
            // je parcours l'alphabet de lautomate sauf epsilon
            for (String c : aut.getAlphabet()) {
                if (!c.equals(Constans.EPSILON)) {
                    // si la trasition de l'etat e sur un symbol de l'alphabet donne un etat qui
                    // n'est pas dans la partition
                    if (!(partition1.contains(aut.getTransitionTable().getTransition(e, c)))) {
                        // je cree une nouvelle partition qui ne contient que l'etat e
                        List<Etat> l = new ArrayList<Etat>();
                        l.add(e);
                        // j'ajoute cette partition a ma liste de partiton
                        MesPartitions.add(l);
                        // je retire cet etat de la partition 1
                        partition1.remove(e);
                    }

                }
            }

        }

        // pour la partition 2

        // en faite c'est le meme code executer sur la partition 1
        // je doit normalement creer une fonction pour eviter la repetition du code ,
        // mais bon ....
        for (Etat e : partition2) {
            // je parcours l'alphabet de lautomate sauf epsilon
            for (String c : aut.getAlphabet()) {
                if (!c.equals(Constans.EPSILON)) {
                    if (!(partition2.contains(aut.getTransitionTable().getTransition(e, c)))) {
                        List<Etat> l = new ArrayList<Etat>();
                        l.add(e);
                        MesPartitions.add(l);
                        partition2.remove(e);
                    }

                }
            }

        }

        // j'ajoute les deux partitions initiale a mes partition
        // mais ici les deux partitons ci , ne serons plus pareil qu'au depart car
        // j'aurais enlever les etats qui sur un symbole de l'alphabet transite vers un
        // etat ne faisant pas partir de la meme partiton ou sous groupe
        MesPartitions.add(partition1);
        MesPartitions.add(partition2);

        // j'affiche mes partition
        System.out.println("\n info sur la minimisation");

        System.out.println(MesPartitions);

        // ce n'est pas fini , just que je veux dabord massurer que mon algoritme divise
        // bien les deux groupe de depart la en plusieur sous groupe
        // la apres je choisir just un etat dans chaque sous groupe pour former l'AFD
        // minimal
        return autMinimiser;
    }

}
