# Stratégies Fetch & Cascade — AutoLoc

## Contrat ↔ Paiement
- **Fetch** : LAZY
- **Cascade** : ALL + orphanRemoval
- **Justification** : Un paiement n'existe que rattaché à son contrat (composition).
  Supprimer le contrat supprime ses paiements. Retirer un paiement de la liste
  le supprime en base (orphanRemoval). Le LAZY évite de charger tous les paiements
  à chaque lecture de contrat.

## Agence → Vehicule
- **Fetch** : LAZY
- **Cascade** : Aucune
- **Justification** : Un véhicule peut être transféré à une autre agence ou rester
  orphelin. Il ne doit donc pas être supprimé avec son agence.

## Agence → Employee
- **Fetch** : LAZY
- **Cascade** : Aucune
- **Justification** : Un employé peut changer d'agence sans être supprimé.

## Vehicule ↔ Equipement
- **Fetch** : LAZY
- **Cascade** : Aucune
- **Justification** : Les équipements sont partagés entre véhicules.
  Supprimer un véhicule ne doit pas supprimer les équipements. Utilisation d'un Set
  pour éviter les doublons.

## Client → Reservation
- **Fetch** : LAZY
- **Cascade** : PERSIST
- **Justification** : Quand on crée un client avec ses premières réservations,
  on veut qu'elles soient persistées automatiquement. Mais supprimer un client
  ne doit pas supprimer ses réservations (historique).

## Reservation → Vehicule
- **Fetch** : LAZY
- **Cascade** : Aucune
- **Justification** : Association unidirectionnelle. Une réservation référence
  un véhicule, mais on ne supprime pas un véhicule parce qu'une réservation
  le référence.

## Reservation ↔ Contrat
- **Fetch** : LAZY
- **Cascade** : ALL (côté Contrat)
- **Justification** : Un contrat n'existe qu'avec sa réservation. On propage
  les opérations de persistance/suppression.

## Vehicule → Maintenance
- **Fetch** : LAZY
- **Cascade** : PERSIST
- **Justification** : Quand on enregistre un véhicule avec son historique de
  maintenance, les maintenances sont persistées. Mais supprimer un véhicule
  ne supprime pas l'historique (traçabilité).