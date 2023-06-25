package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    @TableId("queue_id")
    private Integer queueId;

    private Integer userId;

    private Integer bookId;

    private LocalDateTime queueTime;

    private Integer status;
}