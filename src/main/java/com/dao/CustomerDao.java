package com.dao;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.bean.CustomerBean;
@Component
public class CustomerDao {
	
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	

	
	
	public void addCustomer(CustomerBean customer) {
		
//		 String query="insert into customer (firstname,email,password) values (?,?,?)";
		// jdbctemplate.update(query,customer.getFirstName(),customer.getEmail(), customer.getPassword());
		jdbctemplate.update("insert into customer (firstname,email,password) values (?,?,?)", customer.getFirstName(),
				customer.getEmail(), customer.getPassword());
		
		
		
		
		
		
	}




	public int testdao() {
		// TODO Auto-generated method stub
		return 89;
	}
	public CustomerBean authenticate(String email, String password) {

		CustomerBean customer = null;

		try {
			customer = jdbctemplate.queryForObject("select * from customer where email like ? and password like ?",
					new BeanPropertyRowMapper<CustomerBean>(CustomerBean.class), email, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return customer;
	}

	public void updateToken(int customerId, String token) {
		jdbctemplate.update("update customer set token = ? where customerId = ?", token, customerId);
	}

	public CustomerBean getCustomerByToken(String token) {
		CustomerBean customer = null;

		try {
			customer = jdbctemplate.queryForObject("select * from customer where token like ? ",
					new BeanPropertyRowMapper<CustomerBean>(CustomerBean.class), token);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return customer;
	}




	public CustomerBean getCustomerById(int customerId) {
		CustomerBean customer = null;

		try {
			customer = jdbctemplate.queryForObject("select * from customer where customerId=?",
					new BeanPropertyRowMapper<CustomerBean>(CustomerBean.class), customerId);
			
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		return customer;
		
				
		
	}
	
	public void updateCustomer(int id,CustomerBean customer)
	{
		jdbctemplate.update("update customer set firstname=?,email=?,password=?,token = ? where customerId = ?",customer.getFirstName(),customer.getEmail(),customer.getPassword(), customer.getToken(),id);

	}

}

