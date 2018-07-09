package com.ps.produce.support;

/**
 * @author lyqqq
 * @date 2017年10月24日 下午6:17:49 
 * @param <T>
 */
public class JsonObject<T>{
	     long iTotalRecords;

	    long iTotalDisplayRecords;

	    String sEcho;

	    String sColumns;

	    Iterable<T> aaData;

		public long getiTotalRecords() {
			return iTotalRecords;
		}

		public void setiTotalRecords(long iTotalRecords) {
			this.iTotalRecords = iTotalRecords;
		}

		public long getiTotalDisplayRecords() {
			return iTotalDisplayRecords;
		}

		public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
			this.iTotalDisplayRecords = iTotalDisplayRecords;
		}

		public String getsEcho() {
			return sEcho;
		}

		public void setsEcho(String sEcho) {
			this.sEcho = sEcho;
		}

		public String getsColumns() {
			return sColumns;
		}

		public void setsColumns(String sColumns) {
			this.sColumns = sColumns;
		}

		public Iterable<T> getAaData() {
			return aaData;
		}

		public void setAaData(Iterable<T> aaData) {
			this.aaData = aaData;
		}

		
		
		
}
