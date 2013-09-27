package com.casit.web.action.product;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.casit.bean.product.ProductType;
import com.casit.service.product.ProductTypeService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

@Controller @Scope("prototype")
public class ProductTypeManageAction {
	@Resource
	private ProductTypeService pts;
	private String name;
	private String note;
	private Integer parentid;
	private Integer typeId;
	
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	/**显示添加界面
	 * @return
	 */
	public String addUi() {
		
		return "add";
	}
	
	/**实行添加操作
	 * @return
	 */
	public String add() {
		ProductType pt = new ProductType();
		/*List<String> props = new LinkedList<String>();
		Map<String, Object> map = ActionContext.getContext().getParameters();
		Set<Map.Entry<String,Object>> set = map.entrySet();
		for(Map.Entry<String,Object> key : set) {			
			props.add(((String[])key.getValue())[0]);			
		}*/
		
		pt.setName(name);
		pt.setNote(note);
		if(parentid!=null&&parentid>0) {
			pt.setParent(new ProductType(parentid));
		}		
		pts.save(pt);
		ActionContext.getContext().put("message", "添加类别成功");
		return Action.SUCCESS;
	}
	
	/**实行修改操作
	 * @return
	 */
	public String update() {
		ProductType pt = pts.find(ProductType.class, typeId);
		pt.setName(name);
		pt.setNote(note);		
		pts.update(pt);
		ActionContext.getContext().put("message", "修改类别成功");
		return Action.SUCCESS;
	}
	
	/**显示修改页面
	 * @return
	 */
	public String updateUi() {		
		ProductType pt = pts.find(ProductType.class, typeId);
		name = pt.getName();
		note = pt.getNote();		
		return "edit";
	}
	
	/**查询界面
	 * @return
	 */
	public String queryUi() {
		return "query";
	}
}
