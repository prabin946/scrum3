package dao;

import model.User;

import java.util.List;

public interface DAO<T> {

    List<T> findAll();
    T findById(int id);
    T create(T object);
    boolean update(T object);
    boolean delete(T object);
}
