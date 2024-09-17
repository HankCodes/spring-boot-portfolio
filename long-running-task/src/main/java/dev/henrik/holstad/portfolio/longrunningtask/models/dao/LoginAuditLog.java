package dev.henrik.holstad.portfolio.longrunningtask.models.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity(name = "login_audit_logs")
public class LoginAuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private Instant timestamp;
    private Result result;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Result getResult() {
        return result;
    }

    public enum Result {
        SUCCESS,
        WRONG_PASSWORD,
        WRONG_PASSWORD_TOO_MANY_TIMES,
        USER_NOT_FOUND
    }
}
