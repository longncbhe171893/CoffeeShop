<%-- 
    Document   : SettingList
    Created on : Apr 12, 2024, 8:23:13 AM
    Author     : HP
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Settings</title>
</head>
<body>
    <h1>Settings</h1>
    <table border="1">
        <tr>
            <th>Setting ID</th>
            <th>Setting Name</th>
            <th>Description</th>
            <th>Type</th>
            <th>Setting Sort</th>
        </tr>
        <c:forEach var="setting" items="${settings}">
            <tr>
                <td>${setting.settingId}</td>
                <td>${setting.settingName}</td>
                <td>${setting.description}</td>
                <td>${setting.type}</td>
                <td>${setting.settingSort}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
