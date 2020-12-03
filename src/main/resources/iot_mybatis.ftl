<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.models.demo.mapper.${className?cap_first}Mapper">
   <resultMap id="BaseResultMap" type="${packageName!}.${className?cap_first}">
      <#list  variablelist as column>
      <result column="${column.fieldName}" property="${column.code}"/>
      </#list>
   </resultMap>
   
   <sql id="all">
    <#list variablelist as column><#if column_has_next>${column.fieldName},<#else>${column.fieldName}</#if></#list>
   </sql>
   
    
   <select id="findById" resultMap="BaseResultMap" >
	select <include refid="all" />  from ${table.code!}  where ${pkgFieldName!} = ${"#"}{${pkgCodeName!}}    limit 1
   </select>
   
   <insert id="insertBatch"  parameterType="java.util.List">  
    insert into ${table.code!}  
    (
     	<#list variablelist as column><#if column_has_next>${column.fieldName},<#else>${column.fieldName}</#if></#list>
    )
    values  
    <foreach collection="list" item="item" index="index" separator="," >  
      (
      <#list  variablelist as column>
          <#if column_has_next>  
            ${"#"}{item.${column.code}},
          <#else> 
            ${"#"}{item.${column.code}}
          </#if>
         
      </#list>    
      )
    </foreach>  
   </insert> 
   
   
   <insert id="insert">
	  insert into ${table.code!}   
      <trim prefix="(" suffix=")" suffixOverrides=",">
     <#list  variablelist as column>
         <if test="${column.code} != null ">${column.fieldName},</if>
      </#list>
      </trim>
      <trim prefix=" values (" suffix=")" suffixOverrides=",">
      <#list  variablelist as column>
          <if test="${column.code} != null">${"#"}{${column.code}},</if>
      </#list>    
      </trim>
   </insert>
   
    <update id="update"> 
      update ${table.code!} 
       <set>
       <#list  variablelist as column>
          <if test="${column.code}  != null ">${column.fieldName} = ${"#"}{${column.code}},</if>
       </#list>  
      </set>
      where  ${pkgFieldName!} = ${"#"}{${pkgCodeName!}} 
   </update>
   
   
   <update id="updateSelective"> 
      update ${table.code!} 
       <set>
       <#list  variablelist as column>
          <if test="${column.code}  != null ">${column.fieldName} = ${"#"}{${column.code}},</if>
       </#list>  
      </set>
	 <trim prefix="where" prefixOverrides="and |or "> 
	    <#list  variablelist as column>
          <if test="${column.code}  != null ">and ${column.fieldName} = ${"#"}{${column.code}}</if>
       </#list>  
	 </trim>
   </update>
   
   
   <select id="findByNamedParam" resultMap="BaseResultMap">
	select <include refid="all" />  from  ${table.code!}  
	<trim prefix="where" prefixOverrides="and |or "> 
	    <#list  variablelist as column>
          <if test="${column.code}  != null ">and ${column.fieldName} = ${"#"}{${column.code}}</if>
       </#list>  
	</trim>
    order  by   ${pkgFieldName!}   desc    limit 1
   </select>
   
   <select id="findByNamedParamList" resultMap="BaseResultMap">
	select <include refid="all" />  from  ${table.code!}   
	<trim prefix="where" prefixOverrides="and |or "> 
	    <#list  variablelist as column>
          <if test="${column.code}  != null ">and ${column.fieldName} = ${"#"}{${column.code}}</if>
       </#list>  
	</trim>
    order  by  ${pkgFieldName!}  desc    
   </select>
    
   <delete id="delete">
	delete  from  ${table.code!}   where   ${pkgFieldName!} = ${"#"}{${pkgCodeName!}} 
   </delete>
	
   <update id="remove">
	update ${table.code!}  set  status = '1'  where   ${pkgFieldName!} = ${"#"}{${pkgCodeName!}} 
   </update>
   
   
   <select id="findPageInfo" resultMap="BaseResultMap">
     select  <include refid="all" />   from ${table.code!} 
     <trim prefix="where" prefixOverrides="and |or "> 
	    <#list  variablelist as column>
          <if test=" ${column.code}  != null ">and ${column.fieldName} = ${"#"}{${column.code}}</if>
       </#list>  
	 </trim>
     order  by ${pkgFieldName!}   desc  limit ${"#"}{startRow},${"#"}{pageSize}
   </select>
   
   <select id="countPageInfo"  resultType="java.lang.Integer">
	select  count(1) from  ${table.code!}   
	<trim prefix="where" prefixOverrides="and |or "> 
	    <#list  variablelist as column>
          <if test="${column.code}  != null ">and ${column.fieldName} = ${"#"}{${column.code}}</if>
       </#list>  
	</trim>
   </select>
   
   
</mapper>