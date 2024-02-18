package com.Project.GasBookingSystem.DAO;

import com.Project.GasBookingSystem.Entity.PaymentEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
}
