package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PhoneVo;

public class PhoneDao {
		
		//필드
	
			// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			//connection 관련
			private String driver = "oracle.jdbc.driver.OracleDriver";
			private String url = "jdbc:oracle:thin:@localhost:1521:xe";
			
			private String id = "phonedb";
			private String pw = "phonedb";
			
			int count = 0;
			
			List<PhoneVo> pList;
			
			
		//생성자
	
		//g/s
	
		//일반 메서드
			
		// DB접속 메서드
		private void getConnection() {
			try {
			    // 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName(driver);

			    // 2. Connection 얻어오기
				conn = DriverManager.getConnection(url, id, pw);

			} catch (ClassNotFoundException e) {
			    System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
			    System.out.println("error:" + e);
			}
		}
		
		
		//자원 정리 메서드
		public void close() {
			
			try {
			        if (rs != null) {
			            rs.close();
			        }                
			        if (pstmt != null) {
			            pstmt.close();
			        }
			        if (conn != null) {
			            conn.close();
			        }
			    } catch (SQLException e) {
			        System.out.println("error:" + e);
			    }

		}
		
		///////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		
		public PhoneVo getPerson(int Id) {
			getConnection();
			
			PhoneVo pv = null;
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query = " select			person_id, ";
			    			query +=  "			name,	"	;	
			    			query +=  "			hp,	";
			    			query +=  "			company ";
			    			query +=  "	from	person	";
			    			query +=  "	where	person_id = ? ";
			    			
			    pstmt = conn.prepareStatement(query);
			    pstmt.setInt(1, Id);
			    
			    rs = pstmt.executeQuery();
			    
			    while(rs.next()) {
			    	
			    	int personId = rs.getInt(1);
			    	String name = rs.getString(2);
			    	String hp = rs.getString(3);
			    	String company = rs.getString(4);
			    	
			    	 pv = new PhoneVo(personId, name, hp, company);
			    }
			    // 4.결과처리
			    			
			    			
			} catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			
			
			return pv;
		}
		
		
		
		
		
		// 리스트 가져오는 메서드	
		public List<PhoneVo> getPhList() {
			getConnection();
			
			pList = new ArrayList<PhoneVo>();
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query = " select			person_id, ";
			    			query +=  "						name,	"	;	
			    			query +=  "						hp,	";
			    			query +=  "						company ";
			    			query +=  "	from			person	";
			    			query +=  "	order by		person_id asc ";
			    			
			    pstmt = conn.prepareStatement(query);
			    rs = pstmt.executeQuery();
			    
			    while(rs.next()) {
			    	
			    	int personId = rs.getInt(1);
			    	String name = rs.getString(2);
			    	String hp = rs.getString(3);
			    	String company = rs.getString(4);
			    	
			    	pList.add(new PhoneVo(personId, name, hp, company));
			    }
			    // 4.결과처리
			    			
			    			
			} catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			
			return pList;	
		}

		// 리스트에 저장하는 메서드
		
		public int insert(PhoneVo pv) {
			
			
			getConnection();
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query =   "insert		into person ";	
			   			   query += "values		(seq_person_id.nextval, ? , ? , ? ) ";
			   			   
			   			   pstmt = conn.prepareStatement(query);
			   			   pstmt.setString(1, pv.name);
			   			   pstmt.setString(2, pv.hp);
			   			   pstmt.setString(3, pv.company);
			   			   
			   			   count = pstmt.executeUpdate();
			    // 4.결과처리
			   			   System.out.println(count + "건 등록되었습니다.");
			   			   conn.commit();
			} catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			
			return count;
		}
	

		// 리스트의 값을 수정하는 메서드
		
		public int update(PhoneVo pv) {
			
			getConnection();
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
				String query = "update 		person ";
				query += 		 "	set   			name = ? , ";
				query += 		 "		  			hp = ? , ";
				query +=         " 					company = ? ";
				query += 		"where  		person_id = ? ";
			    
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, pv.name);
				pstmt.setString(2, pv.hp);
				pstmt.setString(3, pv.company);
				pstmt.setInt(4, pv.personId);
				
				count = pstmt.executeUpdate();
				
				// 4.결과처리
				System.out.println(count + "건이 수정되었습니다");
				conn.commit();
			} catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			return count;
		}
		
		//DB에서 삭제하는 메서드
		
		public int delete(int personId) {
			
			
			getConnection();
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query =   "delete		 from person ";	
			   			   query += "where		 person_id = ? ";
			   			   
			   			   pstmt = conn.prepareStatement(query);
			   			   pstmt.setInt(1, personId);
			   			
			   			   
			   			   count = pstmt.executeUpdate();
			    // 4.결과처리
			   			   System.out.println(count + "건 삭제되었습니다.");
			   			   conn.commit();
			} catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			
			return count;
		}
		
		
		//DB의 특정 키워드를 검색하는 메서드
		
		public List<PhoneVo> search(String keyword){
			
			getConnection();
			
			keyword = "%"+keyword;
			keyword += "%";
			 
			pList = new ArrayList<PhoneVo>();
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query = 	  " select			person_id, ";
			    			query +=  "						name,	"	;	
			    			query +=  "						hp,	"	;	
			    			query +=  "						company 	";
			    			query +=  "	from			person	";
			    			query +=  "	where 			name like ? or hp like ? or  company  like ? ";
			    					
			    pstmt = conn.prepareStatement(query);

			    
			    pstmt.setString(1,keyword); 
			    pstmt.setString(2,keyword); 
			    pstmt.setString(3,keyword); 
			    
			    rs = pstmt.executeQuery();
			    
			    while(rs.next()) {
			    	
			    	int personId = rs.getInt(1);
			    	String name = rs.getString(2);
			    	String hp = rs.getString(3);
			    	String company = rs.getString(4);
			    	
			    	pList.add(new PhoneVo(personId, name, hp, company));
			    }
			    // 4.결과처리
			    			
			    			
			} catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			
			return pList;	
		}
		
		
}
