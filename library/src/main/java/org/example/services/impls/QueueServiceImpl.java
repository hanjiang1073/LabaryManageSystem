package org.example.services.impls;

import org.example.entity.Queue;
import org.example.dao.QueueMapper;
import org.example.services.interfaces.IQueueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzy
 * @since 2023-06-23
 */
@Service
public class QueueServiceImpl extends ServiceImpl<QueueMapper, Queue> implements IQueueService {

}
