package com.apress.prospringmvc.bookstore.web.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.BookSearchCriteria;
import com.apress.prospringmvc.bookstore.domain.BookSearchCriteriaJSON;
import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.service.BookstoreService;

@RestController
public class BookSearchRestController {
	@Autowired
	private BookstoreService bookstoreService;
	
	 @RequestMapping(value = "/book/searchJson")
	 public Collection<Book> listJSON(@RequestBody BookSearchCriteriaJSON criteriaJSON) {
		   BookSearchCriteria criteria = new BookSearchCriteria();
		   Category category = new Category();
		   category.setId(criteriaJSON.getCategory());
		   criteria.setCategory(category);
		   criteria.setTitle(criteriaJSON.getTitle());
	    return this.bookstoreService.findBooks(criteria);
	  }
}
