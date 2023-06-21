package org.example.services.impls;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.LibraryManagerMapper;
import org.example.entity.LibraryManager;
import org.example.services.interfaces.LibraryManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jianghan
 */
@Service
public class LibraryManagerServiceImpl extends ServiceImpl<LibraryManagerMapper, LibraryManager>  implements LibraryManagerService {

    @Resource
    LibraryManagerMapper libraryManagerMapper;

    @Override
    public List<LibraryManager> getLibraryInfo() {

        QueryWrapper<LibraryManager> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("content","duration","booksnum");
        List<LibraryManager> result = libraryManagerMapper.selectList(queryWrapper);

        return result;
    }

    @Override
    public List<LibraryManager> getTime() {
        QueryWrapper<LibraryManager> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("content","duration","opentime","closetime");
        List<LibraryManager> result = libraryManagerMapper.selectList(queryWrapper);
        return result;
    }

    @Override
    public List<LibraryManager> getBooksNum() {
        QueryWrapper<LibraryManager> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("content","booksnum");
        List<LibraryManager> result = libraryManagerMapper.selectList(queryWrapper);

        return result;
    }


}
