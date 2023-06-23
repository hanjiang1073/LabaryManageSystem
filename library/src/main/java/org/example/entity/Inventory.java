package com.example.springboot.entity;

import java.io.Serializable;
import cn.hutool.core.annotation.Alias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.springboot.common.LDTConfig;
import lombok.Getter;
import lombok.Setter;

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