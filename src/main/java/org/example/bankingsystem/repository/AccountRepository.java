package org.example.bankingsystem.repository;

import org.example.bankingsystem.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
//    Account findByAccountNo(Long accountNo);
//    @Query("select u from Account u where u.user.userId = ?1")
//    List<Account> findByUser_id(int id);
    List<Account> findByUserUserId(Integer userId);

}
