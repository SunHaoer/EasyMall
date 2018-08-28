package pro.sunhao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pro.sunhao.domain.Prod;
import pro.sunhao.util.JDBCUtils;

public class CartDaoImpl implements CartDao {
	@Override
	public boolean addProdToCartByProd(int pid, String pname, String username) {
		String sql = "insert into " + username + "_cart" + "(id, pid, pname, pnum) values(null, ?, ?, 1)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			ps.setString(2, pname);
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
	public String getCidByPidInCart(String pame, String username) {
		//System.out.println("----");
		String sql = "select * from " + username + "_cart" + " where pname = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, pame);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("pid") + "#" + rs.getInt("pnum");
			} else {
				return "#";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, ps, rs);
		}
		return "#";
	}

	@Override
	public boolean updatePnumByPidInCart(int id, int num, String username) {
		String sql = "update " + username + "_cart" + " set pnum = ? where pid = ?";
		//System.out.println(username + " " + num);
		//String sql = "UPDATE  " + username + "_cart SET pnum = ? WHERE id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		//ResultSet rs = null;

		try {
			conn = JDBCUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			ps.setInt(2, id);
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
	public List<Prod> getListProdInCart(String username) {
		String sql = "select * from "+ username + "_cart, prod where " + username + "_cart.pid = prod.id";
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
				list.add(prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, ps, rs);
		}
		//System.out.println(list);
		return list;
	}
}
