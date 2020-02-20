<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="ishop" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="productList" data-page-count="${pageCount}" data-page-number="1">
	<div class="row">
	<jsp:include page="../fragment/product-list.jsp" />
	</div>
	<%--Если количество страниц с продуктами > 1 отображаем кнопку LoadMore--%>
	<c:if test="${pageCount > 1}">
	<div class="text-center hidden-print">
        <img id="loadMoreIndicator" src="/static/img/loading.gif" class="hidden" alt="Loading...">
        <a id="loadMore" class="btn btn-success">Load more products</a>
	</div>
	</c:if>
</div>
<ishop:add-product-popup />    