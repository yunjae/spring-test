<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
$(document).ready(function(){
});

var ajaxApp = angular.module("jsonGetApp", ['ngResource']);
ajaxApp.controller("jsonGetAppCtl", ["$resource","$scope","$log",function($resource,$scope,$log){ 
	 
   $scope.submitData = function () {
		   var dataObj = $('#bookSearchForm').serializeObject();

		   var msgs = $resource('/book/searchJson',{},{query:{method : "POST", isArray:true}});
		   
      msgs.query(dataObj, function(data) {
    	    $scope.bookList=data; 
			  }, function(error) {
				  alert(error);
			    });
        };
}]);

$.fn.serializeObject = function() {
	  var arrayData, objectData;
	  arrayData = this.serializeArray();
	  objectData = {};

	  $.each(arrayData, function() {
	    var value;

	    if (this.value != null) {
	      value = this.value;
	    } else {
	      value = '';
	    }

	    if (objectData[this.name] != null) {
	      if (!objectData[this.name].push) {
	        objectData[this.name] = [objectData[this.name]];
	      }

	      objectData[this.name].push(value);
	    } else {
	      objectData[this.name] = value;
	    }
	  });

	 return objectData;
};
</script>


<div ng-app="jsonGetApp">
<div ng-controller="jsonGetAppCtl">
<form method="POST" id="bookSearchForm" name="bookSearchForm" ng-submit="submitData()">
    <fieldset>
        <legend><spring:message code="book.searchcriteria"/></legend>
        <table>
            <tr>
                <td><label><spring:message code="book.title" /></label></td>                					
                <td><input type="text" id="title" name="title" ng-model="title"/></td>
            </tr>
            <tr>
                <td><label><spring:message code="book.category" /></label></td>
                <td>
                    <select id="category" name="category" ng-model="category">
                    <c:forEach items="${categories}" var="category">
                    		<option value="${category.id}">${category.name}</option> 	                                
                    </c:forEach>                    
                    </select>
                </td>
        		 </tr>                 
        </table>
    </fieldset>    
    <button type="submit">Submit</button>
    <table id="bookSearchResults">
        <tr>
            <th><spring:message code="book.title"/></th>
            <th><spring:message code="book.description"/></th>
            <th><spring:message code="book.price"/></th>
            <th></th>
        </tr>
        <tr data-ng-repeat="book in bookList">
           <td><a href="<c:url value="/book/detail/{{book.id}}"/>">{{book.title}}</a></td>
           <td>{{book.description}}</td>
           <td>{{book.price}}</td>
           <td>
              <a href='<c:url value="/cart/add/{{book.id}}"/>'><spring:message code="book.addtocart"/></a>
           </td>
        </tr>        			        
    </table>
</form>
</div>
</div>