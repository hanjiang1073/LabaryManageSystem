package org.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Reservation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zqy
 * @since 2023-06-23
 */
@Mapper
public interface ReservationMapper extends BaseMapper<Reservation> {

}
