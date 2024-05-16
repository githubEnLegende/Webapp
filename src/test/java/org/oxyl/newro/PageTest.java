package org.oxyl.newro;

import junit.framework.TestCase;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.oxyl.model.Page;
import org.oxyl.model.Stagiaire;

import java.util.List;

import static org.mockito.Mockito.verify;

public class PageTest extends TestCase {
    @Mock
    Stagiaire stagiaire;

    public void testAddContent() {
        // Création d'un mock pour le contenu de la page
        Page<Stagiaire> page = new Page<>();
        List<Stagiaire> contentMock = Mockito.mock(List.class);
        page.setStagiaires(contentMock);

        // Ajout de contenu à la page
        page.addContent(stagiaire);

        // Vérification que le contenu est ajouté à la liste
        verify(contentMock).add(stagiaire);
    }

    public void testEmptyContent() {
        // Création d'un mock pour le contenu de la page
        Page<Stagiaire> page = new Page<>();
        List<Stagiaire> contentMock = Mockito.mock(List.class);
        page.setStagiaires(contentMock);

        // Appel à la méthode emptyContent()
        page.emptyContent();

        // Vérification que la méthode clear() est appelée sur la liste
        verify(contentMock).clear();
    }
}