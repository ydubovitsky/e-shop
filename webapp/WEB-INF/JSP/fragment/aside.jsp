<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="filter" tagdir="/WEB-INF/tags/" %>


<div class="d-block d-sm-none xs-option-container">
    <a class="pull-right" data-toggle="collapse" href="#productCatalog">Product Catalog<span
            class="caret"></span></a>
    <a class="pull-left" data-toggle="collapse" href="#findProducts">Find products<span class="caret"></span></a>
</div>
<!-- Search -->
<form class="search" action="/search">
    <div id="findProducts" class="panel panel-success collapse">
        <div class="panel-heading">Find products</div>
        <div class="panel-body">
            <div class="input-group">
                <input type="text" name="query" class="form-control" placeholder="Search for...">
                <span class="input-group-btn">
                  <a id="goSearch" class="btn btn-default" type="button">Go!</a>
                </span>
            </div>
            <div class="more-options">
                <a data-toggle="collapse" href="#searchOptions">More filters<span class="caret"></span></a>
            </div>
        </div>
        <div id="searchOptions" class="collapse">
            <filter:category-filter categories="${CATEGORY_LIST}"/>
            <filter:producer-filter producer="${PRODUCER_LIST}"/>
        </div>
    </div>
</form>
<!-- Search -->
<!-- Categories -->
<div id="productCatalog" class="panel panel-success collapse">
    <div class="panel-heading">Product Catalog</div>
    <div class="list-group">
        <c:forEach var="categories" items="${CATEGORY_LIST}">
        <a href="/products${categories.url}" class="list-group-item ${selectedCategoryUrl == categories.url ? 'active' : ''}">
            <span class="badge">${categories.name}</span></a>
        </c:forEach>
    </div>
</div>
<!-- Categories -->