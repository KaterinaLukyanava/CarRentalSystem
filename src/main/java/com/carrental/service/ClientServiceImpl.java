package com.carrental.service;

import com.carrental.dao.model.Client;
import com.carrental.dao.model.Role;
import com.carrental.dao.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clientRepository = clientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public Client findClientByUsername(String userName) {
        return clientRepository.findClientByUsername(userName);
    }

    @Override
    public void save(Client client) {
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        clientRepository.save(client);

    }

    @Override
    public List<Client> findAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client findClientById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        final Client user = findClientByUsername(userName);
        Set<Role> roles= new HashSet<>();
        if(user==null)
            throw new UsernameNotFoundException("пользователь с именем "+userName+" не существует!");
        else
            System.out.println("Success");
        roles.add(user.getRole());
        return new User(userName,user.getPassword(),roles);
    }
}
