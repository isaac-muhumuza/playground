package io.sample.playground.patterns.behavioural.chain_of_responsibility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OoauthAuthentication implements AuthenticatorProcessor {

    private AuthenticatorProcessor nextHandler;

    @Override
    public void setNextHandler(AuthenticatorProcessor nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void authenticate(AuthenticationType authenticationType) {
        if (AuthenticationType.OAUTH.equals(authenticationType)) {
            log.info("Authentication handled by {}", authenticationType.name());
        } else {
            nextHandler.authenticate(authenticationType);
        }
    }
}
