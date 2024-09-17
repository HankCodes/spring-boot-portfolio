package dev.henrik.holstad.portfolio.longrunningtask.models.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "login_audit_logs")
public class LoginAuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String username;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @NotNull
    private Result result;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public enum Result {
        SUCCESS,
        WRONG_PASSWORD,
        WRONG_PASSWORD_TOO_MANY_TIMES,
        USER_NOT_FOUND
    }
}
