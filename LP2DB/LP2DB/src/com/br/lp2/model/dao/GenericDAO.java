package com.br.lp2.model.dao;

import java.util.List;

/**
 *
 * @author Camila
 */
public interface GenericDAO<E> {
    public boolean insert(E e); //C - CREATE
    
    public List<E> findAll(); //R - READ
    public E findById(long id);
    
    public boolean modify(E e); //U - UPDATE
    
    public boolean remove(E e); //D - DELETE
}
