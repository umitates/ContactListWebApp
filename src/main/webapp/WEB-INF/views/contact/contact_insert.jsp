<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>Yeni Kisi Ekleme</title>
</head>
<body>

	<h1>Rehbere Yeni Kisi Ekleme</h1>

	<form:form method="post" action="insert" commandName="new_contact">
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

</body>
</html>