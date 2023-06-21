package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.common.Result;
import org.example.entity.LibraryManager;
import org.example.services.interfaces.LibraryManagerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jianghan
 */
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class LibraryManagerController {

    @Resource
    LibraryManagerService libraryManagerService;


    @GetMapping("/libaryinfo")
    Result getLibraryInfo(){

        List<LibraryManager> result = libraryManagerService.getLibraryInfo();



        List<Map<String,Object>> resultList = new ArrayList<>();

        for(int i=0; i<result.size();i++){
            Map<String, Object> temp = new HashMap<>();
            temp.put("content", result.get(i).getContent());
            temp.put("duration", result.get(i).getDuration());
            temp.put("booksnum", result.get(i).getBooksnum());
            resultList.add(temp);
        }

        return Result.ok(resultList);
    }


    @GetMapping("/opentimeinfo")
    Result getOpenTimeInfo(){

        List<LibraryManager> result = libraryManagerService.getTime();



        List<Map<String,Object>> resultList = new ArrayList<>();

        for(int i=0; i<result.size();i++){
            Map<String, Object> temp = new HashMap<>();
            temp.put("content", result.get(i).getContent());
            temp.put("duration", result.get(i).getDuration());
            temp.put("opentime", result.get(i).getOpentime());
            resultList.add(temp);
        }

        return Result.ok(resultList);
    }



    @GetMapping("/closetimeinfo")
    Result getCloseTimeInfo(){

        List<LibraryManager> result = libraryManagerService.getTime();



        List<Map<String,Object>> resultList = new ArrayList<>();

        for(int i=0; i<result.size();i++){
            Map<String, Object> temp = new HashMap<>();
            temp.put("content", result.get(i).getContent());
            temp.put("duration", result.get(i).getDuration());
            temp.put("closetime", result.get(i).getClosetime());
            resultList.add(temp);
        }

        return Result.ok(resultList);
    }



    @GetMapping("/booksnuminfo")
    Result getBooksInfo(){

        List<LibraryManager> result = libraryManagerService.getBooksNum();


        List<Map<String,Object>> resultList = new ArrayList<>();

        for(int i=0; i<result.size();i++){
            Map<String, Object> temp = new HashMap<>();
            temp.put("content", result.get(i).getContent());
            temp.put("booksnum", result.get(i).getBooksnum());
            resultList.add(temp);
        }

        return Result.ok(resultList);
    }

}
