package com.hantel.tasktracker.accountservice.repository;

import com.hantel.tasktracker.accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
