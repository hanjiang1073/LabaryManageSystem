package org.example.services.impls;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.ReservationMapper;
import org.example.entity.Reservation;
import org.example.services.interfaces.IReservationService;
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
