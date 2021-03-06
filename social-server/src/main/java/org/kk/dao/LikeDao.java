package org.kk.dao;

import org.kk.bean.Like;

import java.util.List;

public interface LikeDao {
    List<Like> getUserById(Integer user_id);

    int addLike(Like like);

    int deleteLike(Integer id);
}
