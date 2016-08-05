<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teste REST</title>
</head>
<body>
	<div align="center">
		<form action="consulta" method="post">
			<table>
				<tr>
					<td>Consulta</td>
				</tr>
				<tr>
					<td><input type="number" name="id"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Enviar">
				</tr>
			</table>
			<c:if test="${not empty lista }">
				<table border="">
					<c:forEach items="${lista }" var="pessoa">
						<tr>
							<td><c:out value="${pessoa.id }" /></td>
							<td><c:out value="${pessoa.nome }" /></td>
							<td><c:out value="${pessoa.sobrenome }" /></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</form>
		<form action="inclusao" method="post">
			<table>
				<tr>
					<td>Incluir</td>
				</tr>
				<tr>
					<td><input type="number" name="id"></td>
				</tr>
				<tr>
					<td><input type="text" name="nome"></td>
				</tr>
				<tr>
					<td><input type="text" name="sobrenome"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Enviar">
				</tr>
			</table>
		</form>
		<form action="atualizacao" method="post">
			<table>
				<tr>
					<td>Atualizar</td>
				</tr>
				<tr>
					<td><input type="number" name="id"></td>
				</tr>
				<tr>
					<td><input type="text" name="nome"></td>
				</tr>
				<tr>
					<td><input type="text" name="sobrenome"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Enviar">
				</tr>
			</table>
		</form>		
		<form action="exclusao" method="post">
			<table>
				<tr>
					<td>Excluir</td>
				</tr>
				<tr>
					<td><input type="number" name="id"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Enviar">
				</tr>
			</table>
		</form>		

	</div>
</body>
</html>