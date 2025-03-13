package io.sample.playground.patterns.behavioural.chain_of_responsibility;


public class AuthenticationChainDelegation {

    public static void main (String[] args) {
        AuthenticatorProcessor basicAuthentication = new BasicAuthentication();
        AuthenticatorProcessor oauthAuthentication = new OoauthAuthentication();
        AuthenticatorProcessor tokenBasedAuthentication = new TokenBasedAuthentication();

        basicAuthentication.setNextHandler(oauthAuthentication);
        oauthAuthentication.setNextHandler(tokenBasedAuthentication);

        basicAuthentication.authenticate(AuthenticationType.BASIC);
        basicAuthentication.authenticate(AuthenticationType.SAML);
        basicAuthentication.authenticate(AuthenticationType.TOKEN_BASED);
        basicAuthentication.authenticate(AuthenticationType.OAUTH);
    }
}
