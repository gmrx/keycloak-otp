package ru.gmrx;

import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.forms.login.LoginFormsProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;


public class CustomOtpSpi implements Authenticator {
    private static final Logger logger = Logger.getLogger(CustomOtpSpi.class);
    public static final String PROVIDER_ID = "custom-otp-spi";
    private static final String TEMPORARY_OTP = "123456";

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        String phoneNumber = context.getUser().getFirstAttribute("phoneNumber");
        logPhoneNumber(context, phoneNumber);

        showOtpForm(context);
    }

    private void logPhoneNumber(AuthenticationFlowContext context, String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            logger.infof("Пользователь [%s] имеет номер телефона [%s]", context.getUser().getUsername(), phoneNumber);
        } else {
            logger.infof("Пользователь [%s] не имеет номера телефона", context.getUser().getUsername());
        }
    }

    private void showOtpForm(AuthenticationFlowContext context) {
        LoginFormsProvider form = context.form();
        context.challenge(form.createForm("custom-otp-form.ftl"));
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        verifyOtp(context);
    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    private void verifyOtp(AuthenticationFlowContext context) {
        String inputOtp = context.getHttpRequest().getDecodedFormParameters().getFirst("otp");
        if (TEMPORARY_OTP.equals(inputOtp)) {
            context.success();
        } else {
            context.form().setError("Неверный OTP");
            showOtpForm(context);
        }
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return user != null && user.getFirstAttribute("phoneNumber") != null;
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
    }

    @Override
    public void close() {
    }
}
