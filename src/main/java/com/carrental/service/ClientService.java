package com.carrental.service;

import com.carrental.dao.model.Client;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ClientService extends UserDetailsService {
    Client findClientByUsername(String userName);
    void save(Client client);
    List<Client> findAllClient();
    void deleteById(Integer id);
    Client findClientById(Integer id);


}
