package com.sursindmitry.crud.service;

public interface RestService {
    String getAllUsers();

    String addUser(String cookie);

    String updateUser(String cookie);

    String deleteUser(String cookie);

}
