package com.sitech.billing.datainput.fileparse;

import com.sitech.billing.common.exception.IopException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * 导入文件解析工厂类
 *
 * @author sunzhen
 * @date 2019/1/24 11:06
 */
@Slf4j
public class FileParseFactory {

    private static HashMap<Integer, Class<?>> fileParseMap = new HashMap<>();

    public static BaseFileParse getFileParse(Integer fileType) {
        try {
            BaseFileParse baseFileParse = (BaseFileParse) fileParseMap.get(fileType).newInstance();
            return baseFileParse;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new IopException("文件解析类不存在");
        }
    }

    static {
        fileParseMap.put(1, TxtFileParse.class);
        fileParseMap.put(2, ExcelFileParse.class);
    }
}
