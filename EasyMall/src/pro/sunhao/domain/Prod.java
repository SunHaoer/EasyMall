package pro.sunhao.domain;

/**
 * 封装商品属性的Bean
 * @author Administrator
 *
 */
public class Prod {
	private int id;		// 商品id
	private String name;		// 商品名称
	private double price;		// 商品价格
	private int cid;	// 商品种类id
	private int pnum;	// 商品数量
	private String imgurl;		// 商品图片url
	private String description;		// 商品描述
	private String cname;		// 商品种类名称
	
	public Prod() {
		super();
	}

	public Prod(int id, String name, double price, int cid, int pnum,
			String imgurl, String description, String cname) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.cid = cid;
		this.pnum = pnum;
		this.imgurl = imgurl;
		this.description = description;
		this.cname = cname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "Prod (id=" + id + ", name=" + name + ", price=" + price
				+ ", cid=" + cid + ", pnum=" + pnum + ", imgurl=" + imgurl
				+ ", description=" + description + ", cname=" + cname + ")";
	}
}
