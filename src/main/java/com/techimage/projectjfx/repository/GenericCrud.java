package com.techimage.projectjfx.repository;

import java.util.List;

public interface GenericCrud<E,T> {
    public void save(E entity);
    public List<E> findAll();
    public E findById(T id);
    public void update (E entity, T id);
    public void delete (T id);
}
