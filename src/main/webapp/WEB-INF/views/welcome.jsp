<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>Kisi Listesi</title>
</head>
<body>

	<h1>Kisi Listesi Uygulamasina Hos Geldiniz</h1>

	<form:form method="post" action="addContact" commandName="new_contact">
		<table>
			<tr>
				<td>Name:</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>Surname</td>
				<td><form:input path="surname" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>Telefon</td>
				<td><form:input path="phoneNumber" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Ekle" /></td>
			</tr>
		</table>
	</form:form>

	<c:if test="${!empty contacts}">
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
					<td>${contact.name}</td>
					<td>${contact.surname}</td>
					<td>${contact.email}</td>
					<td>${contact.phoneNumber}</td>
					<td><a href="deleteContact/${contact.id}">Sil</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>