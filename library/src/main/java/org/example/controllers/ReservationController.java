package org.example.controllers;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.Result;
import com.example.springboot.entity.Reservation;
import com.example.springboot.service.IReservationService;
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
* @author zqy
* @since 2023-06-23
*/
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Resource
    private IReservationService reservationService;

    @AutoLog("新增预约")
    @PostMapping
    public Result save(@RequestBody Reservation reservation) {
//        User user = SessionUtils.getUser();
//        reservation.setUser(user.getName());
//        reservation.setUserid(user.getId());
//        reservation.setDate(DateUtil.today());
//        reservation.setTime(DateUtil.now());
        reservationService.save(reservation);
        return Result.success();
    }

    @AutoLog("编辑预约")
    @PutMapping
    public Result update(@RequestBody Reservation reservation) {
        reservationService.updateById(reservation);
        return Result.success();
    }

    @AutoLog("删除预约")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        reservationService.removeById(id);
        return Result.success();
    }

    @AutoLog("批量删除预约")
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        reservationService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.ok(reservationService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.ok(reservationService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Reservation> queryWrapper = new QueryWrapper<Reservation>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return Result.ok(reservationService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
    * 导出接口
    */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Reservation> list = reservationService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("Reservation信息表", "UTF-8");
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
        List<Reservation> list = reader.readAll(Reservation.class);

        reservationService.saveBatch(list);
        return Result.success();
    }

}
