package fr.uvsq.cprog.collex;

/**
 * Interface Commande : toutes les commandes DNS doivent implémenter cette interface.
 */
interface Commande {
    String execute(Dns dns);
}
