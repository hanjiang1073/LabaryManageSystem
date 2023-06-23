package com.example.springboot.service.impl;

import com.example.springboot.entity.Reservation;
import com.example.springboot.mapper.ReservationMapper;
import com.example.springboot.service.IReservationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zqy
 * @since 2023-06-23
 */
@Service
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation> implements IReservationService {

}
