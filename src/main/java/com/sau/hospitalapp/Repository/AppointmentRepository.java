package com.sau.hospitalapp.Repository;

import com.sau.hospitalapp.Model.Appointment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    @Modifying
    @Transactional
    @Query(value="delete from Patient u where u.id = :id")
    void deleteById(Long id);
}
