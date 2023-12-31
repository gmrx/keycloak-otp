<#import "template.ftl" as layout>
<@layout.registrationLayout displayMessage=!messagesPerField.existsError('username','password','code','phoneNumber') displayInfo=realm.password && realm.registrationAllowed && !registrationDisabled??; section>
    <#if section = "header">
        ${msg("loginAccountTitle")}
    <#elseif section = "form">
        <form id="kc-form-login" action="${url.loginAction}" method="post">
            <div class="form-group">
                <label for="otp">Введите OTP</label>
                <input type="text" id="otp" name="otp" class="form-control" />
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary btn-lg">Подтвердить</button>
            </div>
        </form>
    <#elseif section = "info" >
    <#elseif section = "socialProviders" >
    </#if>
</@layout.registrationLayout>