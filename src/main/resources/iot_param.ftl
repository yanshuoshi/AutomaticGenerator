package ${packageName};

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.models.common.common.pojo.BaseParam;
/**
 * 
 */
@Data
@ApiModel(value="${className?cap_first}",description="${className?cap_first}")
public class ${className?cap_first}Param extends  BaseParam
{
    private static final long serialVersionUID = 1L;
    <#list  variablelist as column>
    @ApiModelProperty(value = "${column.name}")
    private ${column.type} ${column.fieldName};
    </#list>
}
