package ir.sheikhoo.goldengatemonitoring.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface GgsLogRepository extends JpaRepository<GgsLog, LocalDate> {
}
