package com.common.util;

import java.util.ArrayList;
import java.util.List;
import com.modules.sys.entity.Menu;
public class MenuTreeUtil {

	/**
	*<b>Summary:list转换为树形结构 </b>
	* buildMenuTree()
	* @param list
	* @return
	 */
	public List<Menu> buildMenuTree(List<Menu> list) {
		
		List<Menu> trees = new ArrayList<Menu>();
		for (Menu treeNode : list) {
			if ("0".equals(treeNode.getParent().getId())&& "1".equals(treeNode.getIsShow())) {
				trees.add(findChildren(treeNode, list));
			}
		}
		return trees;
	}
	
	/**
	*<b>Summary: 迭代查询子节点</b>
	* findChildren()
	* @param treeNode
	* @param treeNodes
	* @return
	 */
	private Menu findChildren(Menu treeNode,List<Menu> treeNodes) {
		for (Menu it : treeNodes) {  
            if(treeNode.getId().equals(it.getParent().getId())&&"1".equals(treeNode.getIsShow())) { 
                treeNode.addSubMenuList(findChildren(it,treeNodes));  
            }  
        }  
        return treeNode;  
	}
	
}
