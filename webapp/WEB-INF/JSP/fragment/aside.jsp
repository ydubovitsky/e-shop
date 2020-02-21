<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<div class="visible-xs-block xs-option-container">
	<a class="pull-right" data-toggle="collapse" href="#productCatalog">Product catalog <span class="caret"></span></a> 
	<a data-toggle="collapse" href="#findProducts">Find products <span class="caret"></span></a>
</div>
<!-- Search form -->
<form class="search" action="/search">
	<div id="findProducts" class="panel panel-success collapse">
		<div class="panel-heading">Find products</div>
		<div class="panel-body">
			<div class="input-group">
				<input type="text" name="query" class="form-control" placeholder="Search query" value="${searchForm.query}">
				<span class="input-group-btn"> 
					<a id="goSearch" class="btn btn-default">Go!</a>
				</span>
			</div>
			<div class="more-options">
				<a data-toggle="collapse" href="#searchOptions">More filters <span class="caret"></span></a>
			</div>
		</div>
		<div id="searchOptions" class="collapse">
            <tags:category-filter CATEGORY_LIST="${CATEGORY_LIST}"/>
            <tags:products-filter PRODUCERS_LIST="${PRODUCERS_LIST}"/>
		</div>
	</div>
</form>
<!-- /Search form -->
<!-- Categories -->
<div id="productCatalog" class="panel panel-success collapse">
	<div class="panel-heading">Product catalog</div>
	<div class="list-group">
		<c:forEach var="c" items="${CATEGORY_LIST}">
			<%--В цикле проходим по всем категория, если текущая url = selectedCategoryUrl(из сервлете) тогда добавляем класс active--%>
			<a href="/products${c.url}" class="list-group-item ${selectedCategoryUrl == c.url ? 'active' : ''}"> <span class="badge">${c.productCount}</span> ${c.name}</a>
		</c:forEach>
	</div>
</div>
<!-- /Categories -->