package com.techimage.projectjfx.repository.generic;

import java.util.List;

public interface GenericCrud<E,T> {
    public Integer countAll ();
    public void save(E entity);
    public List<E> findAll(Integer page);


    public E findById(T id);
    public void update (E entity, T id);
    public void delete (T id);
}
