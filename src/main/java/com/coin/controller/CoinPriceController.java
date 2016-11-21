package com.coin.controller;

import com.coin.service.ICoinPriceService;
import com.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

import static com.coin.service.impl.CoinPriceService.dataSourceMap;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-14 15:38
 */
@Controller
@RequestMapping("coin")
public class CoinPriceController {
    private static final Logger log = LoggerFactory.getLogger(CoinPriceController.class);

    @Resource
    private ICoinPriceService coinPriceService;

    @RequestMapping("coinprice")
    public String index(Model model) {
        try{
            return "coin/coinManage";
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping("data")
    @ResponseBody
    public String getData(String coinDataParam) {
        try{
            List<String> resultList = coinPriceService.queryFrom3Party(coinDataParam);
            String result = StringUtil.toJSONString(resultList);
            return result;
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
