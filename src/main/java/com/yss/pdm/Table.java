package com.yss.pdm;

import java.util.ArrayList;

public class Table {
	private String id;
	private String name;
	private String code;
	private String creationDate;
	private String modificationDate;
	private ArrayList<Column> columns;
	private String pkFieldCode;

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

	public ArrayList<Column> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<Column> columns) {
		this.columns = columns;
	}

	public String getPkFieldCode() {
		return pkFieldCode;
	}

	public void setPkFieldCode(String pkFieldCode) {
		this.pkFieldCode = pkFieldCode;
	}

	@Override
	public String toString() {
		return "Table{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", code='" + code + '\'' + ", creationDate='" + creationDate + '\'' + ", modificationDate='" + modificationDate + '\'' + ", columns=" + columns + ", pkFieldCode='" + pkFieldCode + '\'' + '}';
	}
}
