<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="PRODUCERS_LIST" required="true" type="java.util.Collection<net.shop.entity.impl.Category>" %>

<div class="panel-heading">Producers filters</div>
<div class="panel-body producers">
    <label><input type="checkbox" id="allProducers"> All</label>
    <c:forEach var="p" items="${PRODUCERS_LIST}">
        <div class="form-group">
            <div class="checkbox">
                <label><input type="checkbox" name="producer" value="1" class="search-option">${p.name} (${p.count}) </label>
            </div>
        </div>
    </c:forEach>
</div>