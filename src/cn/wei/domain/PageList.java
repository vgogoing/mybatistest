package cn.wei.domain;

import java.util.List;

public class PageList {
	//首尾  上下页
	private Integer fristPage;
	private Integer lastPage;
	private Integer nextPage;
	private Integer prevPage;
	
	//总页 当前页  总条数   页条数
	private Integer totalPage;
	private Integer currentPage;
	private Integer totalCount;
	private Integer pageSize;
	
	//用户LIST
	private List<Employee> data;

	public PageList(Integer currentPage,Integer totalCount, Integer pageSize){
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.totalPage = (this.totalCount-1)/pageSize + 1;
		this.nextPage = this.totalPage == this.currentPage ? this.totalPage:this.currentPage+1;
		this.prevPage = this.currentPage -1 > 0 ? this.currentPage-1:1;
		this.fristPage = 1;
		this.lastPage = this.totalPage;
	}
	
	
	public Integer getFristPage() {
		this.fristPage = 1;
		return fristPage;
	}

	public void setFristPage(Integer fristPage) {
		this.fristPage = fristPage;
	}

	public Integer getLastPage() {
		this.lastPage = this.totalPage;
		return lastPage;
	}

	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}

	public Integer getNextPage() {
		this.nextPage = this.totalPage == this.currentPage ? this.totalPage:this.currentPage+1;
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getPrevPage() {
		this.prevPage = this.currentPage -1 > 0 ? this.currentPage-1:1;
		return prevPage;
	}

	public void setPrevPage(Integer prevPage) {
		this.prevPage = prevPage;
	}

	public Integer getTotalPage() {
		this.totalPage = (this.totalCount-1)/pageSize + 1;
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<Employee> getData() {
		return data;
	}

	public void setData(List<Employee> data) {
		this.data = data;
	}
	
	
}
