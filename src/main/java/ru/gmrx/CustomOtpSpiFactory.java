package ru.gmrx;

import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.Config;
import java.util.List;

public class CustomOtpSpiFactory implements AuthenticatorFactory {

    @Override
    public String getId() {
        return CustomOtpSpi.PROVIDER_ID;
    }

    @Override
    public Authenticator create(KeycloakSession session) {
        return new CustomOtpSpi();
    }

    @Override
    public void init(Config.Scope config) {
    }

    @Override
    public String getDisplayType() {
        return "AAA Custom OTP SPI";
    }

    @Override
    public String getReferenceCategory() {
        return "otp";
    }

    @Override
    public boolean isConfigurable() {
        return false;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return new AuthenticationExecutionModel.Requirement[] {
                AuthenticationExecutionModel.Requirement.REQUIRED,
                AuthenticationExecutionModel.Requirement.ALTERNATIVE,
                AuthenticationExecutionModel.Requirement.DISABLED
        };
    }

    @Override
    public boolean isUserSetupAllowed() {
        return true;
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return null;
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public void close() {
    }

    @Override
    public String getHelpText() {
        return "AAA Custom OTP SPI for SMS authentication";
    }
}
