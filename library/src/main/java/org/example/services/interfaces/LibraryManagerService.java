package org.example.services.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.LibraryManager;

import java.util.List;

/**
 * @author jianghan
 */
public interface LibraryManagerService extends IService<LibraryManager> {

    List<LibraryManager> getLibraryInfo();


    List<LibraryManager> getTime();

    List<LibraryManager> getBooksNum();



}
