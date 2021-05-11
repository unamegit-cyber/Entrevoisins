package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void getFavoritesNeighboursWithSuccess() {
        service.getNeighbours().get(0).isFavorite(true);
        service.getNeighbours().get(1).isFavorite(true);
        List<Neighbour> favoritesNeighbours = service.getFavoriteNeighbours();
        List<Neighbour> expectedNeighbours = Arrays.asList(service.getNeighbours().get(0), service.getNeighbours().get(1));
        assertThat(favoritesNeighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
        service.getNeighbours().get(0).isFavorite(false);
        service.getNeighbours().get(1).isFavorite(false);
    }

    @Test
    public void getNeighbourWithSuccess() {
        Neighbour neighbour = service.getNeighbour(3);
        Neighbour expectedNeighbour = service.getNeighbours().get(2);
        assertEquals(neighbour, expectedNeighbour);
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void createNeighbourWithSuccess() {
        Neighbour neighbourToCreate = new Neighbour(1, "test create", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "adress test",
                "+33 0 00 00 00 00", "http://facebook.com/test",  "Bonjour !Je fais un test", false)
        ;
        service.createNeighbour(neighbourToCreate);
        assertTrue(service.getNeighbours().contains(neighbourToCreate));
    }

    @Test
    public void toggleFavoriteOffWithSuccess() {
        service.getNeighbours().get(0).isFavorite(true);
        Neighbour neighbourtoToggleFavorite = service.getNeighbours().get(0);
        service.toggleFavorite(neighbourtoToggleFavorite);
        assertFalse(service.getFavoriteNeighbours().contains(neighbourtoToggleFavorite));
        service.getNeighbours().get(0).isFavorite(false);
    }

    @Test
    public void toggleFavoriteOnWithSuccess() {
        service.getNeighbours().get(0).isFavorite(false);
        Neighbour neighbourtoToggleFavorite = service.getNeighbours().get(0);
        service.toggleFavorite(neighbourtoToggleFavorite);
        assertTrue(service.getFavoriteNeighbours().contains(neighbourtoToggleFavorite));
        service.getNeighbours().get(0).isFavorite(false);
    }

}
