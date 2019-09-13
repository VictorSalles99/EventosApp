//package com.eventosapp.data;
//
//
//import javax.sql.DataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//@Configuration
//public class DateConfiguration {
//
 


//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://localhost:3306/eventosapp?useTimezone=true&serverTimezone=UTC");
//		dataSource.setUsername("root");
//		dataSource.setPassword("root");
//		return dataSource;
//
//	}
//
//	@Bean
//	public JpaVendorAdapter jpaVendorAdapter() {
//		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//		adapter.setDatabase(Database.MYSQL);
//		adapter.setShowSql(true);
//		adapter.setGenerateDdl(true);
//		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL57Dialect");
//		adapter.setPrepareConnection(true);
//
//		return adapter;
//	}
//	
////	 @Bean
////	    public BasicDataSource dataSource() throws URISyntaxException {
////	        URI dbUri = new URI(System.getenv("DATABASE_URL"));
////
////	        String username = dbUri.getUserInfo().split(":")[0];
////	        String password = dbUri.getUserInfo().split(":")[1];
////	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
////
////	        BasicDataSource basicDataSource = new BasicDataSource();
////	        basicDataSource.setUrl(dbUrl);
////	        basicDataSource.setUsername(username);
////	        basicDataSource.setPassword(password);
////
//
//
//}
