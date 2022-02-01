package org.module.springmvc.dao;

public interface DAOInterface {
    void connect() throws ClassNotFoundException;
    void disconnect();
}
