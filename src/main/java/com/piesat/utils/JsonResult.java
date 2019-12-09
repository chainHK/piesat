package com.piesat.utils;

import com.github.pagehelper.PageInfo;

public class JsonResult {

	private int code;
	private String message;
	private Object data;
//	private int totalPage;
//	private int pageNum;
//	private int pageSize;
//	private long rowCount;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

//	public int getTotalPage() {
//		return totalPage;
//	}
//
//	public void setTotalPage(int totalPage) {
//		this.totalPage = totalPage;
//	}
//
//	public int getPageNum() {
//		return pageNum;
//	}
//
//	public void setPageNum(int pageNum) {
//		this.pageNum = pageNum;
//	}
//
//	public int getPageSize() {
//		return pageSize;
//	}
//
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
//
//	public long getRowCount() {
//		return rowCount;
//	}
//
//	public void setRowCount(long rowCount) {
//		this.rowCount = rowCount;
//	}

	public static JsonResult createSuccessResponse(Object data) {
		JsonResult jor = new JsonResult();
		jor.code = 200;
		jor.message = "success";
		jor.data = data;
		return jor;
	}

	public static JsonResult createErrorResponse(int code, String message, Object data) {
		JsonResult jor = new JsonResult();
		jor.code = code;
		jor.message = message;
		jor.data = data;
		return jor;
	}

	public static JsonResult createResponse(int code, String message, Object data) {
		JsonResult jor = new JsonResult();
		jor.code = code;
		jor.message = message;
		jor.data = data;
		return jor;
	}

	public static JsonResult createJsonResponse(int code, String message, PageInfo<?> pageInfo) {
		JsonResult jor = new JsonResult();
		jor.code = code;
		jor.message = message;
		// 修改 Yhq
//		if (pageInfo != null) {
//			jor.data = pageInfo.getList();
//			jor.pageNum = pageInfo.getPageNum();
//			jor.pageSize = pageInfo.getPageSize();
//			jor.rowCount = pageInfo.getTotal();
//			jor.totalPage = pageInfo.getPages();
//		}
		return jor;
	}

	/**
	 * @Title: createSimpleResponse
	 * @author: WANGQI
	 * @Description:简单返回类型
	 * @param:
	 * @return:
	 */
	public static JsonResult createSimpleResponse(int code, String message) {
		JsonResult jor = new JsonResult();
		jor.code = code;
		jor.message = message;
		return jor;
	}

}
