package pro.sunhao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import pro.sunhao.domain.Prod;
import pro.sunhao.domain.ProdCategory;
import pro.sunhao.exception.MsgException;
import pro.sunhao.util.JDBCUtils;
import pro.sunhao.util.TransactionManager;
import pro.sunhao.util.TransactionManager;

/**
 * 为商品提供数据库查询的dao
 * @author Administrator
 *
 */
public class ProdDaoImpl implements ProdDao {

	@Override
	public int getCidByCname(String cname) throws MsgException {
		String sql = "select id from prod_category where cname = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = TransactionManager.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cname);
			rs = ps.executeQuery();
			if(rs.next()) {			// 查到数据
				return rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps, rs);
		}
		return -1;		// 未查到数据
	}

	@Override
	public boolean insertProdCategory(ProdCategory pc) {
		String sql = "insert into prod_category value(null, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = TransactionManager.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, pc.getCname());
			int line = ps.executeUpdate();
			if(line > 0) {		// 更新成功
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps, null);
		}
		return false;
	}

	@Override
	public boolean insertProd(Prod prod) {
		String sql = "insert into prod(pname,price,cid,pnum,imgurl,description) values(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = TransactionManager.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, prod.getName());
			ps.setDouble(2, prod.getPrice());
			ps.setInt(3, prod.getCid());
			ps.setInt(4, prod.getPnum());
			ps.setString(5, prod.getImgurl());
			ps.setString(6, prod.getDescription());
			int line = ps.executeUpdate();
			if(line > 0) {		// 插入成功
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps, null);
		}
		return false;
	}

	@Override
	public List<Prod> listProd() {
		String sql = "select p.*, c.cname from prod p join prod_category c on p.cid = c.id";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Prod> list = new ArrayList<Prod>();
		try {
			conn = JDBCUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Prod prod = new Prod();
				prod.setId(rs.getInt("id"));
				prod.setName(rs.getString("pname"));
				prod.setPrice(rs.getDouble("price"));
				prod.setCid(rs.getInt("cid"));
				prod.setPnum(rs.getInt("pnum"));
				prod.setImgurl(rs.getString("imgurl"));
				prod.setDescription(rs.getString("description"));
				prod.setCname(rs.getString("cname"));
				list.add(prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public boolean updatePnumByPid(int pid, int pnum) {
		String sql = "update prod set pnum = ? where id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pnum);
			ps.setInt(2, pid);
			int line = ps.executeUpdate();
			if(line > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, ps, null);
		}
		return false;
	}

	@Override
	public int getCidByPid(Integer pid) {
		String sql = "select cid from prod where id = ?";		// 悲观锁模式
		List<Prod> list = new ArrayList<Prod>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = TransactionManager.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("cid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps, rs);
		}
		return 0;
	}

	@Override
	public List<Prod> listProdByCid(int cid) {
		String sql = "select * from prod where cid = ? for update";
		List<Prod> list = new ArrayList<Prod>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = TransactionManager.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			while(rs.next()) {
				Prod prod = new Prod();
				prod.setId(rs.getInt("id"));
				prod.setName(rs.getString("pname"));
				prod.setPrice(rs.getDouble("price"));
				prod.setCid(rs.getInt("cid"));
				prod.setPnum(rs.getInt("pnum"));
				prod.setImgurl(rs.getString("imgurl"));
				prod.setDescription(rs.getString("description"));
				list.add(prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps, rs);
		}
		return list;
	}

	@Override
	public boolean deleteProdCategroyByCid(int cid) {
		String sql="delete from prod_category where id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = TransactionManager.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cid);
			int line = ps.executeUpdate();
			if(line > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps, null);
		}
		return false;
	}

	@Override
	public boolean deleteProdByPid(Integer pid) {
		String sql="delete from prod where id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = TransactionManager.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			int line = ps.executeUpdate();
			if(line > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps, null);
		}
		return false;
	}
}
