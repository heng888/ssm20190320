package com.nuesoft.service;

import com.nuesoft.po.BookInfo;
import com.nuesoft.po.BookInfoExample;
import com.nuesoft.po.BookType;
import com.nuesoft.po.BookTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookTypeService {
    int deleteByPrimaryKey(Integer typeId);

    int insert(BookType record);

    int insertSelective(BookType record);

    List<BookType> selectByExample(BookTypeExample example);

    BookType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(BookType record);

    int updateByPrimaryKey(BookType record);
}
