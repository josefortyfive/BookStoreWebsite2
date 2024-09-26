package com.bookstore.entity;
// Generated 22 May 2024, 6:03:07 pm by Hibernate Tools 5.4.33.Final

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Column;


@Entity
@Table(name = "order_detail", catalog = "bookstoredb")
@NamedQueries({
	@NamedQuery(name = "OrderDetail.bestSelling", 
			query = "SELECT od.book FROM OrderDetail od GROUP by od.book.bookId "
					+ "ORDER BY SUM(od.quantity) DESC"),
	@NamedQuery(name = "OrderDetail.countByBook",
				query = "SELECT COUNT(*) FROM OrderDetail od WHERE od.book.bookId =:bookId")
	
})
public class OrderDetail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private OrderDetailId id = new OrderDetailId();
	private Book book;
	private BookOrder bookOrder;
	private int quantity;
	private float subtotal;	

	public OrderDetail() {
	}

	public OrderDetail(OrderDetailId id) {
		this.id = id;
	}

	public OrderDetail(OrderDetailId id, Book book, BookOrder bookOrder, int quantity, float subtotal) {
		this.id = id;
		this.book = book;
		this.bookOrder = bookOrder;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "orderId", column = @Column(name = "order_id", nullable = false)),
			@AttributeOverride(name = "bookId", column = @Column(name = "book_id", nullable = false))})
	public OrderDetailId getId() {
		return this.id;
	}

	public void setId(OrderDetailId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id", insertable = false, updatable = false, nullable = false)
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
		this.id.setBook(book);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false)
	public BookOrder getBookOrder() {
		return this.bookOrder;
	}

	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
		this.id.setBookOrder(bookOrder);
	}
	
	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "subtotal", nullable = false, precision = 12, scale = 0)
	public float getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}	

}

