package dev.henrik.holstad.portfolio.longrunningtask.repositories;

import dev.henrik.holstad.portfolio.longrunningtask.models.dao.LoginAuditLog;
import org.springframework.data.repository.CrudRepository;

public interface LogInAuditLogRepository extends CrudRepository<LoginAuditLog, Integer> {
}
