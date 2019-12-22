<html>
<head>
    <title>Credit Info</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <th>
            Month Payed
        </th>
        <th>
            Current Rate Sum
        </th>
        <th>
            Total Sum
        </th>
        <th>
            Sum Left
        </th>
    </tr>
    <tr>
        <td>
            ${creditInfo.monthPast}
        </td>
        <td>
            ${creditInfo.currentRateSum}
        </td>
        <td>
            ${creditInfo.totalSum}
        </td>
        <td>
            ${creditInfo.sumLeft}
        </td>
    </tr>
</table>

<div><h2>Pay plan</h2></div>
<#if payPlan?has_content>
    <table>
        <tr>
            <th>
                Month Number
            </th>
            <th>
                Current Rate Sum
            </th>
            <th>
                Current Sum Payed
            </th>
            <th>
                Sum Left
            </th>
        </tr>
        <#list payPlan as plan>
            <tr>
                <td>${plan.currentMonth}</td>
                <td>${plan.currentRateSum}</td>
                <td>${plan.currentSum}</td>
                <td>#{plan.sumLeft}</td>
            </tr>
        </#list>
    </table>
</#if>


</body>
</html>
