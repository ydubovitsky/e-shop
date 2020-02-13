<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="producer" required="true" type="java.util.Collection" %>

<div class="panel-heading">Producers filter</div>
<div class="panel-body producers">
    <label><input type="checkbox" id="allProducers">All</label>
    <c:forEach var="producer" items="${producer}">
    <div class="form-group">
        <div class="checkbox">
            <label><input type="checkbox" name="producers" value="1" class="search-option">${producer.name} (${producer.productCount})</label>
        </div>
    </div>
    </c:forEach>
</div>