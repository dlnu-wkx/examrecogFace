package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zsysconfig;

public interface ZsysconfigService {
    Zsysconfig findIPByZname(String zname);
}
