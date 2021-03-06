package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get all favorites Neighbours
     * @return {@link List}
     */
    List<Neighbour> getFavoriteNeighbours();


    /**
     * Get a Neighbour by id
     * @return {@link Neighbour}
     */
    public Neighbour getNeighbour(long id);

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Toggle favorite parameter
     * @param neighbour
     */
    void toggleFavorite(Neighbour neighbour);

}
