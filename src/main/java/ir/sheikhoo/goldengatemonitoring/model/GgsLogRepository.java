package ir.sheikhoo.goldengatemonitoring.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GgsLogRepository extends JpaRepository<GgsLog, LocalDate> {
    GgsLog findFirstByOrderByTimeDesc();
    List<GgsLog> findAllByTime(LocalDateTime time);
    @Query("select a from GgsLog a where a.time >= :time")
    List<GgsLog> findAllWithTimeSevenDaysAgo(@Param("time") LocalDateTime time);
}
