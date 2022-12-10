package ir.sheikhoo.goldengatemonitoring.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GgsLogRepository extends JpaRepository<GgsLog, LocalDate> {
    GgsLog findFirstByOrderByTimeDesc();
    List<GgsLog> findAllByTime(LocalDateTime time);
}
