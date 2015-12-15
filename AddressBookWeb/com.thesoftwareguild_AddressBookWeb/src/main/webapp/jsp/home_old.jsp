<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Company Contacts</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Address Book</h1>
            <hr />
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/search">Search or Limit</a>

                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayAddressBook">All Addresses</a>
                    </li>
                </ul>
            </div>
        </div>

        



        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>