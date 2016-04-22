<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>Kisi Güncelleme</title>
</head>
<body>

	<h1>Kisi Iletisim Bilgisini Guncelleme</h1>

	<form:form method="post" action="submit" commandName="existing_contact">
		<form:hidden path="id" />
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
				<td colspan="2"><input type="submit" value="Güncelle" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>