package com.gerny.core.vo;

import java.util.List;

public class PageResults<T> {
	
    private int draw;  //异步标识
  
    private int recordsTotal;  //即没有过滤的记录数（数据库里总共记录数）
  
    private int recordsFiltered; //过滤后的记录数
  
    private List<T> data;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}  
    
    
  
}