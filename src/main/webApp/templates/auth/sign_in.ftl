<#include "../main-template.ftl"/>

<#macro content>
    <form action="/login/process" method="post">
        <div>
            Login: <input name="login" type="login">
        </div>

        <div>
            Password: <input name="password" type="password">
        </div>
        <table>
            <tr>
                <td>
                    <input type="submit"/>
                </td>
                <td>
                    <form action="/sign_up">
                        <input type="button" value="Sign Up">
                    </form>
                </td>
            </tr>
        </table>

    </form>
    <#if error??>
        <b> Wrong login or password </b>
    </#if>
</#macro>
<@main title="Login"/>
