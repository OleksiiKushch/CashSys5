package com.finalprojultimate.model.entity;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class User {

    private static final Logger logger = LoggerFactory.getLogger(User.class);
    private static final EmailValidator validator = EmailValidator.getInstance();
    public static final String hashAlgorithm = "SHA-256"; // MD5

    private int id;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String passHash;
    private Role role;

    public User() {
        // default constructor
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static String hashPassword(String input) {
        return hashPassword(input, hashAlgorithm);
    }

    public static String hashPassword(String input, String algorithm) {
        String result = null;
        if (input == null) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(input.getBytes());
            result = DatatypeConverter.printHexBinary(md.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
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

    public enum Role {
        CASHIER (1, "cashier"),
        SENIOR_CASHIER (2, "senior cashier"),
        COMMODITY_EXPERT (3, "commodity expert");

        private final int id;
        private final String name;

        Role(int id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * return null if element by id not found
         */
        public static Role getById(int id) {
            for (Role role : Role.values()) {
                if (role.getId() == id)
                    return role;
            }
            return null;
        }

        /**
         * return null if element by name not found
         */
        public static Role getByName(String name) {
            for (Role role : Role.values()) {
                if (Objects.equals(role.getName(), name))
                    return role;
            }
            return null;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Role{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
