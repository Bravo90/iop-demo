package com.sitech.billing.datainput.fileparse;

import java.io.InputStream;
import java.util.List;

/**
 * 导入文件解析接口
 *
 * @author sunzhen
 * @date 2019/1/24 11:02
 */
public interface BaseFileParse {
    List<String> parse(InputStream inputStream) throws Exception;
}
