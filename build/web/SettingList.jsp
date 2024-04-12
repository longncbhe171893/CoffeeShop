<%-- 
    Document   : SettingList
    Created on : Apr 12, 2024, 8:23:13 AM
    Author     : HP
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@page import="DAO.*" %>
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
            
        </tr>
        <c:forEach var="setting" items="${settings}">
            <tr>
                <td>${setting.settingId}</td>
                <td>${setting.settingName}</td>
                <td>${setting.description}</td>
                <td>${setting.type}</td>
                
            </tr>
        </c:forEach>
    </table>
</body>
</html>
