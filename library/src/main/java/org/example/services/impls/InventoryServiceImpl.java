package org.example.services.impls;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.services.interfaces.IInventoryService;
import org.example.entity.Inventory;
import org.example.dao.InventoryMapper;
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
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements IInventoryService {

}
