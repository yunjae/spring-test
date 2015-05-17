package com.apress.prospringmvc.bookstore.domain;

/**
 * Object to hold the search criteria to search books.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
public class BookSearchCriteriaJSON {

    private String title;
    private Long category;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getCategory() {
        return this.category;
    }

}
