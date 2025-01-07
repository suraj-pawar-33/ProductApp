package com.apps.digiple.npdapp.dbdriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.apps.digiple.npdapp.models.AddressModel;
import com.apps.digiple.npdapp.models.BankModel;
import com.apps.digiple.npdapp.models.ContactModel;
import com.apps.digiple.npdapp.models.FieldInfo;
import com.apps.digiple.npdapp.models.Order2ProductModel;
import com.apps.digiple.npdapp.models.OrderModel;
import com.apps.digiple.npdapp.models.ProductModel;

public class DBHelper {
	private static final Logger LOGGER = Logger.getLogger(DBHelper.class);

	static final String OFFLINE = " No connection available";
	static Connection con;
	private static String[] args01;
	public static void createConnection(String[] args) {
		args01 = args;
		try {
			con = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s", args[0], args[1]).toString(), args[2], args[3]);
			LOGGER.info(String.format("Connected to %s > %s", args[1], args[0]).toString());
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
		}
//		localhost:3306
//		npddb
//		suraj
//		Mysql@3395

	}
	
	static Statement getStatement() throws SQLException {
		if (con == null) {
			throw new RuntimeException(OFFLINE);
		}
		return con.createStatement();
	}

	public static void closeCon() {
		try {
			if (con != null) {
				con.close();
				LOGGER.info(String.format("Disconnected %s > %s", args01[1], args01[0]).toString());
			}
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
		}
	}
	public static ResultSet selectBankQuery() throws Exception {
		try {

			Statement stmt = getStatement();
			return stmt.executeQuery(
					"select b.bank_key, b.bank_name, b.short_name, "
					+ "b.branch_name, a.line_1, a.line_2, a.line_3, a.city, "
					+ "a.zip_postcode, a.state, a.country, a.other, a.address_key from bank b "
					+ "LEFT JOIN address a ON a.object_key  = b.bank_key;");
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}

	public static ResultSet selectContactQuery() throws Exception {
		try {

			Statement stmt = getStatement();
			return stmt.executeQuery(
					"select b.contact_key, b.contact_name, b.bank_key, "
					+ "a.line_1, a.line_2, a.line_3, a.city, "
					+ "a.zip_postcode, a.state, a.country, a.other, a.address_key from bank_contact b "
					+ "LEFT JOIN address a ON a.object_key  = b.contact_key;");
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}
	public static void saveAddressQuery(AddressModel address, int objKey) throws Exception{
		try {
			Statement stmt = getStatement();
			String query = String.format(
					"INSERT INTO address(object_key, line_1, line_2 ,line_3 ,"
							+ "city, zip_postcode, state, country, other, creation_time)"
							+ "VALUES(%d, '%s', '%s' ,'%s' ,'%s', %d, '%s', '%s', '%s',NOW());",
					objKey, address.getLine1(), address.getLine2(), address.getLine3(), address.getCity(),
					address.getZip(), address.getState(), address.getCountry(), address.getOther());
			int result = stmt.executeUpdate(query);
			
			LOGGER.info("saveAddressQuery > " + result);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}
	
	public static int saveContactQuery(ContactModel contact) throws Exception {
		try {
			Statement stmt = getStatement();
			String insertBank = String.format(
					"INSERT INTO bank_contact(contact_name, bank_key, creation_time)"
					+ "VALUES('%s', %d, NOW());", contact.getContactName(), contact.getBankKey());
			int result = stmt.executeUpdate(insertBank);

			ResultSet resultSet = stmt.executeQuery("SELECT contact_key FROM bank_contact ORDER BY contact_key DESC LIMIT 1;");

			int key = 0;
			if (resultSet.next()) {
				key = resultSet.getInt(1);
				AddressModel address = contact.address();
				saveAddressQuery(address, key);
			}
			LOGGER.info("saveContactQuery > " + result );
			
			return key;
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}

	public static int saveBankQuery(BankModel bank) throws Exception {
		try {
			Statement stmt = getStatement();
			String insertBank = String.format(
					"INSERT INTO bank(bank_name, short_name, branch_name, creation_time)"
					+ "VALUES('%s', '%s', '%s', NOW());", bank.getBankName(), bank.getShortName(), bank.getBranchName());
			int result = stmt.executeUpdate(insertBank);

			ResultSet resultSet = stmt.executeQuery("SELECT bank_key FROM bank ORDER BY bank_key DESC LIMIT 1;");

			int key = 0;
			if (resultSet.next()) {
				key = resultSet.getInt(1);
				AddressModel address = bank.address();
				saveAddressQuery(address, key);
			}
			LOGGER.info("saveBankQuery > " + result );
			
			return key;
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}

	public static void updateBankQuery(BankModel bank) throws Exception {
		try {
			Statement stmt = getStatement();
			String insertBank = String.format(
					"UPDATE bank SET bank_name = '%s', short_name = '%s',"
					+ " branch_name = '%s' WHERE bank_key = '%s';", 
					bank.getBankName(), bank.getShortName(), bank.getBranchName(), bank.getKey());
			int result1 = stmt.executeUpdate(insertBank);

			AddressModel address = bank.address();
			String insertAddr = String.format(
					"UPDATE address SET line_1 = '%s', line_2 = '%s',line_3 = '%s',city = '%s', "
					+ "zip_postcode = %d, state = '%s', country = '%s', other = '%s' "
					+ "WHERE object_key = %d;", 
					address.getLine1(), address.getLine2(), address.getLine3(), 
					address.getCity(), address.getZip(), address.getState(), address.getCountry(), 
					address.getOther(), bank.getKey());
			int result2 = stmt.executeUpdate(insertAddr);
			
			LOGGER.info("updateBankQuery > " + result1 + " " + result2);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}
	
	public static void updateContactQuery(ContactModel contact) throws Exception {
		try {
			Statement stmt = getStatement();
			String insertBank = String.format(
					"UPDATE bank SET contact_name = '%s',"
					+ " bank_key = '%s' WHERE contact_key = '%s';", 
					contact.getContactName(), contact.getBankKey(), contact.getKey());
			int result1 = stmt.executeUpdate(insertBank);

			AddressModel address = contact.address();
			String insertAddr = String.format(
					"UPDATE address SET line_1 = '%s', line_2 = '%s',line_3 = '%s',city = '%s', "
					+ "zip_postcode = %d, state = '%s', country = '%s', other = '%s' "
					+ "WHERE object_key = %d;", 
					address.getLine1(), address.getLine2(), address.getLine3(), 
					address.getCity(), address.getZip(), address.getState(), address.getCountry(), 
					address.getOther(), contact.getKey());
			int result2 = stmt.executeUpdate(insertAddr);
			
			LOGGER.info("updateBankQuery > " + result1 + " " + result2);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}
	
	public static void deleteBankQuery(int bankKey) throws Exception {
		try {
			Statement stmt = getStatement();
			String insertBank = String.format(
					"DELETE FROM bank WHERE bank_key = %d;", 
					bankKey);
			int result1 = stmt.executeUpdate(insertBank);

			String insertAddr = String.format(
					"DELETE FROM address WHERE object_key = %d;", 
					bankKey);
			int result2 = stmt.executeUpdate(insertAddr);
			
			LOGGER.info("deleteBankQuery > " + result1 + " " + result2);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}
	
	

	public static ResultSet selectProductQuery() throws Exception {
		try {
			Statement stmt = getStatement();
			ResultSet rs = stmt.executeQuery("select P.product_key, P.product_name, PT.pro_type_name, "
					+ "P.product_details, P.short_name, P.cost , P.subscri_cost from product P "
					+ "INNER JOIN product_type PT ON (PT.pro_type_key= P.pro_type_key);");
			return rs;
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}

	

	public static int saveProductQuery(ProductModel product) throws Exception {
		try {
			Statement stmt = getStatement();
			String query2 = String.format(
					"INSERT INTO product(product_name, short_name, product_details, cost, subscri_cost , pro_type_key  , creation_time) "
							+ "VALUES('%s', '%s', '%s', %d, %d, %d,NOW());",
					product.getProductName(), product.getShortName(), product.getProductDetail(),
					Integer.valueOf(product.getProductCost()), product.getProSubCost(), product.getProTypeKey());
			int result2 = stmt.executeUpdate(query2);
			ResultSet resultSet = stmt
					.executeQuery("SELECT product_key FROM product ORDER BY product_key DESC LIMIT 1;");
			
			int productKey = 0;
			if (resultSet.next()) {
				productKey = resultSet.getInt(1);
			}
			LOGGER.info("saveProductQuery > " + result2);
			return productKey;
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}
	
	public static int updateProductQuery(ProductModel product) throws Exception {
		try {
			Statement stmt = getStatement();
				String query2 = String.format(
						"UPDATE product SET product_name = '%s', short_name = '%s', product_details = '%s', "
						+ "cost = %d, subscri_cost = %d, pro_type_key = %d"
						+ "WHERE product_key = %d;",
						product.getProductName(), product.getShortName(), product.getProductDetail(), product.getProductCost(),
						product.getProSubCost(), product.getProTypeKey(), product.getKey());
				int result2 = stmt.executeUpdate(query2);
				LOGGER.info("updateProductQuery > " + result2);
			
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
		return 0;
	}
	
	public static int deleteProductQuery(int productKey) throws Exception {
		try {
			Statement stmt = getStatement();
				String query2 = String.format(
						"DELETE FROM product WHERE product_key = %d;",
						productKey);
				int result2 = stmt.executeUpdate(query2);
				LOGGER.info("deleteProductQuery > " + result2);
			
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
		return 0;
	}

	public static ResultSet selectOrderQuery() throws Exception {
		try {
			Statement stmt = getStatement();
			ResultSet rs = stmt.executeQuery("SELECT O.order_key, O.date_order_placed, O.bill_number, B.short_name ,OT.ord_type_key , "
					+ "O.total_amount, S.status_name, O2P.ord2pro_key, O2P.order_key, O2P.product_key, O2P.quantity, O2P.amount, OT.ord_type_name, B.branch_name "
					+ "FROM orders O "
					+ "LEFT OUTER JOIN bank B ON (B.bank_key = O.bank_key) "
					+ "LEFT OUTER JOIN order_type OT ON (OT.ord_type_key  = O.ord_type_key) "
					+ "LEFT OUTER JOIN status  S ON (S.status_key   = O.status_key) "
					+ "LEFT OUTER JOIN order2product  O2P ON (O2P.order_key   = O.order_key) "
					+ "LEFT OUTER JOIN product  P ON (P.product_key   = O2P.product_key)ORDER BY O.order_key;");
			return rs;
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}
	
	public static int saveOrderQuery(OrderModel order) throws Exception {
		try {
			Statement stmt = getStatement();
			String query = String.format(
					"INSERT INTO orders(date_order_placed, bill_number, bank_key,ord_type_key,"
					+ "total_amount, status_key, creation_time)"
					+ "VALUES(STR_TO_DATE('%s', '%s'), "
					+ "'%s', %d,%d,%d, %d, NOW());",
					order.getPlacedDate(), "%Y-%m-%d %H:%i:%s", order.getBillNumber(), order.getBankKey(), 
					order.getOrderTypeKey(), order.getTotalAmount(), order.getStatusKey());
			int result = stmt.executeUpdate(query);
			ResultSet resultSet = stmt.executeQuery("SELECT order_key FROM orders ORDER BY order_key DESC LIMIT 1;");
			int orderKey = 0;
			if (resultSet.next()) {
				orderKey = resultSet.getInt(1);
			}
			
			StringBuilder result2 = new StringBuilder();
			for (Iterator<Order2ProductModel> iterator = order.getProducts().iterator(); iterator.hasNext();) {
				Order2ProductModel product = iterator.next();
				String updateBankquery = String.format(
						"INSERT INTO order2product(order_key, product_key, quantity, amount, creation_time)VALUES(%d, %d, %d, %d, NOW());",
						orderKey, product.getProductKey(), product.getQuantity(), product.getAmount());
				result2.append(stmt.executeUpdate(updateBankquery));
			}
			LOGGER.info("saveOrderQuery > " + result + " " + result2);
			return orderKey;
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}

	public static int updateOrderQuery(OrderModel order) throws Exception {
		try {
			Statement stmt = getStatement();
			String query = String.format(
					"UPDATE orders SET date_order_placed = STR_TO_DATE('%s', '%s'), "
					+ "bill_number = '%s', "
					+ "bank_key = %d, ord_type_key = %d, total_amount = %d, status_key = %d "
					+ "WHERE order_key = %d;",
					order.getPlacedDate(), "%Y-%m-%d", order.getBillNumber(), order.getBankKey(), order.getOrderTypeKey(), order.getTotalAmount(), order.getStatusKey(), order.getOrderKey());
			int result = stmt.executeUpdate(query);
			StringBuilder result2 = new StringBuilder();
			for (Iterator<Order2ProductModel> iterator = order.getProducts().iterator(); iterator.hasNext();) {
				Order2ProductModel ord2Pro = iterator.next();
				ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM order2product "
						+ "WHERE ord2pro_key = %d", ord2Pro.getKey()));
				String updateOR2PRquery = null;
				if (rs.next()) {
					updateOR2PRquery = String.format("UPDATE order2product SET quantity = %d, amount = %d ",
							ord2Pro.getQuantity(), ord2Pro.getAmount());
				} else {
					updateOR2PRquery = String.format("INSERT INTO order2product(order_key, product_key, quantity, amount, creation_time)"
							+ "VALUES(%d, %d, %d, %d, NOW());", 
							order.getOrderKey(), ord2Pro.getProductKey(), ord2Pro.getQuantity(), ord2Pro.getAmount());					
				}
				result2.append(stmt.executeUpdate(updateOR2PRquery));
			}
			
			LOGGER.info("updateOrderQuery > " + result + " " + result2);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
		return 0;
	}
	
	public static int deleteOrderQuery(int orderKey) throws Exception {
		try {
			Statement stmt = getStatement();
			
			String updateBankquery = String.format("DELETE FROM order2product WHERE order_key = %d;",
					orderKey);
			int result2 = stmt.executeUpdate(updateBankquery);
			
			String query = String.format(
					"DELETE FROM orders WHERE order_key = %d;",
					orderKey);
			int result = stmt.executeUpdate(query);
			
			LOGGER.info("deleteOrderQuery > " + result + " " + result2);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
		return 0;
	}
	
	public static ResultSet getStatusList() throws Exception {
		try {
			Statement stmt = getStatement();
			return stmt.executeQuery("select status_key, status_name FROM status;");
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}

	public static ResultSet getOrderType() throws Exception {
		try {
			Statement stmt = getStatement();
			return stmt.executeQuery("select ord_type_key, ord_type_name FROM order_type;");
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}

	public static ResultSet getBankNameList() throws Exception {
		try {
			Statement stmt = getStatement();
			return stmt.executeQuery("select bank_key, short_name, branch_name FROM bank ORDER BY short_name ASC;");
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}
	
	public static ResultSet getProductType() throws Exception {
		try {
			Statement stmt = getStatement();
			return stmt.executeQuery("select pro_type_key , pro_type_name FROM product_type;");
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}
	
	public static int tempUpdateBillColumn() throws Exception {
		try {
			Statement stmt = getStatement();
			String sql = "ALTER TABLE orders MODIFY COLUMN bill_number INT UNIQUE;";

			stmt.executeUpdate(sql);
			LOGGER.info("tempUpdateBillColumn success >>>>>>>>");
			return 1;
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}
	
	public static int tempUpdatBankQuery() throws Exception {
		try {
			Statement stmt = getStatement();
			Statement stmt2 = getStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT bank_key, bank_name FROM bank ORDER BY bank_key DESC;");

			while (resultSet.next()) {
				String query2 = "INSERT INTO address(object_key, line_1, line_2 ,line_3, "
						+ "city, zip_postcode, state, country, other, creation_time)"
						+ "VALUES(0, '-', NULL ,NULL ,'-', 0, NULL, NULL, NULL,NOW())";		
				
				stmt2.executeUpdate(query2);
				String query3 = "UPDATE bank SET "
						+ "address_key = ( SELECT address_key "
						+ "FROM address ORDER BY address_key DESC LIMIT 1) "
						+ "WHERE bank_key = " + resultSet.getInt(1);
				stmt2.executeUpdate(query3);
			}
			LOGGER.info("tempUpdatBankQuery success >>>>>>>>" );
			return 1;
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.debug(e);
			throw e;
		}
	}

	public static ResultSet selectGeneralQuery(String dbTableName, FieldInfo[] fieldInfoList) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
