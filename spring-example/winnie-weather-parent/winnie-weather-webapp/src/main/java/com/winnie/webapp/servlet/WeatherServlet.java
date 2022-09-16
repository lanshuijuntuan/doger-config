package com.winnie.webapp.servlet;

import com.alibaba.fastjson.JSONObject;
import com.winnie.dto.CityDto;
import com.winnie.dto.WeatherDto;
import com.winnie.webapp.service.CityService;
import com.winnie.webapp.service.WeatherService;
import com.winnie.webapp.service.impl.CityServiceImpl;
import com.winnie.webapp.service.impl.WeatherServiceImpl;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WeatherServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cityName=request.getParameter("cityName");
        PrintWriter printWriter =response.getWriter();
        if(StringUtils.isEmpty(cityName)){
            printWriter.write("cityName参数非法");
            return;
        }
        CityService cityService=new CityServiceImpl();
        CityDto cityDto =cityService.getCityDtoByName(cityName);
        if(cityDto==null){
            printWriter.write("未找到关联的城市");
            return;
        }

        WeatherService weatherService=new WeatherServiceImpl();
        WeatherDto weatherDto =weatherService.getWeatherByCityCode(cityDto.getCode());
        if(weatherDto==null){
            printWriter.write("未找到相关城市天气信息");
            return;
        }
        printWriter.write(JSONObject.toJSONString(weatherDto));
        printWriter.close();
    }

}
