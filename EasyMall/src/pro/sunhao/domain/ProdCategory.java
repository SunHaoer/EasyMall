package pro.sunhao.domain;

/**
 * 封装商品种类信息的Bean
 * @author Administrator
 *
 */
public class ProdCategory {
	private int id;		// 商品种类编号
	private String cname;		// 商品种类名称
	
	public ProdCategory() {
		super();
	}

	public ProdCategory(int id, String cname) {
		super();
		this.id = id;
		this.cname = cname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "ProdCategory [id=" + id + ", cname=" + cname + "]";
	}
}
