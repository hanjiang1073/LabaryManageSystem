package com.example.springboot.service.impl;

import com.example.springboot.entity.Queue;
import com.example.springboot.mapper.QueueMapper;
import com.example.springboot.service.IQueueService;
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
