package com.sitech.billing.datainput.controller;

import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.datainput.execute.DataInputExecute;
import com.sitech.billing.system.base.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
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

    @PostMapping("/upload")
    public JsonResult upload(@RequestParam("file") MultipartFile file,
                             @RequestParam("inputId") int id,
                             @RequestParam("fileType") int fileType) {
        if (file.isEmpty()) {
            throw new RuntimeException("上传失败，请选择文件");
        }
        try {
            InputStream is = file.getInputStream();
            int input = DataInputExecute.input(is, id, fileType);
            return JsonResult.success(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
