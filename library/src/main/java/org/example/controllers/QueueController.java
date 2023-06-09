package org.example.controllers;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.Result;
import org.example.entity.Queue;
import org.example.entity.Reservation;
import org.example.services.interfaces.IQueueService;
import org.example.common.AutoLog;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

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
        Queue temp = queueService.getById(queue.getQueueId());

        if(temp != null){
            queueService.updateById(queue);

        }
        else {
            queueService.save(queue);
        }
        return Result.ok(queue);
    }

    @AutoLog("编辑排队")
    @PutMapping
    public Result update(@RequestBody Queue queue) {
        queueService.updateById(queue);
        return Result.ok(queue);
    }

    @AutoLog("删除排队")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        queueService.removeById(id);
        return Result.ok("成功删除排队，id: "+id);
    }

    @AutoLog("批量删除排队")
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        queueService.removeByIds(ids);
        return Result.ok("成功批量删除排队");
    }

    @GetMapping
    public Result findAll() {
        return Result.ok(queueService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.ok(queueService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Queue> queryWrapper = new QueryWrapper<Queue>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return Result.ok(queueService.page(new Page<>(pageNum, pageSize), queryWrapper));
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
