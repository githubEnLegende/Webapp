package org.oxyl.newro;

import junit.framework.TestCase;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.oxyl.core.model.Page;
import org.oxyl.core.model.Stagiaire;

import java.util.List;

import static org.mockito.Mockito.verify;

public class PageTest extends TestCase {
    @Mock
    Stagiaire stagiaire;

    public void testAddContent() {
        // Création d'un mock pour le contenu de la page
        Page<Stagiaire> page = new Page<>();
        List<Stagiaire> contentMock = Mockito.mock(List.class);
        page.setContent(contentMock);

        // Ajout de contenu à la page
        page.addContent(stagiaire);

        // Vérification que le contenu est ajouté à la liste
        verify(contentMock).add(stagiaire);
    }

    public void testEmptyContent() {
        // Création d'un mock pour le contenu de la page
        Page<Stagiaire> page = new Page<>();
        List<Stagiaire> contentMock = Mockito.mock(List.class);
        page.setContent(contentMock);

        // Appel à la méthode emptyContent()
        page.emptyContent();

        // Vérification que la méthode clear() est appelée sur la liste
        verify(contentMock).clear();
    }
}