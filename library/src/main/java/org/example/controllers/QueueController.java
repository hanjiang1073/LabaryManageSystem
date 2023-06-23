package com.example.springboot.controller;

import com.example.springboot.common.annotation.AutoLog;
import cn.hutool.core.date.DateUtil;
import com.example.springboot.entity.User;
import com.example.springboot.utils.SessionUtils;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import java.net.URLEncoder;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.Result;
import org.springframework.web.multipart.MultipartFile;
import com.example.springboot.service.IQueueService;
import com.example.springboot.entity.Queue;

import org.springframework.web.bind.annotation.RestController;

/**
* <p>
*  前端控制器
* </p>
*
* @author lzy
* @since 2023-06-23
*/
@RestController
@RequestMapping("/queue")
public class QueueController {

    @Resource
    private IQueueService queueService;

    @AutoLog("新增排队")
    @PostMapping
    public Result save(@RequestBody Queue queue) {
//        User user = SessionUtils.getUser();
//        queue.setUser(user.getName());
//        queue.setUserid(user.getId());
//        queue.setDate(DateUtil.today());
//        queue.setTime(DateUtil.now());
        queueService.save(queue);
        return Result.success();
    }

    @AutoLog("编辑排队")
    @PutMapping
    public Result update(@RequestBody Queue queue) {
        queueService.updateById(queue);
        return Result.success();
    }

    @AutoLog("删除排队")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        queueService.removeById(id);
        return Result.success();
    }

    @AutoLog("批量删除排队")
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        queueService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(queueService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(queueService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Queue> queryWrapper = new QueryWrapper<Queue>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return Result.success(queueService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
    * 导出接口
    */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Queue> list = queueService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("Queue信息表", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    /**
    * excel 导入
    * @param file
    * @throws Exception
    */
    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<Queue> list = reader.readAll(Queue.class);

        queueService.saveBatch(list);
        return Result.success();
    }

}
