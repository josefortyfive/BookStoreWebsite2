package com.bookstore.entity;
// Generated 22 May 2024, 6:03:07 pm by Hibernate Tools 5.4.33.Final

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;


@Entity
@Table(name = "review", catalog ="bookstoredb")
@NamedQueries({
	@NamedQuery(name = "Review.listAll", query = "SELECT r FROM Review r ORDER BY r.reviewTime DESC"),
	@NamedQuery(name = "Review.countAll", query = "SELECT COUNT (r) FROM Review r"),
	@NamedQuery(name = "Review.findByCustomerAndBook", 
					query = "SELECT r FROM Review r WHERE r.customer.customerId =:customerId"
							+ " AND r.book.bookId =:bookId")
})
public class Review implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer reviewId;
	private Book book;
	private Customer customer;
	private int rating;
	private String headline;
	private String comment;
	private Date reviewTime;

	public Review() {
	}

	public Review(Book book, Customer customer, int rating, String headline, String comment, Date reviewTime) {
		this.book = book;
		this.customer = customer;
		this.rating = rating;
		this.headline = headline;
		this.comment = comment;
		this.reviewTime = reviewTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "review_id", unique = true, nullable = false)
	public Integer getReviewId() {
		return this.reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id", nullable = false)
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Column(name = "rating", nullable = false)
	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Column(name = "headline", nullable = false, length = 128)
	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	@Column(name = "comment", nullable = false, length = 500)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "review_time", nullable = false, length = 19)
	public Date getReviewTime() {
		return this.reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}
	
	@Transient
	public String getStars() {
		String result = "";
		
		int numberOfStarOn = (int) rating;
		
		for(int i = 1; i <= numberOfStarOn; i++) {
			result += "on,";
		}
		
		for(int j = numberOfStarOn + 1; j <= 5; j++) {
			result += "off,";
		}
		
		return result.substring(0, result.length() - 1);
	}

}