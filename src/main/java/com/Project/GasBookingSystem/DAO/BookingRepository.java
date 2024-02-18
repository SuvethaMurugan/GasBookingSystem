package com.Project.GasBookingSystem.DAO;

import com.Project.GasBookingSystem.Entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity,Integer> {
}
