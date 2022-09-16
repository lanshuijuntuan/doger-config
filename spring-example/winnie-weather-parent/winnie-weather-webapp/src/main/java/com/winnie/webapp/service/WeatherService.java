package com.winnie.webapp.service;

import com.winnie.dto.WeatherDto;

public interface WeatherService {

    WeatherDto getWeatherByCityCode(String cityCode);


}
