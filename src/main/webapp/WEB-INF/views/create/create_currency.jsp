<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<h2 class="margin-bottom-10">
	<spring:message code="label.title.add" />
</h2>
<form:form action="${pageContext.request.contextPath}/currencies"
	modelAttribute="currencyForm" class="templatemo-login-form" enctype="multipart/form-data"
	method="post">

	<div class="row form-group">

		<div class="col-lg-6 col-md-6 form-group">
			<label for="name"> <spring:message code="label.name" />
			</label>
			<form:input path="name" class="form-control" id="name" />
			<form:errors path="name" cssClass="error" />
		</div>

		<div class="col-lg-6 col-md-6 form-group">
			<label for="unitName"><spring:message code="label.unitName" /></label>
			<form:input path="unitName" class="form-control" id="unitName" />
			<form:errors path="unitName" cssClass="error" />
		</div>

		<div class="col-lg-6 col-md-6 form-group">
			<label for="rateDefault"><spring:message
					code="label.rateDefault" /></label>
			<form:input path="rateDefault" class="form-control" id="rateDefault" />
			<form:errors path="rateDefault" cssClass="error" />
		</div>
		<div class="col-lg-6 col-md-6 form-group">
			<label for="icon"><spring:message code="label.icon" /></label>
			<form:input type="file" path="image"  id="image" />
		</div>
		<div class="col-lg-6 col-md-6 form-group">
			<spring:message code="label.create" var="labelSubmit"></spring:message>
			<input type="submit" value="${labelSubmit}" class="btn btn-primary" />
		</div>
	</div>
</form:form>