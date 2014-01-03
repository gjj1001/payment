package com.casit.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.casit.bean.entity.ProductType;
import com.casit.service.ProductTypeService;
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

	/**��ʾ��ӽ���
	 * @return
	 */
	public String addUi() {
		
		return "add";
	}
	
	/**ʵ����Ӳ���
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
		ActionContext.getContext().put("message", "������ɹ�");
		return Action.SUCCESS;
	}
	
	/**ʵ���޸Ĳ���
	 * @return
	 */
	public String update() {
		ProductType pt = pts.find(ProductType.class, typeId);
		pt.setName(name);
		pt.setNote(note);		
		pts.update(pt);
		ActionContext.getContext().put("message", "�޸����ɹ�");
		return Action.SUCCESS;
	}
	
	/**��ʾ�޸�ҳ��
	 * @return
	 */
	public String updateUi() {		
		ProductType pt = pts.find(ProductType.class, typeId);
		name = pt.getName();
		note = pt.getNote();		
		return "edit";
	}
	
	/**��ѯ����
	 * @return
	 */
	public String queryUi() {
		return "query";
	}
}
