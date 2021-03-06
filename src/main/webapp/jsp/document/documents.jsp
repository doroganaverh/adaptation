<%--
  Created by IntelliJ IDEA.
  User: dyapparov
  Date: 01.07.2016
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Documents list</title>
    <link href="css/index.css" rel="stylesheet" type="text/css" >
</head>
<body>
    <div id="wrapper">
        <h1>Documents by <c:out value="${person.lastName}"/> <c:out value="${person.firstName}"/> <c:out value="${person.middleName}"/></h1>
        <c:set var="rowSwitch" value="0" scope="page"/>
        <table class="listTable">
            <tr class="tableHeader">
                <th>Document name</th>
                <th>RegNum</th>
                <th>RegDate</th>
                <th>Текст документа</th>
                <th>View</th>
            </tr>
            <c:forEach items="${requestScope.docs}" var="document">
                <c:set var="rowSwitch" value="${rowSwitch+1}"/>
                <tr class="<c:out value="row${rowSwitch mod 2}"/> selectable">
                    <td><c:out value = "${document.docName}"/></td>
                    <td><c:out value = "${document.registrationNumber}"/></td>
                    <td><fmt:formatDate value="${document.registerDate}" pattern="HH:mm dd.MM.yyyy"/></td>
                    <td><c:out value = "${document.text}"/></td>
                    <td class="detailsButton"><a href="<c:out value="document?id=${document.id}"/>" title="Details" ><img src="img/documents.png" alt="Details" width="32"/></a></td>
                </tr>
            </c:forEach>
        </table>
        <p><a href="persons"><img src="img/back.png" alt="Back" class="backButton"></a></p>
    </div>
</body>
</html>
