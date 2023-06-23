package org.example.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* <p>
* 
* </p>
*
* @author lzy
* @since 2023-06-23
*/
@Getter
@Setter
@ApiModel(value = "Inventory对象", description = "")
public class Inventory implements Serializable {

private static final long serialVersionUID = 1L;

    private Integer bookId;

    private Integer quantity;
}