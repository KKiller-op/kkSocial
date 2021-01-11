package com.kk.bean;

import com.kk.bean.parent.Parent;

/**
 * 评论类
 */
public class Comments extends Parent {
    private Integer new_id; //动态表ID
    private String content; //评论内容
    private User user; //用户id;
    private String create_date; //创建时间
    private int like;
    private Integer write_id; //父级评论Id


    public Comments(){}
    public Comments(Integer id, Integer new_id, String content) {
        super(id);
        this.new_id = new_id;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "news_id=" + new_id +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", create_date='" + create_date + '\'' +
                ", like=" + like +
                '}';
    }

    public Integer getNew_id() {
        return new_id;
    }

    public void setNew_id(Integer new_id) {
        this.new_id = new_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getWrite_id() {
        return write_id;
    }

    public void setWrite_id(Integer write_id) {
        this.write_id = write_id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
