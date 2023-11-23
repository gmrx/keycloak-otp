<#import "template.ftl" as layout>
<@layout.registrationLayout displayMessage=!messagesPerField.existsError('username','password','code','phoneNumber') displayInfo=realm.password && realm.registrationAllowed && !registrationDisabled??; section>
    <#if section = "header">
        ${msg("loginAccountTitle")}
    <#elseif section = "form">
        <form id="kc-form-login" onsubmit="login.disabled = true; return true;" action="${url.loginAction}" method="post">
            <label>
                <input name="username" placeholder="login">
            </label>
            <label>
                <input name="password" type="password" placeholder="password">
            </label>
            <input type="submit">
        </form>
    <#elseif section = "info" >
    <#elseif section = "socialProviders" >
    </#if>
</@layout.registrationLayout>