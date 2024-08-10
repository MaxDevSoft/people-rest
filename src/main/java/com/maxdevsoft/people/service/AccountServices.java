package com.maxdevsoft.people.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxdevsoft.people.model.AccountCreate;
import com.maxdevsoft.people.repository.AccountRepository;

@Service
public class AccountServices implements AccountServiceImplement{

    @Autowired
    private AccountRepository accountRepository;
    

    public AccountCreate savePeople (AccountCreate accountCreate){

        return accountRepository.save(accountCreate);

    }

    public List<AccountCreate> listPeople (){

        return accountRepository.findAll();

    }

    public Optional<AccountCreate> listPeopleId (Long id){

        return accountRepository.findById(id);

    }


}
