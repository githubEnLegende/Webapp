//package org.oxyl.newro;
//import junit.framework.TestCase;
//
//import java.util.Scanner;
//import static org.mockito.Mockito.*;
//import static org.oxyl.newro.FonctionDIsplay.DisplayAfficherStagiaire;
//import static org.oxyl.persistence.StagiaireDAO.detailStagiaire;
//
//
//public class DisplayAfficherStagiaireTest extends TestCase {
//
//    public void testDisplayAfficherStagiaireWithValidID() {
//        // Création d'un mock pour Scanner
//        Scanner scMock = mock(Scanner.class);
//
//        // Simulation de l'entrée utilisateur
//        when(scMock.nextLine()).thenReturn("123");
//
//        // Appel de la fonction à tester
//        when(DisplayAfficherStagiaire(scMock));
//
//        // Vérification que detailStagiaire est appelée avec l'ID entré par l'utilisateur
//        verify();
//        detailStagiaire(123);
//    }
//
//    public void testDisplayAfficherStagiaireWithInvalidID() {
//        // Création d'un mock pour Scanner
//        Scanner scMock = mock(Scanner.class);
//
//        // Simulation de l'entrée utilisateur
//        when(scMock.nextLine()).thenReturn("abc");
//
//        // Appel de la fonction à tester
//        DisplayAfficherStagiaire(scMock);
//
//        // Vérification que "not a valid ID" est affiché
//        verify(System.out).println("not a valid ID");
//    }
//
//}
