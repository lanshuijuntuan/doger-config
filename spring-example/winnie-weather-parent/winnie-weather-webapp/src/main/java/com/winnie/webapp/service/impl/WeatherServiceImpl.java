package com.winnie.webapp.service.impl;

import com.winnie.dto.WeatherDto;
import com.winnie.webapp.service.WeatherService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WeatherServiceImpl implements WeatherService {
    @Override
    public WeatherDto getWeatherByCityCode(String cityCode) {
        List<WeatherDto> list=genList();
        List<WeatherDto> queryList=list.stream().filter(new Predicate<WeatherDto>() {
            @Override
            public boolean test(WeatherDto weatherDto) {
                if(weatherDto.getCityId().equals(cityCode)){
                    return true;
                }
                return false;
            }
        }).collect(Collectors.toList());
        if(queryList.isEmpty()){
            return null;
        }
        return queryList.get(0);
    }





    private List<WeatherDto> genList(){
        List<WeatherDto> weatherDtoList=new ArrayList<>();
        WeatherDto sz=new WeatherDto();
        sz.setAp("999.3hPa");
        sz.setCity("深圳");
        sz.setCityId("101280601");
        sz.setRadar("JC_RADAR_AZ9755_JB");
        sz.setIsRadar("1");
        sz.setNjd("暂无实况");
        sz.setSd("83%");
        sz.setSm("1.3");
        sz.setTemp("28.3");
        sz.setTime("17:50");
        sz.setWd("西南风");
        sz.setWs("小于3级");
        sz.setWse("<3");
        weatherDtoList.add(sz);
        return weatherDtoList;
    }
}
