package Automate;

import Utils.Constans;
import Utils.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;

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
        int test = 0;

        // liste des partitons apres avoir applique la theorie du congossa
        List<List<Etat>> MesPartitions = new ArrayList<List<Etat>>();

        Queue<List<Etat>> nonTraiter = new ArrayDeque<List<Etat>>();

        // creation de la partition 1 , qui est lensemble des etats finaux de l'automate
        List<Etat> partition1 = aut.getFinalStates();

        // creation de la partition 2, ensembles des autres etats sauf les finaux
        List<Etat> partition2 = aut.getStates();
        for (Etat e : aut.getFinalStates()) {
            if (partition2.contains(e)) {
                partition2.remove(e);
            }
        }

        // j'ajoute d'abord les deux partitions a la partition final
        nonTraiter.add(partition1);
        nonTraiter.add(partition2);
        System.out.println("\nVoici les deux parttions de departs : ");
        System.out.println(nonTraiter);
        // je commencer a traiter les partitions
        while (!nonTraiter.isEmpty()) {
            // on travaill sur une partition s'il a plus d'un etat
            List<Etat> partition = nonTraiter.poll();
            // on cree une liste qui vas contenir les etats qui trahissent le congossa
            List<Etat> l = new ArrayList<Etat>();
            List<Etat> partitionInter = new ArrayList<Etat>(partition);

            if (partition.size() > 1) {
                // // on parcours les Etats de la partition
                // Queue<Etat> etatsNonTraiter = new ArrayDeque<Etat>(partition);
                for (Etat etat : partition) {
                    // // je parcours tous les symbol de l'alphabet de l'automate sauf epsilone
                    for (String symb : aut.getAlphabet()) {
                        if (!symb.equals(Constans.EPSILON)) {
                            // // si la trasition de l'etat sur un symbol de l'alphabet donne un etat qui
                            // // n'est pas dans la partition

                            if (!partition.contains(aut.getTransitionTable().getTransition(etat, symb).get(0))) {
                                // // j'ajoute l'etat a la liste des trahisseurs de la partition
                                if (!l.contains(etat)) {
                                    l.add(etat);

                                }
                                partitionInter.remove(etat);

                            }

                        }

                    }

                }
                if (partitionInter.isEmpty()) {
                    MesPartitions.add(l);
                } else
                    nonTraiter.add(l);

            }
            if (partitionInter.size() > 1) {
                for (String symb : aut.getAlphabet()) {
                    if (!symb.equals(Constans.EPSILON)) {
                        // // si la trasition de l'etat sur un symbol de l'alphabet donne un etat qui
                        // // n'est pas dans la partition
                        for (Etat etat : partitionInter) {
                            if (!partitionInter.contains(aut.getTransitionTable().getTransition(etat, symb).get(0))) {
                                test = 1;

                            }
                        }

                    }

                }
            }
            if (!partitionInter.isEmpty()) {
                if (test == 0) {
                    MesPartitions.add(partitionInter);
                } else {
                    nonTraiter.add(partitionInter);
                    test = 0;
                }
            }

        }

        // j'affiche mes partition
        System.out.println("voici les partitions creer apres la theorie du congossa :");

        System.out.println(MesPartitions);

        // maintenant on cree la table de transition de l'automate minimiser a partir de
        // la table de transition de l'AFD
        // table de transition vide
        Transitions transitionTable = new Transitions(aut.getAlphabet());
        // je parcours mes partitons
        for (List part : MesPartitions) {
            // je prend le premiere element dans chaque partitons
            Etat etat = (Etat) part.get(0);
            // je parours lalphabet sauf epsilone
            for (String symb : aut.getAlphabet()) {
                if (!symb.equals(Constans.EPSILON)) {
                    // si la partition na qu'un etat
                    if (part.size() == 1) {
                        int contenu = 0;// cette variable vas me dire si sur un symbole de lalphabet la trasition de mon
                                        // etat donne un etat qui est seule dans sa transition
                        for (List part2 : MesPartitions) {
                            if (part2.contains(aut.getTransitionTable().getTransition(etat, symb).get(0))
                                    && part2.size() == 1) {
                                contenu = 1;
                                break;

                            }
                        }
                        // si letat donne un etat qui est seul dans sa partition apres transition sur un
                        // symbole de lalpahbet j'ajoute cette transition a ma tabe de transition
                        if (contenu == 1) {
                            transitionTable.AjouterTransition(etat, symb,
                                    aut.getTransitionTable().getTransition(etat, symb).get(0));
                        }
                    }

                }
            }

        }
        return autMinimiser;
    }

}
