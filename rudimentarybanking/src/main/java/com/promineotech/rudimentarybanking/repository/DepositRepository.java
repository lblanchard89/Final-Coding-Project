package com.promineotech.rudimentarybanking.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.rudimentarybanking.entites.Deposit;

public interface DepositRepository extends CrudRepository<Deposit, Long>{

}