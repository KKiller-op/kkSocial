package org.kk.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kk.bean.News;
import org.kk.bean.PageRequest;
import org.kk.bean.PageResult;
import org.kk.bean.Status;
import org.kk.dao.NewsDao;
import org.kk.util.PageUtils;
import org.kk.util.RedisUtil;
import org.kk.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private NewsDao newsDao;

    public PageResult getSelectRandom(PageRequest pageRequest){
        int pageNum = pageRequest.getPageNum(), pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<News> list = newsDao.getSelectRandom();
        ToolUtil.setList(list);
        PageResult pageResult = PageUtils.getPageResult(new PageInfo<>(list));
        return pageResult;
    }
    public PageResult getNewsByTag(String tag, PageRequest pageRequest){
        tag = "#" + tag + "#";
        int pageNum = pageRequest.getPageNum(), pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<News> list = newsDao.getNewsByTag(tag);
        return PageUtils.getPageResult(new PageInfo<>(list));
    }
    public PageResult getFriends(Integer id, PageRequest pageRequest){
        int pageNum = pageRequest.getPageNum(), pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<News> newsAndUserById = newsDao.getNewsAndUserById(id);
        return PageUtils.getPageResult(new PageInfo<>(newsAndUserById));
    }
    public List<String> getTopTag() {
        boolean flag = redisUtil.hashKey("top");
        if (flag) {
            List<String> topTag = newsDao.getTopTag();
            System.out.println("topTag = " + topTag);
            redisUtil.set("top", topTag, 10800); //三小时刷新一下热搜
        }
        Object top = redisUtil.get("top");
        System.out.println("top = " + top);
        return (List<String>) top;
    }
    public String getTopSearch(){
        boolean flag = redisUtil.hashKey("topSearch");
        if(flag){
            String search = newsDao.getSearch().split(" ")[0].replaceAll("#", " ").strip();
            redisUtil.set("topSearch", search , 10800);
        }
        Object topSearch = redisUtil.get("topSearch");
        return (String) topSearch;
    }
    public Integer add(News news){
        System.out.println(news);
        int i = newsDao.add(new Status<News>(news));
        return i;
    }
}