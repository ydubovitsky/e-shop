<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--Принимаем атрибуты из aside--%>
<%@attribute name="PRODUCERS_LIST" required="true" type="java.util.Collection" %>
<%@attribute name="searchForm" required="true" type="net.shop.form.SearchForm" %>

<div class="panel-heading">Producers filters</div>
<div class="panel-body producers">
    <label><input type="checkbox" id="allProducers"> All</label>
    <c:forEach var="p" items="${PRODUCERS_LIST}">
        <div class="form-group">
            <div class="checkbox">
                <label><input type="checkbox" name="producer" value="${p.id}" ${searchForm.producers.contains(p.id) ? 'checked' : ''} class="search-option">${p.name} (${p.count}) </label>
            </div>
        </div>
    </c:forEach>
</div>