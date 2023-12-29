package com.techimage.projectjfx.repository;

import com.techimage.projectjfx.annotations.entity.Entity;
import com.techimage.projectjfx.exception.NotFoundEntity;
import com.techimage.projectjfx.util.DbUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;


public  class GenericCrudImpl<E, T> implements GenericCrud<E, T> {

    private E genericEntity;
    public GenericCrudImpl(E entity) {
        this.genericEntity = entity;
    }

    private ArrayList<String> getColumnsName (E entity) {
        Field [] fieldArray = entity.getClass().getDeclaredFields();
        List<Field> fields =  Arrays.stream(fieldArray).collect(Collectors.toList());
        ArrayList<String> columns = new ArrayList<>();
        for(Field field : fields) {
            columns.add(field.getName());
        }
        Collections.sort(columns);
        return columns;
    }

    private ArrayList<Method> getMethodsGet (E entity) {
        Method [] declaredMethods = entity.getClass().getDeclaredMethods();
        List<Method> methods =  Arrays.stream(declaredMethods).toList();
        HashMap<String, Method> methodsGet = new HashMap<>();
        ArrayList<Method> finalMethod = new ArrayList<>();
        for(Method method : methods) {
            if(method.getName().contains("get")) {
                methodsGet.put(method.getName(), method);
            }
        }
        ArrayList<String> methodsName = new ArrayList<>(methodsGet.keySet());
        Collections.sort(methodsName);
        for(String methodName : methodsName) {
                finalMethod.add(methodsGet.get(methodName));
        }
        return finalMethod;
    }


    public void save(E entity) {
        StringBuilder columnNames = new StringBuilder();
        StringBuilder values = new StringBuilder();
        ArrayList<String> fields = getColumnsName(entity);
        for (int i = 0; i < fields.size(); i++) {
            columnNames.append(fields.get(i));
            values.append("?");
            if(i < fields.size() - 1) {
                columnNames.append(",");
                values.append(",");
            }
        }
        String insert = String.format("INSERT INTO %s (%s) VALUES (%s)",
                entity.getClass().getSimpleName(),
                columnNames.toString(),
                values.toString());

        int index = 0;
        try {
            PreparedStatement statement = Objects.requireNonNull(DbUtil.dbConnect()).prepareStatement(insert);
            for (Method method : getMethodsGet(entity)) {

                if(method.getName().contains("get")) {
                    index++;
                    if(method.getReturnType().equals(Integer.class)) {
                        statement.setInt(index, (Integer) method.invoke(entity));
                    }
                    if(method.getReturnType().equals(String.class)) {
                        statement.setString(index, (String) method.invoke(entity));
                    }
                }
            }
            statement.executeUpdate();
            statement.close();
            DbUtil.dbDisconnect();
        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }


    }

    public List<E> findAll() {
        List<E> list = new ArrayList<>();
        Statement statements = null;
        E entity = null;
        try {
            entity = (E) this.genericEntity.getClass().getDeclaredConstructor().newInstance();
        } catch (InstantiationException
                 | IllegalAccessException
                 | InvocationTargetException
                 | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try {
            statements = DbUtil.dbConnect().createStatement();
            ResultSet resultSet =  statements.executeQuery(String.format("select * from %s",
                    entity.getClass().getSimpleName()));
            while (resultSet.next()) {
                Method[] methods =  entity.getClass().getDeclaredMethods();
                for (Method method : methods) {
                    if(method.getName().contains("set")) {
                        method.invoke(entity, resultSet.getObject(
                                method.getName().toLowerCase().substring(3)));
                        list.add(entity);
                    }
                }
                System.out.println(entity);
            }
            DbUtil.dbDisconnect();
        } catch (SQLException
                 | IllegalAccessException
                 | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private ResultSet isExist(T id, Class<?> entity) {
        String idName = null;
        try {

            idName =  entity.getAnnotation(Entity.class).pk();

            String findQuery =   String.format("SELECT * FROM %s WHERE %s = ?",
                    entity.getSimpleName(),
                    idName);
            PreparedStatement statement = DbUtil.dbConnect().prepareStatement(findQuery);

            if(id.getClass().equals(Integer.class)) {
                statement.setInt(1, (Integer) id);
            }
            if(id.getClass().equals(String.class)) {
                statement.setString(1, (String) id);
            }

            ResultSet resultSetTest = statement.executeQuery();
            if(!resultSetTest.next()) {
                throw new NotFoundEntity(String.format("%s avec l'id %s n'existe pas",
                        entity.getSimpleName(), id));
            }
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {

            DbUtil.dbDisconnect();

        }
    }
    public E findById(T id) {
        try {
            var entity = this.genericEntity.getClass().
                    getDeclaredConstructor().newInstance();

            ResultSet resultSet = this.isExist(id, entity.getClass());
                while (resultSet.next()) {
                    Method[] methods = entity.getClass().getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.getName().contains("set")) {
                            method.invoke(entity, resultSet.getObject(
                                    method.getName().toLowerCase().substring(3)));
                        }
                    }
                }
            resultSet.close();
            return (E) entity;

        } catch (InstantiationException
                 | NoSuchMethodException
                 | IllegalAccessException
                 | InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(E entity, T id) {
        this.isExist(id, entity.getClass());
        StringBuilder columnsToChange = new StringBuilder();
        ArrayList<String> fields = getColumnsName(entity);
        for (int i = 0; i < fields.size(); i++) {
            columnsToChange.append(String.format("%s = ?",
                    fields.get(i)));
            if(i < fields.size() - 1) {
                columnsToChange.append(",");
            }
        }
        String idName = entity.getClass().getAnnotation(Entity.class).pk();
        String updateQuery = String.format("UPDATE %s SET %s WHERE %s = ?",
                entity.getClass().getSimpleName(),
                columnsToChange.toString(),
                idName);

        System.out.println(updateQuery);

        int index = 0;
        try {
            Method pkGetMethod = null;
            PreparedStatement statement = Objects.requireNonNull(DbUtil.dbConnect()).prepareStatement(updateQuery);
            for (Method method : getMethodsGet(entity)) {
                if(method.getName().contains("get")) {
                    if(method.getName().toLowerCase().contains(idName)) {
                        pkGetMethod = method;
                    }
                    index++;
                    if(method.getReturnType().equals(Integer.class)) {
                        statement.setInt(index, (Integer) method.invoke(entity));
                    }
                    if(method.getReturnType().equals(String.class)) {
                        statement.setString(index, (String) method.invoke(entity));
                    }
                }
            }
            index++;
            if(id.getClass().equals(Integer.class)) {
                statement.setInt(index, (Integer) id);
            }
            if(id.getClass().equals(String.class)) {
                statement.setString(index, (String) id);
            }
            statement.executeUpdate();
            statement.close();
            DbUtil.dbDisconnect();
        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(T id) {
        this.isExist(id, this.genericEntity.getClass());
        String idName = null;
        try {
            var entity =  this.genericEntity.getClass();
            idName =  entity.getAnnotation(Entity.class).pk();

            String deleteQuery =   String.format("DELETE  FROM %s WHERE %s = ?",
                    entity.getSimpleName(),
                    idName);
            PreparedStatement statement = DbUtil.dbConnect().prepareStatement(deleteQuery);

            if(id.getClass().equals(Integer.class)) {
                statement.setInt(1, (Integer) id);
            }
            if(id.getClass().equals(String.class)) {
                statement.setString(1, (String) id);
            }
            statement.executeUpdate();
            DbUtil.dbDisconnect();
            System.out.println("deleted!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
