package com.nuesoft.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuesoft.po.BookInfo;
import com.nuesoft.po.BookType;
import com.nuesoft.po.BookTypeExample;
import com.nuesoft.service.BookInfoService;
import com.nuesoft.service.BookTypeService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsFileUploadSupport;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class BookController implements ServletContextAware {
    private ServletContext servletContext;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;
    }

    @Autowired
    private BookTypeService bookTypeService;
    @Autowired
    private BookInfoService bookInfoService;

    @RequestMapping("/download.do")
    public ResponseEntity<byte[]> downLoad(String bookPath) throws IOException {
        String paths = "E:\\apache-tomcat-9.0.0.M26\\webapps\\ROOT\\";
        String path=paths+bookPath;
        System.out.println(path);
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();
        // String fileName=new
        // String("你好.txt".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
        headers.setContentDispositionFormData("attachment", "java1.jpg");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    @RequestMapping("/test.do")
    public void test(){
        List<BookInfo> bookInfos = bookInfoService.selectAllBookInfo(2, "三", 0);
        for(BookInfo bookInfo : bookInfos){
            System.out.println(bookInfo);
        }
    }

    @RequestMapping("/toUpdate.do")
    public String toUpdate(int bookId,ModelMap map){
        List<BookType> bookTypes = bookTypeService.selectByExample(new BookTypeExample());
        map.put("bookTypes",bookTypes);
        BookInfo bookInfo = bookInfoService.selectByPrimaryKey(bookId);
        map.put("bookInfo",bookInfo);
        return "updateBook";
    }

    @RequestMapping("/updateBook.do")
    public String updateBook(BookInfo bookInfo){
        int i = bookInfoService.updateByPrimaryKeySelective(bookInfo);
        return "forward:BookInfo.do";
    }

    @RequestMapping("/details.do")
    public String details(int bookId,ModelMap map){
        BookInfo bookInfo = bookInfoService.selectOneBook(bookId);
        map.put("bookInfo",bookInfo);
        return "details";
    }

    @RequestMapping("/deleteAllBook.do")
    public String deleteAllBook(@RequestParam(value="bookid",required=false) String [] ids){
        int i = bookInfoService.deleteAllBook(ids);
        return "forward:BookInfo.do";
    }

    @RequestMapping("/deleteBook.do")
    public String deleteBook(int bookId){
        int i = bookInfoService.deleteByPrimaryKey(bookId);
        return "forward:BookInfo.do";
    }

    @RequestMapping("/BookInfo.do")
    public String selectBookInfo(
                                   @RequestParam(value = "booktype",required=false,defaultValue = "0") Integer bookType,
                                   @RequestParam(value ="bookname" ,required=false,defaultValue = "") String bookName,
                                   @RequestParam(value="isborrow" ,required=false,defaultValue = "-1") Integer isBorrow,
                                   @RequestParam(value = "num",required = false,defaultValue = "1") Integer num,ModelMap map){
        List<BookType> bookTypes = bookTypeService.selectByExample(new BookTypeExample());
        PageHelper.startPage(num,3);
        List<BookInfo> BookInfo = bookInfoService.selectAllBookInfo(bookType, bookName, isBorrow);
        PageInfo<com.nuesoft.po.BookInfo> pageInfo = new PageInfo<>(BookInfo);
        map.put("pageInfo",pageInfo);
        map.put("bookTypes",bookTypes);
        map.put("bookType",bookType);
        map.put("bookName",bookName);
        map.put("isBorrow",isBorrow);
        return "BookInfo";
    }






    @RequestMapping("toAdd.do")
    public String toAddBookInfo(ModelMap map){
        List<BookType> bookTypes = bookTypeService.selectByExample(new BookTypeExample());
        map.put("bookTypes",bookTypes);
        return "addBookInfo";
    }


    @RequestMapping("fileImage.do")
    @ResponseBody
    public String uploadImage(@RequestParam("fileImage") CommonsMultipartFile fileImage){
        // 获取上传图片的位置
        String path = servletContext.getRealPath("/resource/upload/");
        // 获取文件名称
        String filename = fileImage.getOriginalFilename();
        // 创建file对象 写入
        File file = new File(path, filename);
        try {
            fileImage.getFileItem().write(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 将上传的图片路径以json的方式返回客户端
        String imagePath="resource/upload/"+filename;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imagePath",imagePath);
        String jsonString = jsonObject.toJSONString();
        return jsonString;
    }

    @RequestMapping("/addBookInfo.do")
    public String addBookInfo(BookInfo bookInfo){
        bookInfo.setIsBorrow(0);
        int i = bookInfoService.insertSelective(bookInfo);
        return "forward:BookInfo.do";
    }



}
