<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="eshop" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <c:forEach var="p" items="${products}">
        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
            <div id="product${p.id}" class="panel panel-default product">
                <div class="panel-body">
                    <div class="thumbnail">
                        <img src="${p.imageLink}" alt="${p.name}" class="img-thumbnail">
                        <div class="desc">
                            <div class="cell">
                                <p>
                                    <span class="title">Details</span>${p.description}
                                </p>
                            </div>
                        </div>
                    </div>
                    <h4 class="name">${p.name}</h4>
                    <hr>
                    <div class="price">$${p.price}</div>
                    <div class="row code-price">
                        <div class="code col-6">Serial code:${p.id}</div>
                        <a class="col-6 pull-right btn btn-primary buy-btn" data-id-product="${p.id}">Buy now</a>
                    </div>
                    <div class="list-group">
                    <span class="list-group-item"> <small>Category:</small> <span
                            class="category">${p.category}</span></span>
                        <span class="list-group-item"> <small>Producer:</small> <span class="producer">${p.producer}</span></span>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

