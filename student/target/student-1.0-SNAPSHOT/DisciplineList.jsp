<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
    <title>Discipline List</title>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="wrap">
    <section>
        <div class="container">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Name Discipline</th>
                </tr>
                </thead>
                <tbody>
<c:forEach items="${disciplines}" var="discipline">
    <tr>
        <td>
            <c:out value="${discipline.id}"/>
        </td>
        <td>
            <c:out value="${discipline.nameDiscipline}"/>
        </td>
        <td><a href="DisciplineController?action=delete&disciplineId=<c:out value="${discipline.id}"/> ">Delete</a></td>
        <td><a href="DisciplineController?action=edit&disciplineId=<c:out value="${discipline.id}"/>">Update</a></td>
        <td></td>
    </tr>
    </c:forEach>
                </tbody>
                </table>
            <a href="DisciplineController?action=insert" role="button" class="btn btn-info btn-lg" data-toggle="modal">Add new Discipline</a>
            <a href="DisciplineController?action=back" role="button" class="btn btn-info btn-lg" data-toggle="modal">Back</a>
            </div>
    </section>
</div>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</body>
</html>

