package com.maxdevsoft.people.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxdevsoft.people.dtos.AccountDto;
import com.maxdevsoft.people.model.AccountCreate;
import com.maxdevsoft.people.repository.AccountRepository;
import com.maxdevsoft.people.service.AccountServices;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class PeopleController {

    @Autowired
    private AccountServices accountServices;

    @Autowired
    private AccountRepository accountRepository;
    
    @PostMapping("/save")
    public ResponseEntity<AccountCreate> createAccountPeople (@RequestBody @Valid AccountDto aDto){

        AccountCreate accountCreate = new AccountCreate();
        BeanUtils.copyProperties(aDto, accountCreate);
        accountServices.savePeople(accountCreate);

        return new ResponseEntity<>(accountCreate, HttpStatus.CREATED);

    }

    @GetMapping("/list")
    public ResponseEntity<List<AccountCreate>> listAccountPeople (){

        return new ResponseEntity<>(accountServices.listPeople(), HttpStatus.OK);

    }

    @GetMapping("/list/{id_people}")
    public ResponseEntity<Object> listAccountPeopleId (@PathVariable("id_people") Long id){

        return new ResponseEntity<>(accountServices.listPeopleId(id), HttpStatus.OK);

    }

    @PutMapping("update/{id_people}")
    public ResponseEntity<Object> updatePeople (@PathVariable ("id_people") Long id, @RequestBody @Valid AccountDto aDto) {

        Optional<AccountCreate> pOptional = accountServices.listPeopleId(id);

        if(pOptional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Field Empty");
        }

        var peopleModel = pOptional.get();
        BeanUtils.copyProperties(aDto, peopleModel);

        return ResponseEntity.status(HttpStatus.OK).body(accountServices.savePeople(peopleModel));
    }

    @DeleteMapping("delete/{id_people}")
    public ResponseEntity<Object> deletePeople (@PathVariable ("id_people") Long id) {

        Optional<AccountCreate> pOptional = accountServices.listPeopleId(id);

        if(pOptional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }

        accountRepository.delete(pOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("People deleted success");
    }

}
