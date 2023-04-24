package com.express.repository;

import com.express.model.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts,Long> {

    Accounts findByCustomerId(int customerId);
}
