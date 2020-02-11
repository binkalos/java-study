import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbtest2 {
	public static void main(String args[]){
		
		 Connection conn = null;//Drivermanager에 등록된 각 드라이버를 getConnection메소드로 식별해서 url 식별자와 같은 것 찾아서 mapping
		 Statement stmt = null;//sql쿼리 생성, 실행
		 PreparedStatement pstmt = null, pstmt2 = null, pstmt3 = null;//statement클래스의 기능향상(코드 안전성 굿, 매개변수 set으로 코드량 증가)
		 ResultSet rs = null;//executeQery()메소드를 결과로 rs결과 반환(원하는 데이터 추출)
		
			try {
				//DB 종료에 따른 JDBC DRIVER 클래스
				Class.forName("com.mysql.jdbc.Driver");
				//DB접속 url
				String url="jdbc:mysql://192.168.0.100:10633/backup_1212?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
				//DB접속 ID
				String user = "test";
				//DB접속 PW
				String password = "0725";
				//접속 정보로 JDBC연결 커넥션 생성
				conn = DriverManager.getConnection(url, user, password);
				
				//3.delete 2번 where 
				//tbzone 학교, 학교이름 tbzone2 학교에 있는 건물단위, tbzone3 건물 안에 있는 강의실 단위
				
			
				//2.inert 대학  건물 강의실 전부 넣기
				String sql = "insert into tb_zone (zonenm, zonenmEn, available, seq_area, type, addr, email) values (?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				String sql2 = "insert into tb_zone2 (seq_zone, zone2nm, type, param,param2)values (?, ?, ?, ?, ?)";
				pstmt2 = conn.prepareStatement(sql2);
				String sql3 = "insert into tb_zone3 (seq_zone2, id, floor, zone3nm, available, payable, category, price, seq_zone3_type, param)";
				sql3 += "values (?,?,?,?,?,?,?,?,?,?)";
				pstmt3 = conn.prepareStatement(sql3);
				//2.
				pstmt.setString(1,"서울대학교");
				pstmt.setString(2,"SEOUL UNIVERSITY");
				pstmt.setString(3,"0");
				pstmt.setString(4,"1");
				pstmt.setString(5,"2");
				pstmt.setString(6,"08826 서울시 관악구 관악로 1 서울대학교");
				pstmt.setString(7,"seouluniv@naver.com");
				
				
				pstmt2.setString(1, "1");
				pstmt2.setString(2, "실내체육관시설");
				pstmt2.setString(3, "1");
				pstmt2.setString(4, "2");
				pstmt2.setString(5, "5");
				
				pstmt3.setString(1, "1");
				pstmt3.setString(2, "300");
				pstmt3.setString(3, "B50");
				pstmt3.setString(4, "실내체육관");
				pstmt3.setString(5, "0");
				pstmt3.setString(6, "0");
				pstmt3.setString(7, "0");
				pstmt3.setString(8, "30000");
				pstmt3.setString(9, "1");
				pstmt3.setString(10, "-1");
				//2.
				int rs2 = pstmt.executeUpdate();
				int rs3 = pstmt2.executeUpdate();
				int rs4 = pstmt3.executeUpdate();
				
				System.out.println("rs2 : " + rs2);
				System.out.println("rs3 : " +rs3);
				System.out.println("rs4 : " +rs4);
				//rs2.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}	
	}//mainS
}
