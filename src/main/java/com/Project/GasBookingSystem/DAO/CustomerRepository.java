package com.Project.GasBookingSystem.DAO;

import com.Project.GasBookingSystem.Entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {
}
