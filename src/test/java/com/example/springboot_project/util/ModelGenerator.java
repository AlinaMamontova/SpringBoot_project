package com.example.springboot_project.util;

import com.example.springboot_project.entity.Bank;
import com.example.springboot_project.entity.Client;
import com.example.springboot_project.entity.Currency;
import com.example.springboot_project.entity.User;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ModelGenerator {
    @Autowired
    @Lazy
    private Faker faker;//валидирует
    private Model<User> userModel;
    private Model<Bank> bankModel;
    private Model<Client> clientModel;
    private Model<Currency> currencyModel;


    @PostConstruct
    private void initUser() {
        userModel = Instancio.of(User.class)
                .ignore(Select.field(User::getId))
                .toModel();
        bankModel = Instancio.of(Bank.class)
                .ignore(Select.field(Bank::getId))
                .toModel();
    }

    public Model<User> getUserModel() {
        if (userModel == null) {
            userModel = Instancio.of(User.class)
                    .ignore(Select.field(User::getId))
                    .toModel();
        }
        return userModel;
    }

    public Model<Bank> getBankModel() {
        if (bankModel == null) {
            bankModel = Instancio.of(Bank.class)
                    .ignore(Select.field(Bank::getId))
                    .toModel();
        }
        return bankModel;
    }
}
