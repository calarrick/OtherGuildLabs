<%-- 
    Document   : search
    Created on : Oct 29, 2015, 2:30:23 PM
    Author     : calarrick
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
            <h1>Address Book</h1>
            <hr />
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/home">All Addresses</a>
                    </li>
                    <li role="presentation" class="active" >
                        <a href="${pageContext.request.contextPath}/search">Search or Limit</a>

                    </li>

                </ul>
            </div>
        </div>


        <div class="container">
            <div class="row">
                <div class="col-md-6">

                    <h2>Address List</h2>

                    <ul id="addressList"></ul>

                </div>  


                <div class="col-md-6">
                    <h2>Search or Limit</h2>
                    <p>Enter content into the fields you want to match. e.g. to search only by last name, leave the other
                        fields empty.</p>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="add-last-name" class="col-md-4 control-label">Last Name: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="search-last-name" placeholder="Last Name" />
                                <div id="lastName" class="form-error" style="color:red"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-city" class="col-md-4 control-label">City: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="search-city" placeholder="city" />
                                <div id="city" class="form-error" style="color:red"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-state" class="col-md-4 control-label">State: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="search-state" placeholder="state" />
                                <div id="state" class="form-error" style="color:red"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-zip" class="col-md-4 control-label">Zip Code: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="search-zip" placeholder="zip" />
                                <div id="zip" class="form-error" style="color:red"></div>
                            </div>
                        </div>
                       <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit" id="search-button" class="btn btn-default">Search Now</button>

                            </div>
                    </form>
                </div>
            </div>             




            <!--
                    <div class="container">
            
                        
                        <div class="row">
                        <div class="col-md-6">
                        <h2>Find Addresses</h2>
                        <a href="displayNewAddressForm">Add an Address</a><br />
                        
                        </div>
            
                        <div class="col-md-6">    
                        <form role="form" action="searchAddress" method="POST">
                            
                            <div class="row">
                            <div class="form-group">
                                <label for="search-facet" class="control-label">Search By: </label><br />
                                <select id="search-facet" class="col-md-3">
                                        <option value="LAST_NAME" label="LastName">Last Name</option>
                                        <option value="CITY" label="city">City</option>
                                        <option value="STATE" label="state">State</option>
                                        <option value="ZIP_CODE" label="zip code">Zip Code</option>
                                    </select><br /></div>
                            </div>
                    </div>
                            
                            <div class="form-group">
                            
                            <div for="search-entry" class="control-label">Search: </div>
                            <input type="text" class="col-md-3" id="search-entry" name="query" placeholder="My Search" />
                            </div>
                                
                            <div class="form-group row">
                                
                                    <button type="submit" id="add-button" class="btn-default">Submit</button>
                                </div>
                        </div>
                              
                        </form>
            
            <hr /> -->



            <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script> 
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/addressBook.js"></script>
    </body>
</html>