package com.elliot.mall.mapper;

import com.elliot.mall.model.CmsTopicComment;
import com.elliot.mall.model.CmsTopicCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsTopicCommentMapper {
    long countByExample(CmsTopicCommentExample example);

    int deleteByExample(CmsTopicCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsTopicComment row);

    int insertSelective(CmsTopicComment row);

    List<CmsTopicComment> selectByExample(CmsTopicCommentExample example);

    CmsTopicComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CmsTopicComment row, @Param("example") CmsTopicCommentExample example);

    int updateByExample(@Param("row") CmsTopicComment row, @Param("example") CmsTopicCommentExample example);

    int updateByPrimaryKeySelective(CmsTopicComment row);

    int updateByPrimaryKey(CmsTopicComment row);
}