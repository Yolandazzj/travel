package com.qdu.service;

import com.qdu.dao.SearchDao;
import com.qdu.page.page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;


    @Override
    public List searchEssay(String keyword) {

        return searchDao.searchEssay(keyword);
    }


    @Override
    public List searchHotel(String keyword) {
        return searchDao.searchHotel(keyword);
    }

    //分页
    @Override
    public page queryForPage(int currentPage, int pageSize,String keyword) {
        page page = new page();
        //总记录数
        int allRow =searchDao.getAllRowCount(keyword);
        //当前页开始记录为第几条
        int offset = page.countOffset(currentPage,pageSize);
        //分页查询结果集
        List list = searchDao.searchRoute(keyword,offset,pageSize);

        page.setPageNo(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRecords(allRow);
        page.setList(list);

        return page;
    }
}
