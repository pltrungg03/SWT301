<%-- 
    Document   : managerProduct
    Created on : Nov 1, 2023, 6:03:09 PM
    Author     : lactr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>History <b>Order</b></h2>
                        </div>

                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>

                            <th>OrderID</th>
                            <th>UserName</th>
                            <th>TotalMoney</th>
                            <th>Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listO}" var="o">
                            <tr>
                                <td>${o.id}</td>
                                <td>${o.c.user}</td>
                                <td>${o.totalmoney}K</td>
                                <td>${o.date}</td>
                                <td><button class="btn btn-sm btn-primary" onclick="viewDetail(${o.id},${sessionScope.acc.id})">
                                        View Detail
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>


            </div>
            <div class="col-12 pb-1 ">
                <nav aria-label="Page navigation">
                    <ul class="listPage">

                    </ul>
                </nav>
            </div>
            <table class="table table-bordered text-center mb-0">
                <thead class="bg-secondary text-dark list">
                    <tr>
                        
                        <th>Image</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody class="align-middle " id="orderDetail">

                </tbody>   

            </table>
            <a href="home"><button type="button" class="btn btn-primary">Back to home</button></a>

        </div>

        <!-- Edit Modal HTML -->
        <!-- Edit Modal HTML -->


    </a>
    <script src="js/manager.js" type="text/javascript"></script>
    <script>
                                    function viewDetail(oId, cId) {
                                        console.log(oId + "==" + cId);
                                        $.ajax({
                                            url: "/assign/view-detail-customer",
                                            type: "get",
                                            data: {
                                                oId: oId,
                                                cId: cId
                                            },
                                            success: function (data) {
                                                var out = document.getElementById("orderDetail");
                                                out.innerHTML = data;
                                                loadItem();
                                            },
                                            error: function (xhr) {
                                                //handle error
                                            }
                                        });
                                    }
    </script>
</body>
</html>