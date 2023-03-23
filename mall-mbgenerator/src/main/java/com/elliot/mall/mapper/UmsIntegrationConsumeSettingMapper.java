package com.elliot.mall.mapper;

import com.elliot.mall.model.UmsIntegrationConsumeSetting;
import com.elliot.mall.model.UmsIntegrationConsumeSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsIntegrationConsumeSettingMapper {
    long countByExample(UmsIntegrationConsumeSettingExample example);

    int deleteByExample(UmsIntegrationConsumeSettingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsIntegrationConsumeSetting row);

    int insertSelective(UmsIntegrationConsumeSetting row);

    List<UmsIntegrationConsumeSetting> selectByExample(UmsIntegrationConsumeSettingExample example);

    UmsIntegrationConsumeSetting selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UmsIntegrationConsumeSetting row, @Param("example") UmsIntegrationConsumeSettingExample example);

    int updateByExample(@Param("row") UmsIntegrationConsumeSetting row, @Param("example") UmsIntegrationConsumeSettingExample example);

    int updateByPrimaryKeySelective(UmsIntegrationConsumeSetting row);

    int updateByPrimaryKey(UmsIntegrationConsumeSetting row);
}