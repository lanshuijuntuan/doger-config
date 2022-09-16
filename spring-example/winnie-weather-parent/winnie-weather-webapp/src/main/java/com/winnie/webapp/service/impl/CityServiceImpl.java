package com.winnie.webapp.service.impl;

import com.winnie.dto.CityDto;
import com.winnie.webapp.service.CityService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CityServiceImpl implements CityService {
    @Override
    public CityDto getCityDtoByName(String name) {
        List<CityDto> cityDtoList=genList();
        List<CityDto> queryList=cityDtoList.stream().filter(new Predicate<CityDto>() {
            @Override
            public boolean test(CityDto cityDto) {
                if(name.equals(cityDto.getName())){
                    return true;
                }
                return false;
            }
        }).collect(Collectors.toList());
        if(queryList.size()==0){
            return null;
        }
        return queryList.get(0);
    }


    private List<CityDto> genList(){
        List<CityDto> list=new ArrayList<>();
        CityDto sz=new CityDto();
        sz.setCode("101280601");
        sz.setName("深圳");
        list.add(sz);
        return list;
    }
}
