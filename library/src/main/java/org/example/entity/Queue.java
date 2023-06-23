package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@ApiModel(value = "Queue对象", description = "")
public class Queue implements Serializable {

private static final long serialVersionUID = 1L;

    @TableId(value = "queue_id", type = IdType.AUTO)
    private Integer queueId;

    private Integer userId;

    private Integer bookId;

    private LocalDateTime queueTime;

    private Integer status;
}