package com.schoolused.service;

import com.schoolused.entry.indexMapUrl;

import java.util.List;

public interface indexMapservice {
    public List<indexMapUrl> getIndexMap();
    public int submitNewMap(indexMapUrl indexMapUrl);
    public int deleteAllMap();

}
