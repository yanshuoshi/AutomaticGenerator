package com.models.demo.Service;

import  com.models.demo.domain.${className?cap_first};
import com.models.common.service.impl.BaseService;
import com.models.common.utils.BaseQuery;
import com.models.common.utils.QueryResult;
import java.util.List;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 */
public interface ${className?cap_first}Service extends BaseService<${className?cap_first}>{

   ${className?cap_first} findById(Serializable id);

    int insertBatch(List<${className?cap_first}> ${className?uncap_first}s);

    int insert(${className?cap_first} ${className?uncap_first});

    int update(${className?cap_first} ${className?uncap_first});

    int delete(Serializable id);

    int remove(Serializable id);

    QueryResult<${className?cap_first}> findPageInfo(BaseQuery baseQuery);

    List<${className?cap_first}> findByNamedParamList (Map<String,Object> param);


}
