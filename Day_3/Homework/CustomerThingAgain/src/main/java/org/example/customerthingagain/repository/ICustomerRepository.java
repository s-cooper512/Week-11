package org.example.customerthingagain.repository;

import org.example.customerthingagain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    public List<Customer> findAllByName(String name);

    public List<Customer> findCustomerByLastFourDigitsOfCard(Integer lastFour);

    public List<Customer> findAllByNameAndLastFourDigitsOfCard(String name, Integer lastFourDigits);
}

