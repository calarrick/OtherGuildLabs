<%-- 
    Document   : editAddressForm
    Created on : Oct 27, 2015, 10:28:43 AM
    Author     : calarrick
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--tag lib for c: tags-->
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!--sf additional tags for managing forms -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Address Book</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Add Address</h1>
            <hr />
        </div>

        <div class="container">
            
            <a href="displayAddressBook">Address Book</a><br />
            <hr />
            <!-- sf extra tags ... -->
            <!-- model attribute to establish binding between this form and the contact variable in our model 'view bag'-->
            <sf:form class="form-horizontal" role="form" modelAttribute="address" action="editAddress" method="POST">
                <!--action and method together define the end point to which we'll send this-->
                <div class="form-group">
                    <label for="add-first-name" class="col-md-4 control-label">Last Name: </label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-last-name" path="lastName" placeholder="${address.lastName}" />
                        <sf:errors path="lastName" cssClass="error"></sf:errors>
                            
                        </div></div>
                    <div class="form-group">
                        <label for="add-first-name" class="col-md-4 control-label">First Names (separate by comma): </label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-first-name" path="firstNameBag" placeholder="${address.firstNameBag}" />
                        <sf:errors path="firstNameBag" cssClass="error"></sf:errors>
                        </div> </div>
                        
                        <div class="form-group">
                        <label for="add-state" class="col-md-4 control-label">Street Number: </label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-street-num" path="streetNumber" placeholder="${address.streetNumber}" />
                        <sf:errors path="streetNumber" cssClass="error"></sf:errors>
                        </div></div>
                        
                        <div class="form-group">
                        <label for="add-state" class="col-md-4 control-label">Street Name: </label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-street" path="streetName" placeholder="${address.streetName}" />
                        <sf:errors path="streetName" cssClass="error"></sf:errors>
                        </div></div>
                        
                    <div class="form-group">
                        <label for="add-city" class="col-md-4 control-label">City: </label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-city" path="cityName" placeholder="${address.cityName}" />
                        <sf:errors path="cityName" cssClass="error"></sf:errors>
                        </div></div>
                    
                    <div class="form-group">
                        <label for="add-state" class="col-md-4 control-label">State (2-letter postal abbr.): </label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-state" path="stateName" placeholder="${stateName}" />
                        <sf:errors path="stateName" cssClass="error"></sf:errors>
                        </div></div>
                        
                        <div class="form-group">
                        <label for="add-state" class="col-md-4 control-label">Zip Code: </label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-zip-code" path="zipCode" placeholder="${zipCode}" />
                        <sf:errors path="zipCode" cssClass="error"></sf:errors>
                        </div></div>
                                            
                        <div class="form-group">
                        <label for="add-phone" class="col-md-4 control-label">Phone Numbers (separate by comma): </label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-phone" path="phoneNumBag" placeholder="phone" />
                        <sf:errors path="phoneNumBag" cssClass="error"></sf:errors>
                        </div></div>
                        
                        <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit" id="add-button" class="btn-default">Add to Address Book</button>
                        </div></div>
                    

            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
