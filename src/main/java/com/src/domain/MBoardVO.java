package com.src.domain;

import java.sql.Date;

public class MBoardVO {
	private int movie_id;
	private String m_title;
	private String m_content;
	private String m_writer;
	private Date m_date;
	private int m_hit;
	private String post_img;
	private String post_thumbimg;
	private int category_id;
	
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getPost_thumbimg() {
		return post_thumbimg;
	}
	public void setPost_thumbimg(String post_thumbimg) {
		this.post_thumbimg = post_thumbimg;
	}
	public String getPost_img() {
		return post_img;
	}
	public void setPost_img(String post_img) {
		this.post_img = post_img;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getM_title() {
		return m_title;
	}
	public void setM_title(String m_title) {
		this.m_title = m_title;
	}
	public String getM_content() {
		return m_content;
	}
	public void setM_content(String m_content) {
		this.m_content = m_content;
	}
	public String getM_writer() {
		return m_writer;
	}
	public void setM_writer(String m_writer) {
		this.m_writer = m_writer;
	}
	public Date getM_date() {
		return m_date;
	}
	public void setM_date(Date m_date) {
		this.m_date = m_date;
	}
	public int getM_hit() {
		return m_hit;
	}
	public void setM_hit(int m_hit) {
		this.m_hit = m_hit;
	}
	@Override
	public String toString() {
		return "MBoardVO [movie_id=" + movie_id + ", m_title=" + m_title + ", m_content=" + m_content + ", m_writer="
				+ m_writer + ", m_date=" + m_date + ", m_hit=" + m_hit + ", post_img=" + post_img + ", post_thumbimg="
				+ post_thumbimg + ", category_id=" + category_id + "]";
	}
}
