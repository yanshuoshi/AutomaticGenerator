package com.yss.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * sql转java类型
 *
 * @author sonny
 */
public class SqlType2JavaType implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(SqlType2JavaType.class);

	public static String sqlType2JavaType(String sqlType) throws Exception {

		if (sqlType.equalsIgnoreCase("char")) {
            return "String";
        }
		if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        }
		if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Boolean";
        }
		if (sqlType.equalsIgnoreCase("smallint")) {
            return "Short";
        }

		if (sqlType.equalsIgnoreCase("mediumint")) {
            return "Short";
        }

		//无符号
		//smallint
		if (sqlType.equalsIgnoreCase("smallint unsigned")) {
            return "Short";
        }

		if (sqlType.equalsIgnoreCase("mediumint unsigned")) {
            return "Short";
        }

		if ((sqlType.equalsIgnoreCase("integer")) || (sqlType.equalsIgnoreCase("int unsigned"))) {
			return "Integer";
		}
		if (sqlType.equalsIgnoreCase("int") || sqlType.startsWith("int")) {
            return "Integer";
        }

		if (sqlType.equalsIgnoreCase("bigint unsigned")) {
            return "Long";
        }

		if ((sqlType.equalsIgnoreCase("integer")) || (sqlType.equalsIgnoreCase("int") || (sqlType.equalsIgnoreCase("tinyint unsigned")))) {
			return "Integer";
		}
		if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        }
		if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        }
		if ((sqlType.equalsIgnoreCase("double")) || (sqlType.equalsIgnoreCase("decimal unsigned")) || (sqlType.equalsIgnoreCase("decimal")) || (sqlType.equalsIgnoreCase("numeric")) || (sqlType.equalsIgnoreCase("real")) || (sqlType.equalsIgnoreCase("money")) || (sqlType.equalsIgnoreCase("smallmoney"))) {
            return "Double";
        }
		if ((sqlType.equalsIgnoreCase("longtext")) || (sqlType.equalsIgnoreCase("varchar")) || (sqlType.equalsIgnoreCase("char")) || (sqlType.equalsIgnoreCase("nvarchar")) || (sqlType.equalsIgnoreCase("nchar")) || (sqlType.equalsIgnoreCase("text"))) {
            return "String";
        }

		if (sqlType.equalsIgnoreCase("datetime")) {
            return "Date";
        }
		if (sqlType.equalsIgnoreCase("timestamp")) {
            return "Date";
        }
		if (sqlType.equalsIgnoreCase("image")) {
			return "Blod";
		}

		sqlType = sqlType.toLowerCase();

		if (sqlType.startsWith("date")) {
			return "Date";
		}

		if (sqlType.startsWith("long")) {
			return "Long";
		}

		//StringUtils.contains(sqlType,"char")
		if (sqlType.startsWith("Integer")) {
			return "Integer";
		}

		if (sqlType.startsWith("varchar")) {
			return "String";
		}

		if (sqlType.startsWith("bool") || sqlType.startsWith("Boolean")) {
			return "Boolean";
		}
		if (sqlType.startsWith("char")||sqlType.startsWith("mediumblob")) {
			return "String ";
		}

		if (sqlType.startsWith("decimal")) {
            return "Double";
        }

		if (StringUtils.contains(sqlType, "time")) {
            return "Date";
        }

		if (StringUtils.contains(sqlType, "String") || sqlType.startsWith("string")) {
            return "String";
        }

		throw new Exception("转换错误" + sqlType);

	}
}