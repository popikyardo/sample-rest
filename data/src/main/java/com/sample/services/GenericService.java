package com.sample.services;

import java.util.List;

/**
 * Created by popikyardo on 06/08/2015.
 */
public interface GenericService<E, K> {
    /**
     *
     * @param entity
     * @return
     */
    public E save(E entity);

    /**
     *
     * @return
     */
    public List<E> findAll();

    /**
     *
     * @return
     */
    public List<E> findAll(Iterable<K> ids);

    /**
     *
     * @param id
     * @return
     */
    public E findOne(K id);

    /**
     *
     * @param entity
     */
    public void remove(E entity);

    /**
     *
     * @param id
     */
    public void removeById(K id);
}
