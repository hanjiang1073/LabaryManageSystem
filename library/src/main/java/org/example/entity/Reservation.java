package org.example.entity;

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
* @author zqy
* @since 2023-06-23
*/
@Getter
@Setter
@ApiModel(value = "Reservation对象", description = "")
public class Reservation implements Serializable {

private static final long serialVersionUID = 1L;

    private Integer reservationId;

    private Integer userId;

    private Integer bookId;

    private LocalDateTime reservationTime;

    private Integer status;
}