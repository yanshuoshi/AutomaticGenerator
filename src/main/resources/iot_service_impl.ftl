package com.models.demo.Service.impl;

import com.models.common.utils.BaseQuery;
import com.models.common.utils.QueryResult;
import com.models.demo.domain.${className?cap_first};
import com.models.demo.mapper.${className?cap_first}Mapper;
import com.models.demo.service.${className?cap_first}Service;
import com.models.common.service.impl.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 */
@Service
public class ${className?cap_first}ServiceImpl extends AbstractService<${className?cap_first}>  implements ${className?cap_first}Service {


   @Autowired
   ${className?cap_first}Mapper ${className?uncap_first}Mapper;

   @Override
   public ${className?cap_first} findById(Serializable id) {
     return ${className?uncap_first}Mapper.findById(id);
   }

    @Override
    public int insertBatch(List<${className?cap_first}> ${className?uncap_first}s) {
      return ${className?uncap_first}Mapper.insertBatch(${className?uncap_first}s);
    }

    @Override
    public int insert(${className?cap_first} ${className?uncap_first}) {
      return ${className?uncap_first}Mapper.insert(${className?uncap_first});
    }

    @Override
    public int update(${className?cap_first} ${className?uncap_first}) {
      return ${className?uncap_first}Mapper.update(${className?uncap_first});
    }

    @Override
    public List<${className?cap_first} > findByNamedParamList(Map<String,Object> param){
        return tIotLocationsMapper.findByNamedParamList(serializable);
    }

    @Override
    public int delete(Serializable id) {
      return ${className?uncap_first}Mapper.delete(id);
    }

    @Override
    public int remove(Serializable id) {
      return ${className?uncap_first}Mapper.remove(id);
    }

    @Override
    public QueryResult<${className?cap_first}> findPageInfo(BaseQuery baseQuery) {
        QueryResult<${className?cap_first}> result = new QueryResult<${className?cap_first}>();
        result.setQuery(baseQuery);
        Map
        <String, Object> params = result.getQuery().build();
        Integer amount = this.${className?uncap_first}Mapper.countPageInfo(params);
        result.setTotalRecord(amount);
        if (amount == 0) {
        return result;
        }
        List<${className?cap_first}> list = ${className?uncap_first}Mapper.findPageInfo(params);
        if (!CollectionUtils.isEmpty(list)) {
        result.setResultList(list);

        }
        return result;
    }
}
