package com.yss.pdm;

public class Column {

	private String id;
	private String name;
	private String fieldName;
	private String code;
	private String creationDate;
	private String modificationDate;
	private String type;
	private Integer length;
	private Boolean pkFlag;
	private String emptyValue; //是否可以为空
	private String pkgFieldName; //字段名称
	private String pkgCodeName; //主键code

	public String getPkgFieldName() {
		return pkgFieldName;
	}

	public void setPkgFieldName(String pkgFieldName) {
		this.pkgFieldName = pkgFieldName;
	}

	public String getPkgCodeName() {
		return pkgCodeName;
	}

	public void setPkgCodeName(String pkgCodeName) {
		this.pkgCodeName = pkgCodeName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getEmptyValue() {
		return emptyValue;
	}

	public void setEmptyValue(String emptyValue) {
		this.emptyValue = emptyValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Boolean getPkFlag() {
		return pkFlag;
	}

	public void setPkFlag(Boolean pkFlag) {
		this.pkFlag = pkFlag;
	}

}
