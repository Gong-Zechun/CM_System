package com.coin.service;

import com.common.HttpRequest;
import com.util.PropertiesReaderUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-15 10:38
 */
public interface ICoinPriceService{

    /**
     * 查询结果集
     * @param coinDataParam 需要查询的接口url的参数
     * @return 查询的结果集
     * @throws IOException
     */
    public List<String> queryFrom3Party(String coinDataParam) throws IOException;
}
