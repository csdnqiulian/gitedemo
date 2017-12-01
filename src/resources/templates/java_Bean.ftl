<#-- Bean 中没处理字段类型，都默认为String -->
package ${template.packageOfBean};

<#list fields as field>
	<#if field.javaType == "Date">
import java.util.Date;
	<#break>
	</#if>
</#list>

/**
 *<p>Title:${template.nameOfBean}.java</p>
 *<p>Description:${table.remarks} Bean(对应表名:${table.table_name})</p>
 *<p>Copyright:Copyright (c) 2014 autoCoder</p>
 *<p>Company:湖南科创</p>
 *@author ${template.author}
 *@version 1.0
 *${template.nowDate}
 */
public class ${template.nameOfBean} {
<#list fields as field>
    private ${field.javaType} ${field.javaName};		//${field.jdbcRemark}
</#list>

<#list fields as field>
	/**
	*<b>Summary:设置${field.jdbcRemark}</b>
	* set${field.javaName?cap_first}
	* @param ${field.javaName}
	*/
    public void set${field.javaName?cap_first}(${field.javaType} ${field.javaName}){
    	this.${field.javaName} = ${field.javaName};
    }
    /**
	*<b>Summary:获取${field.jdbcRemark}</b>
	* get${field.javaName?cap_first}
	* @return
	*/
    public ${field.javaType} get${field.javaName?cap_first}(){
    	return ${field.javaName};
    }
    
</#list>
}