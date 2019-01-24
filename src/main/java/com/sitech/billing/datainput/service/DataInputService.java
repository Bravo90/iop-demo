package com.sitech.billing.datainput.service;

import com.sitech.billing.datainput.model.DataInputTable;

import java.util.List;

/**
 *
 * @author sunzhen
 * @date 2019/1/24 13:32
 */
public interface DataInputService {
    int batchSave(List<String> list, DataInputTable table);
}
