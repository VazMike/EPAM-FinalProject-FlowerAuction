package com.viazovski.flowerauction.model;

import java.sql.Date;
import java.util.Objects;

/**
 * {@code Buyer} represents Buyer table.
 */
public class Buyer {

    private int buyerId;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String login;

    private String passwordHash;

    private String passwordSalt;

    private Language language = Language.EN;

    private Role role = Role.USER;

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyer buyer = (Buyer) o;
        return buyerId == buyer.buyerId &&
                Objects.equals(firstName, buyer.firstName) &&
                Objects.equals(lastName, buyer.lastName) &&
                Objects.equals(dateOfBirth, buyer.dateOfBirth) &&
                Objects.equals(login, buyer.login) &&
                Objects.equals(passwordHash, buyer.passwordHash) &&
                Objects.equals(passwordSalt, buyer.passwordSalt) &&
                language == buyer.language &&
                role == buyer.role;
    }

    @Override
    public int hashCode() {

        return Objects.hash(buyerId, firstName, lastName, dateOfBirth, login, passwordHash, passwordSalt, language, role);
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "buyerId=" + buyerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", login='" + login + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
                ", language=" + language +
                ", role=" + role +
                '}';
    }
}
