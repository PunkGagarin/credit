<#include "../main-template.ftl"/>

<#macro content>
    <form action="/login?orderId=#{orderId!currentOrderId}" method="post">
        <div>
            Login: <input name="login" type="login">
        </div>

        <div>
            Password: <input name="password" type="password">
        </div>
        <table>
            <tr>
                <td>
                    <input type="submit" value="Log In"/>
                </td>
                <td>
                    <a href="/sign_up">Sign Up</a>
                </td>
            </tr>
        </table>


    </form>
    <#if error??>
        <b> Wrong login or password </b>
    </#if>
</#macro>
<@main title="Login"/>
