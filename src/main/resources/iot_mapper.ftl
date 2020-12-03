package com.models.common.mapper;

import com.models.demo.domain.${className?cap_first};
import org.apache.ibatis.annotations.Mapper;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Mapper
public interface ${className?cap_first}Mapper {

       ${className?cap_first} findById(Serializable id);

        int insertBatch(List<${className?cap_first}> userInfos);

        int insert(${className?cap_first} userInfo);

        int update(Map<String,Object> param);

        int updateSelective(Map<String,Object> param);

        ${className?cap_first} findByNamedParam(Map<String,Object> param);

        List<${className?cap_first}> findByNamedParamList(Map<String,Object> param);

        int delete(Serializable id);

        int remove(Serializable id);

        List<${className?cap_first}> findPageInfo(Map<String, Object> params);

        int countPageInfo(Map<String, Object> parameter);
}