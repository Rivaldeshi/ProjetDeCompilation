package Automate;

import Utils.Constans;
import Utils.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;

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

    
        // maintenant on cree la table de transition de l'automate minimiser a partir de
        // la table de transition de l'AFD
        // table de transition vide
        // je parcours mes partitons
        for (List<Etat> part : MesPartitions) {
            // je prend le premiere element dans chaque partitons
            Etat etat = (Etat) part.get(0);

            // je parours lalphabet sauf epsilone
            for (String symb : aut.getAlphabet()) {
                if (!symb.equals(Constans.EPSILON)) {
                    // si la partition na qu'un etat
                    if (part.size() == 1) {
                        int contenu = 0;// cette variable vas me dire si sur un symbole de lalphabet la trasition de mon
                                        // etat donne un etat qui est seule dans sa partition
                        List<Etat> partition = new ArrayList<Etat>();
                        for (List<Etat> part2 : MesPartitions) {
                            if (part2.contains(aut.getTransitionTable().getTransition(etat, symb).get(0))) {
                                partition = part2;

                                contenu = 1;
                            }
                        }

                        // si letat donne un etat qui est dans une partition apres transition sur un
                        // symbole de lalpahbet j'ajoute cette transition a ma tabe de transition
                        if (contenu == 1) {
                            if (!autMinimiser.getStates().contains(etat)) {
                                autMinimiser.ajouterUnEtat(etat);
                            }
                            autMinimiser.ajouterUneTransition(etat, symb, partition.get(0));
                        } else {
                            throw new ValidationException("pourquoi  la transition de l'etat :" + etat
                                    + " sur le symbole : " + symb + " ne donne aucun etat ?");

                        }
                    } else {// si la taille de la partition depasse un
                        if (part.contains(aut.getTransitionTable().getTransition(etat, symb).get(0))) {
                            if (!autMinimiser.getStates().contains(etat)) {
                                autMinimiser.ajouterUnEtat(etat);
                            }
                            autMinimiser.ajouterUneTransition(etat, symb, etat);
                        } else {
                            if (!autMinimiser.getStates().contains(etat)) {
                                autMinimiser.ajouterUnEtat(etat);
                            }
                            autMinimiser.ajouterUneTransition(etat, symb,
                                    aut.getTransitionTable().getTransition(etat, symb).get(0));

                        }
                    }

                }
            }

        }
        autMinimiser.setInitialState(aut.getInitialState());
        return autMinimiser;
    }

}
