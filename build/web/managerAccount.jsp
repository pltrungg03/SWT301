
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
                            <h2>Manage <b>Account</b></h2>
                        </div>

                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>

                            <th>ID</th>
                            <th>User</th>
                            <th>Pass</th>
                            <th>Quyền bán hàng</th>
                            </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listacc}" var="o">
                            <tr>

                                <td>${o.id}</td>
                                <td>${o.user}</td>
                                <td>
                                    ${o.pass}
                                </td>
                                <td> 
                                    <form id="setSeller${o.id}" action="change-seller-status" method="post">
                                        <input type="hidden" name="id" value="${o.id}">
                                        <select name="isSeller" id="setPosition" onchange="setSeller(${o.id})">
                                            <option value="1" <c:if test="${o.isSell eq 1}">selected</c:if>>Seller</option>
                                            <option value="0" <c:if test="${o.isSell != 1}">selected</c:if>>Not a Seller</option>
                                            </select>
                                        </form></td>

                                </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
            <a href="home"><button type="button" class="btn btn-primary">Back to home</button></a>

        </div>

        <!-- Edit Modal HTML -->

        <!-- Edit Modal HTML -->

    </a>
    <script src="js/manager.js" type="text/javascript"></script>
    <script>
                                            function setSeller(id) {
                                                document.getElementById("setSeller" + id).submit();
                                            }
                                            $('.changeStatus').on('click', function () {
                                                $('#changeStatus').submit();
                                            });

                                            let thisPage = 1;
                                            let limit = 6;
                                            let list = document.querySelectorAll('.list .item');

    </script>
</body>
</html>