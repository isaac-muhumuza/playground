package io.sample.playground.patterns.behavioural.chain_of_responsibility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenBasedAuthentication implements AuthenticatorProcessor {

    @Override
    public void setNextHandler(AuthenticatorProcessor nextHandler) {
        // Do nothing as it's the last in chain
    }

    @Override
    public void authenticate(AuthenticationType authenticationType) {
        if (AuthenticationType.TOKEN_BASED.equals(authenticationType)) {
            log.info("Authentication handled by {}", authenticationType.name());
        } else {
            log.warn("Invalid authentication type used for {}", authenticationType.name());
        }
    }
}
