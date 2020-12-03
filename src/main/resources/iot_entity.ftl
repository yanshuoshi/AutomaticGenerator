package ${packageName};

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import lombok.Data;

/**
 * 
 */
@Data
public class ${className?cap_first} implements Serializable
{
    private static final long serialVersionUID = 1L;
    <#list  variablelist as column>
    private ${column.type} ${column.code};  //${column.name}
    </#list>
    
     
    @Override
    public String toString() {
	 return ToStringBuilder.reflectionToString(this);
    }

}
