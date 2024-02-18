package com.Project.GasBookingSystem.DAO;

import com.Project.GasBookingSystem.Entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<BankEntity,Integer> {
}
