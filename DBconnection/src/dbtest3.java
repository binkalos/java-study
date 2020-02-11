import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbtest3 {
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
				
				String sql = "delete from tb_zone where zonenm='서울대학교' and email = 'seouluniv@naver.com'";
				pstmt = conn.prepareStatement(sql);
				String sql2 = "delete from tb_zone2 where seq_zone='1' and zone2nm = '실내체육관시설' and param2 = '5'";
				pstmt = conn.prepareStatement(sql2);
				String sql3 = "delete from tb_zone3 where seq_zone2='1' and  floor = 'B50'";
				pstmt = conn.prepareStatement(sql3);
				
				pstmt.executeUpdate();
				pstmt2.executeUpdate();
				pstmt3.executeUpdate();
			
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}	
	}//mainS
}
