package ir.ansarit.controller;

import ir.ansarit.entity.Account;
import ir.ansarit.repository.AccountRepository;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public class AccountController {

    @Autowired
    private AccountRepository repository;

    @PostMapping("account")
    public ResponseEntity create(Account account) {
       /* PasswordGenerator passwordGenerator = new PasswordGenerator();
        passwordGenerator.generatePassword(4);
        account.setPassword(String.valueOf(passwordGenerator));*/

        account.setPassword((long) Math.random());
        return new ResponseEntity(repository.save(account), HttpStatus.OK);
    }
}
