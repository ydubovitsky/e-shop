<%@ page trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h4 class="text-center">My Orders</h4>
<hr />
<table id="myOrders" class="table table-bordered" data-page-number="1" data-page-count="${pageCount}">
    <thread>
        <tr>
            <th>Order id</th>
            <th>Date</th>
        </tr>
    </thread>
    <tbody>
    <c:if test="${empty orders}">
        <tr>
            <td colspan="2" class="text-center">No orders found!</td>
        </tr>
    </c:if>
    <jsp:include page="../fragment/my-orders-tbody.jsp"/>
    </tbody>
</table>
<div class="text-center hidden-print">
    <c:if test="${pageCount > 1}">
    <a id="loadMoreMyOrders" class="btn btn-success">Load more orders</a>
    </c:if>
</div>
