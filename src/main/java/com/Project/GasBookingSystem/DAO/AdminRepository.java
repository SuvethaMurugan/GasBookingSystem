package com.Project.GasBookingSystem.DAO;

import com.Project.GasBookingSystem.Entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AdminRepository extends JpaRepository<AdminEntity,Integer> {
}
