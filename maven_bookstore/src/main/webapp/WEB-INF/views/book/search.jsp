<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<script type="text/javascript">
   $('#bookSearchCriteria').submit(function(evt) {
	      alert("!!!!!!!!!!11");
	   evt.preventDefault();
	   formData = $('#bookSearchCriteria').serialize();
	   $.ajax({
	    	url: $('#bookSearchCriteria').action,
	    	type: 'GET',
	    	data: formData,
	    	success: function(html) {
	    		resultTable = $('#')
	    	}
	      });
   });
</script>

<form:form method="GET" modelAttribute="bookSearchCriteria">
    <fieldset>
        <legend>searchcriteria</legend>
        <table>
            <tr>
                <td><form:label path="title">title</form:label></td>
                <td><form:input path="title" /></td>
            </tr>
            <tr>
                <td><form:label path="category">category</form:label></td>
                <td>
                ##${category.id}###
                <form:select path="category" selected="true">
                    <form:options items="${categories}" itemValue="id" itemLabel="name" />
                </form:select>
                </td></tr>
        </table>
    </fieldset>
       <input type="submit" value="조회"/>
    <button id="search">search</button>
    <span id="bookSearchResult"/>
</form:form>

<c:if test="${not empty bookList}">
    <table>
        <tr>
            <th><spring:message code="book.title"/></th>
            <th><spring:message code="book.description"/></th>
            <th><spring:message code="book.price"/></th>
            <th></th>
        </tr>
        <c:forEach items="${bookList}" var="book">
            <tr>
                <td><a href="<c:url value="/book/detail/${book.id}"/>">${book.title}</a></td>
                <td>${book.description}</td>
                <td>${book.price}</td>
                <td>
                	<a href='<c:url value="/cart/add/${book.id}"/>'><spring:message code="book.addtocart"/></a>
                </td>	
            </tr>
        </c:forEach>
    </table>
</c:if>