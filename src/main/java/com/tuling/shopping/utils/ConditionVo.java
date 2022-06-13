package com.tuling.shopping.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 潘峰
 * @create 2022/5/14 6:56
 */
@Data
public class ConditionVo implements Serializable {
    private static final long serialVersionUID =-5099378457111419832L;
/**
 数据库字段名
 *
 */
private String column;
/**
 字段值
 *
 */
private String value;
/**
 连接类型，如
 *
 llike,equals,gt,ge,lt,le
 */
private String type;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
