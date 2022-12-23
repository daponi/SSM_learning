package com.atguigu.www.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_book
 */
@Data
public class Book implements Serializable {
    /**
     * 主键
     */
    private Integer book_id;

    /**
     * 图书名称
     */
    private String book_name;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 库存（无符号）
     */
    private Object stock;

    private static final long serialVersionUID = 1L;
}