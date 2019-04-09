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

    //查询书籍跟类型详细信息
    BookInfo selectBookInfoDetails(Integer book_id);

    //根据id批量删除书籍
    int deleteBookInfo(int[] ids);

    //根据属性查询
    List<BookInfo> selectAllBookInfo(@Param("book_type") int book_type,@Param("book_name") String book_name,@Param("is_borrow") int is_borrow);
}