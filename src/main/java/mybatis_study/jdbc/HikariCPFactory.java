package mybatis_study.jdbc;

import java.io.IOException;
import java.util.Properties;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.io.Resources;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPFactory extends UnpooledDataSourceFactory {
	private static HikariDataSource dataSource;
	private static int minIdle = 10;
	private static int maxPoolSize = 100;

	public HikariCPFactory() {
		//resources에 있는 properties 파일을 Properties 로 읽어들이기
		
		try {
			Properties prop = Resources.getResourceAsProperties("application.properties");
			 HikariConfig cfg = new HikariConfig(prop);
	            dataSource = new HikariDataSource(cfg);
	            dataSource.setMinimumIdle(minIdle);
	            dataSource.setMaximumPoolSize(maxPoolSize);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HikariDataSource getDataSource() {
		return dataSource;
	}
	
}
