package com.sitech.billing.datainput.controller;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.common.exception.IopException;
import com.sitech.billing.customization.table.model.Table;
import com.sitech.billing.datainput.fileparse.BaseFileParse;
import com.sitech.billing.datainput.fileparse.FileParseFactory;
import com.sitech.billing.datainput.model.DataInputTable;
import com.sitech.billing.datainput.service.DataInputTableService;
import com.sitech.billing.system.base.BaseController;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 数据导入
 *
 * @author sunzhen
 * @date 2019/1/23 11:26
 */
@RestController
@RequestMapping("/input")
public class DataInputController extends BaseController {

    @RequestMapping("/page")
    public ModelAndView page() {
        return new ModelAndView("datainput/datainput");
    }

    @GetMapping("/table")
    public JsonResult tables(@RequestParam(name = "tableName", required = false) String tableName,
                             @RequestParam(name = "tableDesc", required = false) String tableDesc,
                             @RequestParam(name = "tableId", required = false) Integer tableId,
                             @RequestParam(name = "pageSize") int pageSize,
                             @RequestParam(name = "pageNum") int pageNum) {

        PageInfo<DataInputTable> info =
                dataInputTableService.listTables(tableName, tableDesc, tableId, pageSize, pageNum);
        return JsonResult.success(info);
    }

    @GetMapping("/table/{id}")
    public JsonResult table(@PathVariable("id") int id) {
        DataInputTable table = dataInputTableService.getTableById(id);
        return JsonResult.success(table);
    }

    @PostMapping("/table")
    public JsonResult tableAdd(@RequestBody DataInputTable dataInputTable) {
        dataInputTableService.saveTable(dataInputTable);
        return JsonResult.success("添加成功");
    }

    @PutMapping("/table")
    public JsonResult tableUpdate(@RequestBody DataInputTable dataInputTable) {
        dataInputTableService.updateTable(dataInputTable);
        return JsonResult.success("更新成功");
    }

    @DeleteMapping("/table")
    public JsonResult tableDel(@RequestBody List<Integer> ids) {
        dataInputTableService.delTables(ids);
        return JsonResult.success("删除成功");
    }

    /**
     * 文件上传并导入数据
     *
     * @param file     文件
     * @param id
     * @param fileType 文件类型
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public JsonResult upload(@RequestParam("file") MultipartFile file,
                             @RequestParam("inputId") int id,
                             @RequestParam("fileType") int fileType) throws IOException {
        if (file.isEmpty()) {
            throw new IopException("上传失败，请选择文件");
        }
        InputStream is = file.getInputStream();
        BaseFileParse fileParse = FileParseFactory.getFileParse(fileType);
        List<String> list = fileParse.parse(is);

        DataInputTable table = dataInputTableService.getTableById(id);
        int rows = dataInputService.batchSave(list, table);
        return JsonResult.success("导入数据成功,导入数据量：" + rows);
    }
}
