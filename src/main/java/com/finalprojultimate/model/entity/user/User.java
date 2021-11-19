package com.finalprojultimate.model.entity.user;

import com.finalprojultimate.model.entity.Entity;
import org.apache.commons.validator.routines.EmailValidator;

public class User extends Entity {
    private static final long serialVersionUID = 5222693753031074609L;

    private static final EmailValidator validator = EmailValidator.getInstance();
    public static final String hashAlgorithm = "SHA-256"; // MD5

    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String passHash;
    private Role role;

    public User() {
        // default constructor
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static boolean checkEmail(String input) {
        return validator.isValid(input);
    }

    public String getFormattedName() {
        return lastName + " " + firstName.charAt(0) + "." + middleName.charAt(0) + ".";
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passHash='" + passHash + '\'' +
                ", role=" + role +
                '}';
    }

    public static class Builder {
        private final User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder withId(int id) {
            newUser.id = id;
            return this;
        }

        public Builder withEmail(String email) {
            newUser.email = email;
            return this;
        }

        public Builder withFirstName(String firstName) {
            newUser.firstName = firstName;
            return this;
        }

        public Builder withMiddleName(String middleName) {
            newUser.middleName = middleName;
            return this;
        }

        public Builder withLastName(String lastName) {
            newUser.lastName = lastName;
            return this;
        }

        public Builder withPassHash(String passHash) {
            newUser.passHash = passHash;
            return this;
        }

        public Builder withRoleId(Role role) {
            newUser.role = role;
            return this;
        }

        public User build() {
            return newUser;
        }
    }
}
