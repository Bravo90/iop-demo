package com.sitech.billing.datainput.fileparse;

import com.sitech.billing.common.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 文本文件解析
 *
 * @author sunzhen
 * @date 2019/1/24 10:59
 */
public class TextFileParse implements BaseFileParse {
    @Override
    public List<String> parse(InputStream inputStream) throws IOException {
        return IOUtils.toList(inputStream);
    }
}
