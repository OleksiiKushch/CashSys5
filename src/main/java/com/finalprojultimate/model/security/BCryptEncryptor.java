package com.finalprojultimate.model.security;

import com.finalprojultimate.exception.ApplicationException;
import com.finalprojultimate.util.MessageKey;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptEncryptor implements Securable {

    private static final int LOG_ROUNDS = 13;
    private static final String BCRYPT_PREFIX = "$2a$";
    private static final String INVALID_HASH_PROVIDED_FOR_COMPARISON_LOG_MSG =
            "Invalid hash provided for comparison";

    @Override
    public String encryptPassword(String password) {
        String salt = BCrypt.gensalt(LOG_ROUNDS);
        return BCrypt.hashpw(password,salt);
    }

    @Override
    public boolean checkPassword(String password, String hash) {
        if(hash == null || !hash.startsWith(BCRYPT_PREFIX))
            throw new ApplicationException()
                    .addLogMessage(INVALID_HASH_PROVIDED_FOR_COMPARISON_LOG_MSG)
                    .addMessage(MessageKey.ERROR_ILLEGAL_PASSWORD_DECRYPTED);

        return BCrypt.checkpw(password,hash);
    }
}
