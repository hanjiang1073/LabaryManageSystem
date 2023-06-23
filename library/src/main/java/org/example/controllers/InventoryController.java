package org.example.controllers;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.entity.Inventory;
import org.example.springboot.services.interfaces.IInventoryService;
import org.example.common.AutoLog;
import org.example.common.Result;
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
@RequestMapping("/inventory")
public class InventoryController {

    @Resource
    private IInventoryService inventoryService;

    @AutoLog("新增库存")
    @PostMapping
    public Result save(@RequestBody Inventory inventory) {
//        User user = SessionUtils.getUser();
//        inventory.setUser(user.getName());
//        inventory.setUserid(user.getId());
//        inventory.setDate(DateUtil.today());
//        inventory.setTime(DateUtil.now());
        inventoryService.save(inventory);
        return Result.success();
    }

    @AutoLog("编辑库存")
    @PutMapping
    public Result update(@RequestBody Inventory inventory) {
        inventoryService.updateById(inventory);
        return Result.success();
    }

    @AutoLog("删除库存")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        inventoryService.removeById(id);
        return Result.success();
    }

    @AutoLog("批量删除库存")
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        inventoryService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.ok(inventoryService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.ok(inventoryService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Inventory> queryWrapper = new QueryWrapper<Inventory>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return Result.ok(inventoryService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
    * 导出接口
    */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Inventory> list = inventoryService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("Inventory信息表", "UTF-8");
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
        List<Inventory> list = reader.readAll(Inventory.class);

        inventoryService.saveBatch(list);
        return Result.success();
    }

}
