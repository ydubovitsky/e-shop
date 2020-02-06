<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="eshop" tagdir="/WEB-INF/tags" %>

<div id="productList">
    <%@include file="../fragment/product-list.jsp"%>
    <div class="text-center">
        <img id="loadMoreIndicator" src="static/img/loading.gif" class="d-none" alt="loading...">
        <a id="loadMore" class="btn btn-success">Load More</a>
    </div>
</div>
<eshop:add-product-popup/>
