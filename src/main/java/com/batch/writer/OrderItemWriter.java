package com.batch.writer;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.batch.model.Order;

import org.springframework.batch.item.ItemWriter;

public class OrderItemWriter implements ItemWriter<Order> {

	private static final String INSERT_ORDER = "insert into PUBLIC.TUTORIALS_TBL ( ordernum, custid, country ) values(?,?,?)";
	private static final String DELETE_ORDER = "DELETE FROM PUBLIC.TUTORIALS_TBL";
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public void write(List<? extends Order> orders) throws Exception {
		jdbcTemplate.update(DELETE_ORDER);
		for (Order order : orders) {
			jdbcTemplate.update(INSERT_ORDER, order.getOrderNum(),
					order.getCustId(), order.getCountry());
		}

	}

	public OrderItemWriter(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

}
