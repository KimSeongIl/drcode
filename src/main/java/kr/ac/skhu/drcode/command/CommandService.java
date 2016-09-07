package kr.ac.skhu.drcode.command;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;




@Service
public class CommandService {
	Connection connection = null;

	public void dbLogin(DatabaseDto database) throws ClassNotFoundException, SQLException {
		/*	String JDBC_DRIVER_NAME = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://allan.kr:3306/drcode?useUnicode=true&characterEncoding=utf8";
		String USER_ID="drcode";
		String USER_PASSWORD="qwer1234"; 
		 */

		String JDBC_DRIVER_NAME = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://"+database.getUrl()+":3306/" +database.getDbName()+"?useUnicode=true&characterEncoding=utf8";
		String USER_ID=database.getUserName();
		String USER_PASSWORD=database.getPassword(); 

		Class.forName(JDBC_DRIVER_NAME);
		try{
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_ID, USER_PASSWORD);
		}catch(Exception e){
			if(connection !=null){
				connection.close();
				
			}
			throw new ClassNotFoundException("연결 정보가 올바르지 않습니다.");
		}
		

	}

	public void dbDisconnect() throws SQLException {		
		if (connection != null) connection.close();
	}

	public Object queryExecute(String query) throws IOException, ClassNotFoundException, SQLException{

		/*String JDBC_DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String DB_URL = "jdbc:sqlserver://192.168.63.25;databaseName=bbs1";
		String USER_ID="sa";
		String USER_PASSWORD="test123"; */

		if(connection==null) {
			throw new SQLException("연결 정보가 없습니다.");
		}


		StringBuffer sql = new StringBuffer(query);

		Map<String, Object> map = null;
		map = new LinkedHashMap<String,Object>();
		try(PreparedStatement psmt = connection.prepareStatement(sql.toString());){
			boolean flag = psmt.execute();

			if(flag == true) {
				try(ResultSet resultset = psmt.getResultSet();){

					java.sql.ResultSetMetaData meta = resultset.getMetaData();
					int colCount = meta.getColumnCount();
					for( int i=1; i<=colCount; i++ ){

						map.put(meta.getColumnName(i), new ArrayList<String>());
					}
					List<String> tmp;
					while (resultset.next()) {

						for( int i=1; i<=colCount; i++ ){
							tmp=(ArrayList<String>)map.get(meta.getColumnName(i));
							String result=resultset.getString(meta.getColumnName(i));
							if(result!=null && result.length()>300){
								result=result.substring(0,300);

							}

							tmp.add(result);
							map.put(meta.getColumnName(i),tmp);
						}

					}
				}


			}

			else {

				int cnt = psmt.getUpdateCount();
				map.put("count",cnt);
			}
		}



		return map;

	}
}
