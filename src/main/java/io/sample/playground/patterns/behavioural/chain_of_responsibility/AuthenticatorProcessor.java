package io.sample.playground.patterns.behavioural.chain_of_responsibility;

public interface AuthenticatorProcessor {

    void setNextHandler(AuthenticatorProcessor nextHandler);

    void authenticate(AuthenticationType authenticationType);
}
