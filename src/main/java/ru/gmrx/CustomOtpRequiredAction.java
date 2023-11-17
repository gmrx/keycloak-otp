package ru.gmrx;

import org.jboss.logging.Logger;
import org.keycloak.authentication.RequiredActionContext;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.forms.login.LoginFormsProvider;

public class CustomOtpRequiredAction implements RequiredActionProvider {

    private static final Logger logger = Logger.getLogger(CustomOtpRequiredAction.class);
    public static final String PROVIDER_ID = "custom-otp-action";
    private static final String TEMPORARY_OTP = "123456";

    @Override
    public void evaluateTriggers(RequiredActionContext context) {
    }

    @Override
    public void requiredActionChallenge(RequiredActionContext context) {
        String phoneNumber = context.getUser().getFirstAttribute("phoneNumber");
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            logger.infof("Пользователь с ID [%s] имеет номер телефона [%s]", context.getUser().getId(), phoneNumber);
        } else {
            logger.infof("Пользователь с ID [%s] не имеет номера телефона", context.getUser().getId());
        }
        LoginFormsProvider form = context.form();
        context.challenge(form.createForm("custom-otp-form.ftl"));
    }

    @Override
    public void processAction(RequiredActionContext context) {
        String inputOtp = context.getHttpRequest().getDecodedFormParameters().getFirst("otp");
        if (TEMPORARY_OTP.equals(inputOtp)) {
            context.success();
        } else {
            LoginFormsProvider form = context.form();
            form.setError("Неверный Action OTP");
            context.challenge(form.createForm("custom-otp-form.ftl"));
        }
    }

    @Override
    public void close() {
    }
}
