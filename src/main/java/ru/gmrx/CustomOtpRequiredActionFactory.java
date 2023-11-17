package ru.gmrx;

import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.authentication.RequiredActionFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class CustomOtpRequiredActionFactory implements RequiredActionFactory {

    @Override
    public String getId() {
        return CustomOtpRequiredAction.PROVIDER_ID;
    }

    @Override
    public RequiredActionProvider create(KeycloakSession session) {
        return new CustomOtpRequiredAction();
    }

    @Override
    public void init(org.keycloak.Config.Scope config) {
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public void close() {
    }

    @Override
    public String getDisplayText() {
        return "AAB Custom OTP Required Action";
    }
}
