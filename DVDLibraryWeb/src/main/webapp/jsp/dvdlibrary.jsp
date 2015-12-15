<%-- 
    Document   : dvdlibrary
    Created on : Oct 27, 2015, 9:44:33 PM
    Author     : apprentice
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dvdlibrary.css">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <title>My DVD Library</title>
    </head>
    <body>


        <div class="container">
            <h1>My DVD Library</h1><hr>

            <nav class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" id="home" class="active">
                        <a id="home-opts">Home</a>
                    </li>
                    <li role="presentation" id="search">
                        <a id="search-opts">Search</a>
                    </li>

                    <li role="presentation" id="stats">
                        <a id="stats-opts">Stats</a>
                    </li>
                </ul>
            </nav>

            <div class="row">
                
                

                <div id="stats-panel" class="col-md-8 col-sm-8 col-xs-12">
                    
                    <h3 id="oldest-header" class="stat-header"></h3>
                    <ul id="old-movie-list" class="movie-list"></ul>
                    <h3 id="newest-header" class="stat-header"></h3>
                    <ul id="new-movie-list" class="movie-list"></ul>
                    <h3 id="average-header" class="stat-header"></h3>
                    <p id="average-age"></p>
                    

                </div>


                <div id="search-panel" class="col-md-8 col-sm-8 col-xs-12">

                    <p>Enter content into the fields you want to match.<br>
                        e.g. to search only by director, leave the other
                        fields empty.</p>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="search-title" class="col-md-2 control-label">Title: </label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="search-title" placeholder="title" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="search-director" class="col-md-2 control-label">Director: </label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="search-director" placeholder="director" />

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="search-studio" class="col-md-2 control-label">Studio: </label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="search-studio" placeholder="studio" />

                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-2">
                                <button type="submit" id="search-button" class="btn btn-default">Search Now</button>

                            </div>
                    </form>

                </div>



            </div>
        </div>

        <div class="row">

            <div class="col-md-8 col-sm-8 col-xs-12">
                <p>
                    <a id="add-movie">Add a Dvd?</a>
                </p>
                
                <div id="add-panel">
                    <form class="form-horizontal" role="form" id="add-dvd">
                        <div class="form-group">
                            <label for="add-title" class="col-md-2 control-label">Title: </label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="add-title" placeholder="Title" />
                                <div id="title-error" class="form-error" style="color:red"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-director" class="col-md-2 control-label">Director: </label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="add-director" placeholder="Director" />
                                <div id="director-error" class="form-error" style="color:red"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-studio" class="col-md-2 control-label">Studio: </label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="add-studio" placeholder="Studio" />
                                <div id="studio-error" class="form-error" style="color:red"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-rating" class="col-md-2 control-label">MPAA Rating: </label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="add-rating" placeholder="Rating" />
                                <div id="rating-error" class="form-error" style="color:red"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-date" class="col-md-2 control-label">Release Date: </label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="add-year" placeholder="Year ####" />
                                <div id="date-error" class="form-error" style="color:red"></div>
                            </div>
                        
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="add-month" placeholder="Month Name" />
                                
                            </div>
                            
                        
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="add-day" placeholder="Day of Month" />
                                
                            </div>
                            
                        </div>
                        
                        <textarea name="comment" id="add-note" form="add-dvd" class="col-md-12 col-xs-12">Enter your own notes here&hellip;</textarea>
                        <div class="form-group">
                            <div class="col-md-2">
                                <button type="submit" id="add-button" class="btn btn-default">Add Dvd</button>
                             
                        </div>
                        </div>
                    </form>
                    
                    
                    
                </div>
                
                

                <h2>All Movies</h2>
                <div id="sort_select">
                    Sort by: <a id="by-title">Title</a> | <a id="by-date">Date</a>
                </div>
                <ul id="main-movie-list" class="movie-list"></ul>
            </div>













        </div>










    </div>





    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script> 
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/dvdlibrary.js"></script>
</body>
</html>
