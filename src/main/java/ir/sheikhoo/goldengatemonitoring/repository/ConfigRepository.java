package ir.sheikhoo.goldengatemonitoring.repository;

import ir.sheikhoo.goldengatemonitoring.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<Config,Long> {
}
