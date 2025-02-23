package com.schoolused.service.Impl;

import com.schoolused.entry.indexMapUrl;
import com.schoolused.mapper.indexMapDao;
import com.schoolused.service.indexMapservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class indexMapserviceImpl implements indexMapservice {
    @Autowired
    indexMapDao indexMapDao;
    @Override
    public List<indexMapUrl> getIndexMap() {
        List res = indexMapDao.getMapUrl();
        return res;
    }

    @Override
    public int submitNewMap(indexMapUrl indexMapUrl) {

        int x = indexMapDao.AddedMap(indexMapUrl);

        return x;
    }

    @Override
    public int deleteAllMap() {
        List<Integer> res = indexMapDao.getMapId();
        int x = indexMapDao.deleturl(res);
        return x;
    }
}
