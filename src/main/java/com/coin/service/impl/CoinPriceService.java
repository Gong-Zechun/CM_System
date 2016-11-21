package com.coin.service.impl;

import com.coin.service.ICoinPriceService;
import com.common.HttpRequest;
import com.util.PropertiesReaderUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-15 10:38
 */
@Service
public class CoinPriceService implements ICoinPriceService{
    //前端传过来的参数值对应的url参数名
    public static final Map<Integer, String> dataSourceMap;
    static{
        Map<Integer, String> temp = new HashMap<>();
        temp.put(0, "btccUrlParam");
        temp.put(1, "huobiUrlParam");
        temp.put(2, "okcoinUrlParam");
        temp.put(3, "chbtcUrlParam");
        temp.put(4, "coinbaseUrlParam");
        dataSourceMap = Collections.unmodifiableMap(temp);
    }

    @Override
    public List<String> queryFrom3Party(String coinDataParam) throws IOException{
        Properties prop = PropertiesReaderUtil.reader("coinDataSource.properties");
        String btc123Url = prop.getProperty("btc123Url").trim();
        String[] tempArray = coinDataParam.split(",");
        List<String> paramList = new ArrayList<>();
        for(String meta : tempArray) {
            int paramInt = Integer.parseInt(meta);
            String urlParam = dataSourceMap.get(paramInt);
            paramList.add(prop.getProperty(urlParam).trim());
        }
        return queryFrom3Party(btc123Url, paramList);
    }

    public List<String> queryFrom3Party(String btc123Url, List<String> paramList) {
        List<String> resultList = new ArrayList<String>();
        for(String meta : paramList) {
            StringBuilder urlSb = new StringBuilder(btc123Url + meta);
            String result = HttpRequest.sendGet(urlSb.toString());
            resultList.add(result);
        }
        return resultList;
    }

    public static void main(String[] args) {
        for(Map.Entry<Integer, String> entry : dataSourceMap.entrySet()) {
            System.out.println(entry.getKey()+"--->"+entry.getValue());
        }
    }
}
