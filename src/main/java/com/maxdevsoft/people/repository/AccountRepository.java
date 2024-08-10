package com.maxdevsoft.people.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxdevsoft.people.model.AccountCreate;

@Repository
public interface AccountRepository extends JpaRepository<AccountCreate, Long>{
    
}
