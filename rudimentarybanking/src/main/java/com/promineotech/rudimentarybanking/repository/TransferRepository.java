package com.promineotech.rudimentarybanking.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.rudimentarybanking.entites.Transfer;

public interface TransferRepository extends CrudRepository<Transfer, Long> {

}