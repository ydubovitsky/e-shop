<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="CATEGORY_LIST" required="true" type="java.util.Collection" %>

<div class="panel-heading">Category filters</div>
<div class="panel-body categories">
    <label><input type="checkbox" id="allCategories"> All</label>
    <c:forEach var="c" items="${CATEGORY_LIST}">
        <div class="form-group">
            <div class="checkbox">
                <label><input type="checkbox" name="category" value="1" class="search-option">${c.name} (${c.productCount})</label>
            </div>
        </div>
    </c:forEach>
</div>