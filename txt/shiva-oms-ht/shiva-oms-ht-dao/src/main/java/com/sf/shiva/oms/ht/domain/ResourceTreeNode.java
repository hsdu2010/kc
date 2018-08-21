package com.sf.shiva.oms.ht.domain;

import java.util.List;

public class ResourceTreeNode {

    public ResourceTreeNode() {}
    
    /** 资源名称 */
    String name;

    /** 资源id */
    Long  id;
    
    /** 资源uid */
    String uid;
    
    /** 节点是否打开 */
    Boolean open = true;
    
    /** 是否父节点 */
    Boolean isParent = false;
    
    /** 资源url */
    String resourceUrl;
    
    /** 资源类型 */
    Integer resourceLevel;
    
    /** 是否选中 */
    Boolean checked;
    
    /** 子节点 */
    List<ResourceTreeNode> children;

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public List<ResourceTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceTreeNode> children) {
        this.children = children;
    }


    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

	public Integer getResourceLevel() {
		return resourceLevel;
	}

	public void setResourceLevel(Integer resourceLevel) {
		this.resourceLevel = resourceLevel;
	}
    
    
}
