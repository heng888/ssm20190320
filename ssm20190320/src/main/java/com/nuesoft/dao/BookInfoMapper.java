package com.nuesoft.dao;

import com.nuesoft.po.BookInfo;
import com.nuesoft.po.BookInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookInfoMapper {
    int deleteByPrimaryKey(Integer bookId);

    int insert(BookInfo record);

    int insertSelective(BookInfo record);

    List<BookInfo> selectByExample(BookInfoExample example);

    BookInfo selectByPrimaryKey(Integer bookId);

    int updateByPrimaryKeySelective(BookInfo record);

    int updateByPrimaryKey(BookInfo record);

    //查询批量书籍
    List<BookInfo> selectAllBookInfo(@Param("book_type") Integer book_type,@Param("book_name") String book_name,@Param("is_borrow") Integer is_borrow);

    //批量删除
    int deleteAllBook(String[] ids);

    //根据id查询一本书籍的详细信息
    BookInfo selectOneBook(int book_id);
}