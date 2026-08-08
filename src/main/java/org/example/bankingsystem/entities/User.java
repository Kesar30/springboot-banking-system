package org.example.bankingsystem.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.bankingsystem.enums.UserStatus;
import org.hibernate.annotations.Audited;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Audited
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String fullName;

    private String email;

    private String password;

    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private UserStatus status;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onaddUser(){
        createdAt = LocalDateTime.now();
        status = UserStatus.ACTIVE;
    }
    @PreUpdate
    public void onupdateUser(){
        updatedAt = LocalDateTime.now();

    }

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Account> account;

    public User() {}

    public User(Integer userId, String fullName, String email, String password, String phoneNumber, UserStatus status, LocalDateTime createdAt, LocalDateTime updatedAt, List<Account> account) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.account = account;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}