<%@ page trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-bordered">
    <thead>
    <tr>
        <th>Product</th>
        <th>Price</th>
        <th>Count</th>
        <th class="hidden-print">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${items}">
        <tr id="product${item.product.id}" class="item">
            <td class="text-center"><img class="small" src="${item.product.imageLink}" alt="${item.product.name}"><br>${item.product.name}</td>
            <td class="price">$ ${item.product.price}</td>
            <td class="count">${item.count}</td>
            <td class="hidden-print">
                <c:choose>
                    <c:when test="${item.count > 1}">
                        <a class="btn btn-danger remove-product" data-id-product="${item.product.id}" data-count="1">Remove one</a>
                        <a class="btn btn-danger remove-product remove-all" data-id-product="${item.product.id}" data-count="${item.count}">Remove all</a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-danger remove-product" data-id-product="${item.product.id}" data-count="1">Remove one</a>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="2" class="text-right"><strong>Total:</strong></td>
        <td colspan="${showActionColumn ? 2 : 1}" class="total">$ ${totalCost}</td>
    </tr>
    </tbody>
</table>
