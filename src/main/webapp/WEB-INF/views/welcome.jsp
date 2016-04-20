<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
    <title>Kisi Listesi</title>
</head>
<body>
 
<h1>Kisi Listesi Uygulamasina Hos Geldiniz</h1>

<c:if  test="${!empty contacts}">
<table class="data">
<tr>
	
    <th>Isim</th>
    <th>Soyisim</th>
    <th>E-Posta</th>
    <th>Telefon</th>    
    <th>Islem</th>
	
</tr>
<c:forEach items="${contacts}" var="contact">
    <tr>
   		
        <td>${contact.name} </td>
        <td>${contact.surname} </td>
        <td>${contact.email} </td>
        <td>${contact.phoneNumber} </td>
        
        <td><a href="deleteContact/${contact.id}">Sil</a></td>
    </tr>
</c:forEach>
</table>
</c:if>

</body>
</html>