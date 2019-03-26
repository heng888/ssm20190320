package com.nuesoft.service;

import com.nuesoft.po.BookInfo;
import com.nuesoft.po.BookInfoExample;

import java.util.List;

public interface BookInfoService {
    int deleteByPrimaryKey(Integer bookId);

    int insert(BookInfo record);

    int insertSelective(BookInfo record);

    List<BookInfo> selectByExample(BookInfoExample example);

    BookInfo selectByPrimaryKey(Integer bookId);

    int updateByPrimaryKeySelective(BookInfo record);

    int updateByPrimaryKey(BookInfo record);

    List<BookInfo> selectAllBookInfo(Integer book_type,String book_name,Integer is_borrow);

    //批量删除
    int deleteAllBook(String[] ids);

    //根据id查询一本书籍的详细信息
    BookInfo selectOneBook(int book_id);
}
