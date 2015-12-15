<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Address Book</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addressbook.css">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Address Book</h1>
            <hr />
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/home">All Addresses</a>
                    </li>
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/search">Search or Limit</a>

                    </li>

                </ul>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-6">

                    <h2>Address List</h2>
                    
                    
                    <ul id="addressList" class="address-list"></ul>
                </div>  

                <div class="col-md-6">
                    <h2>Add New Address</h2>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="add-last-name" class="col-md-4 control-label">Last Name: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-last-name" placeholder="Last Name" />
                                <div id="lastName" class="form-error"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-first-name" class="col-md-4 control-label">First Names: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-first-name" placeholder="First Names" />
                                <div id="firstName" class="form-error"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-street-num" class="col-md-4 control-label">Street Number: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-street-num" placeholder="123" />
                                <div id="streetNum" class="form-error"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-street" class="col-md-4 control-label">Street Name: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-street" placeholder="Sample Street" />
                                <div id="street" class="form-error"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-apt" class="col-md-4 control-label">Apt. Num: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-apt" placeholder="apt z" />
                                <div id="apt" class="form-error"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-city" class="col-md-4 control-label">City: </label>

                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-city" placeholder="city" />
                                <div id="city" class="form-error"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-state" class="col-md-4 control-label">State: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-state" placeholder="State" />
                                <div id="state" class="form-error"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-zip" class="col-md-4 control-label">Zip Code: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-zip" placeholder="zip" />
                                <div id="zip" class="form-error"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-phone" class="col-md-4 control-label">Phone Numbers: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-phone" placeholder="phone numbers" />
                                <div id="phone" class="form-error"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-email" class="col-md-4 control-label">Email Addresses: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-email" placeholder="email addresses" />
                                <div id="email" class="form-error"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit" id="add-button" class="btn btn-default">Add Address</button>

                            </div>
                    </form>
                </div>
            </div>      
            </div>
        </div>

            <!-- Edit Modal -->
            <div class="modal fade" id="edit-pane" tabindex="-1" role="dialog"
                 aria-labelledby="editModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                                <span class="sr-only">Close</span>
                            </button>
                            <h4 class="modal-title" id="detailsModalLabel">Edit Address</h4>
                        </div>
                        <div class="modal-body">
                            <h3 id="address-id"></h3>
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label for="edit-last-name" class="col-md-4 control-label">Last Name:</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-last-name" placeholder="Last Name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="edit-first-names" class="col-md-4 control-label">First Names:</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-first-name" placeholder="Separate by comma">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="edit-street-num" class="col-md-4 control-label">Street Number: </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-street-num" placeholder="123" />
                                        <div id="streetNum" class="form-error" style="color:red"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="edit-street" class="col-md-4 control-label">Street Name: </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-street-name" placeholder="Sample Street" />
                                        <div id="street" class="form-error" style="color:red"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="edit-apt" class="col-md-4 control-label">Apt. Num: </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-apt-num" placeholder="apt z" />
                                        <div id="apt" class="form-error" style="color:red"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="edit-city" class="col-md-4 control-label">City: </label>

                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-city" placeholder="city" />
                                        <div id="city" class="form-error" style="color:red"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="edit-state" class="col-md-4 control-label">State: </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-state" placeholder="State" />
                                        <div id="state" class="form-error" style="color:red"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="edit-zip" class="col-md-4 control-label">Zip Code: </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-zip-code" placeholder="zip" />
                                        <div id="zip" class="form-error" style="color:red"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="edit-phone" class="col-md-4 control-label">Phone Numbers: </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-phone" placeholder="phone numbers" />
                                        <div id="phone" class="form-error" style="color:red"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="edit-email" class="col-md-4 control-label">Email Addresses: </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-email" placeholder="email addresses" />
                                        <div id="email" class="form-error" style="color:red"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <button type="submit" data="address_id" id="edit-button" class="btn btn-default" data-dismiss="modal">
                                            Edit Address
                                        </button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                        <input type="hidden" id="edit-address-id">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Edit Modal -- END -->

            <!-- Delete Modal -->
            <div class="modal fade" id="delete-pane" tabindex="-1" role="dialog"
                 aria-labelledby="deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                                <span class="sr-only">Close</span>
                            </button>
                            <h4 class="modal-title" id="deleteModalLabel">Delete Address</h4>
                        </div>
                        <div class="modal-body">
                            <button type ="button" class="btn" id="delete_btn" data="address_id">Delete Now?</button>
                            <div id="delete_ask" class="delete_warning" style="color:red"></div>
                            <h3 id="address-id"></h3>
                            
                                <div class="address-list address-list-modal">
                                    <span id="address-last-name"></span><br>
                                    <span id="address-first-names"></span><br>
                                    <span id="address-line-one"></span><br>
                                    <span id="address-line-two"></span><br>
                                    <span id="phone-numbers-"></span><br>
                                    <span id="email-addresses"></span><br>
                                    
                                </div>
                                
                                </div>
                           </div>
                    </div>
                </div>
            

            <!-- Edit Modal -- END -->
            
            
            
            <div class="modal fade" id="details-pane" tabindex="-1" role="dialog"
                 aria-labelledby="detailsModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                                <span class="sr-only">Close</span>
                            </button>
                            <h4 class="modal-title" id="detailsModalLabel">Address Details</h4>
                        </div>
                        <div class="modal-body">
                            
                            <h3 id="address-id"></h3>
                            
                                <div class="address-list address-list-modal">
                                    <div class="names">
                                    <span id="address-last-name-det"></span><br>
                                    <span id="address-first-names-det"></span>
                                    </div>
                                    <div class="mail-address">
                                    <span id="address-line-one-det"></span><br>
                                    <span id="address-line-two-det"></span><br>
                                    </div>
                                    <div class="phone-email">
                                    <span id="phone-numbers-det"></span><br><br>
                                    <span id="email-addresses-det"></span>
                                    </div>
                                    
                                </div>
                                
                                </div>
                           </div>
                    </div>
                </div>
            
            <!--end model-->
            
           

                        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script> 
                        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
                        <script src="${pageContext.request.contextPath}/js/addressBook.js"></script>
                        </body>
                        </html>