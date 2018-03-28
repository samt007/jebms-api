package com.jebms.fnd.entity;

import java.util.ArrayList;
import java.util.List;

//菜单VO对象，其实就是菜单行。不过包括一些很重要的信息，例如层次化查询需要用到的。还有子菜单列表！
//方便调用。
public class FndMenuVO extends FndMenuLineVO{

    /**
     * 层次
     */
	private Integer level;
	
    /**
     * 哪个头菜单下的展开的所有菜单明细
     */
	private Long topMenuId;
	
    /**
     * 是否是叶子节点
     */
    private Boolean leaf = true;
    
    /**
     * 父节点(menuId-menuSequence)
     */
	private String parent;
	
    /**
     * 子节点
     */
    private List<FndMenuVO> children = new ArrayList<>();

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getTopMenuId() {
		return topMenuId;
	}

	public void setTopMenuId(Long topMenuId) {
		this.topMenuId = topMenuId;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public List<FndMenuVO> getChildren() {
		return children;
	}

	public void setChildren(List<FndMenuVO> children) {
		this.children = children;
	}

    /**
     * 添加子节点
     *
     * @param node 菜单节点
     */
    public void addChild(FndMenuVO node) {
        this.children.add(node);
    }

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	
	
    /*private List<FndMenuLineVO> menuLineVOs = new ArrayList<>();

	public List<FndMenuLineVO> getMenuLineVOs() {
		return menuLineVOs;
	}

	public void setMenuLineVOs(List<FndMenuLineVO> menuLineVOs) {
		this.menuLineVOs = menuLineVOs;
	}*/

}