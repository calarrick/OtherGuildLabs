<%-- 
    Document   : home
    Created on : Oct 26, 2015, 11:30:54 AM
    Author     : apprentice
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--tag lib for c: tags-->
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
            <h1>Company Contacts</h1>
            <hr />
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/search">Search</a>

                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/stats">Stats</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displayContactListNoAjax">
                            Contact List (No Ajax)</a>

                    </li>
                </ul>
            </div>
        </div>
        <div class="container">
            <h1>Company Contacts</h1>
            <a href="displayNewContactFormNoAjax">Add a Contact</a><br />
            <hr />
            
            <c:forEach var="contact" items="${contactList}">
                
                <s:url value="deleteContactNoAjax" var="deleteContact_url">
                    <s:param name="contactId" value="${contact.contactId}" />
                </s:url>
                
                <s:url value="displayEditContactFormNoAjax" var="editContact_url">
                    <s:param name="contactId" value="${contact.contactId}" />
                </s:url>
                
                <c:if test="${contact.lastName == 'Doe'}"><!--this is silly example, just here to show using things like if
                             statements with EL in our jsp-->
                    CEO<br />
                </c:if>
                    
                Name: ${contact.firstName} ${contact.lastName} |
                <a href="${deleteContact_url}">Delete</a> |
                <a href="${editContact_url}">Edit</a><br />
                
                Company: ${contact.company}<br />
                Phone: ${contact.phone}<br />
                Email: ${contact.email}<br />
                <hr />
                
            </c:forEach>

        </div>                   



        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
