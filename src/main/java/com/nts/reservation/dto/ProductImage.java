package com.nts.reservation.dto;

/**
 * 상품 이미지 모델
 * @author 황성욱
 * @since 2019-07-30
 */
public class ProductImage {
	private int productId; // 상품 id
	private int productImageId; // 상품 이미지 id
	private String type; // 이미지 타입(main, thumbnail, etc) [ma, th, et]
	private int fileInfoId; // 파일 id
	private String fileName; // 파일 이름
	private String saveFileName; // 파일 저장 위치 이름
	private String contentType; // 파일 확장자
	private boolean deleteFlag; //삭제 여부
	private String createDate; // 생성일 ($date-time)
	private String modifyDate; // 수정일 ($date-time)

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(int productImageId) {
		this.productImageId = productImageId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFileInfoId() {
		return fileInfoId;
	}

	public void setFileInfoId(int fileInfoId) {
		this.fileInfoId = fileInfoId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ProductImage [productId=" + productId + ", productImageId=" + productImageId + ", type=" + type
			+ ", fileInfoId=" + fileInfoId + ", fileName=" + fileName + ", saveFileName=" + saveFileName
			+ ", contentType=" + contentType + ", deleteFlag=" + deleteFlag + ", createDate=" + createDate
			+ ", modifyDate=" + modifyDate + "]";
	}
}
