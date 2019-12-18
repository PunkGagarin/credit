<#include "../main-template.ftl"/>

<#macro content>
    <form action="/login/process" method="post">
        <div>
            Email: <input name="email" type="email">
        </div>

        <div>
            Password: <input name="password" type="password">
        </div>

        <input type="submit"/>
    </form>
    <#if error??>
        <b> Bad credentials</b>
    </#if>
</#macro>
<@main title="Login"/>
