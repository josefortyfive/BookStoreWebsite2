package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Review;

public class ReviewServices {

	private ReviewDAO reviewDAO;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.reviewDAO = new ReviewDAO();
		this.request = request;
		this.response = response;
	}
	
	
	public void listAllReviews() throws ServletException, IOException {
		listAllReviews(null);
	}
	
	public void listAllReviews(String message )throws ServletException, IOException  {
		List<Review> listReviews = reviewDAO.listAll();
		
		request.setAttribute("listReviews", listReviews);
		
		
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "review_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}



	public void editReview() throws ServletException, IOException   {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewId);
		
		request.setAttribute("review", review);
		
		String edittPage = "review_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(edittPage);
		dispatcher.forward(request, response);
	}



	public void updateReview() throws ServletException, IOException  {
		Integer reviewId = Integer.parseInt(request.getParameter("reviewId"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		Review review = reviewDAO.get(reviewId);
		review.setHeadline(headline);
		review.setComment(comment);
		
		reviewDAO.update(review);

		
		String message = "The review has been updated successfully";
		listAllReviews(message);
		
	}
	
}
