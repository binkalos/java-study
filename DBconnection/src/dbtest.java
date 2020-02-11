import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dbtest {
	public static void main(String args[]){
		
		 Connection conn = null;//Drivermanager에 등록된 각 드라이버를 getConnection메소드로 식별해서 url 식별자와 같은 것 찾아서 mapping
		 Statement stmt = null;//sql쿼리 생성, 실행
		 PreparedStatement pstmt = null;//statement클래스의 기능향상(코드 안전성 굿, 매개변수 set으로 코드량 증가)
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
			
				//tbzone 학교, 학교이름 tbzone2 학교에 있는 건물단위, tbzone3 건물 안에 있는 강의실 단위
				
				//1.한성대 시설리스트 이름이랑 시설(join해야함 시설나오는 zone2랑) 뽑아오기
				String sql = "select t1.zonenm,t2.zone2nm from tb_zone as t1 join tb_zone2 as t2 on t1.seq = t2.seq where t1.zonenm in(?)";
				pstmt = conn.prepareStatement(sql);//PrepareStatement객체 생성
				
				//pstmt.setString(1,"first");//where절 first일때 
				//1.
				pstmt.setString(1, "한성대학교");
				
				
				//1.
				rs = pstmt.executeQuery();//PrepareStatement객체 생성 후 executeQuery()메소드 사용해 쿼리 처리
				
				//많은 리스트들을 배열에 담아서 출력하기 위해 사용
				List<String[]> tbList = new ArrayList<String[]>();
				//1.
				while(rs.next()) {
					 String[] arrStr = {rs.getString("zonenm"),rs.getString("zone2nm")};
					 tbList.add(arrStr);
				}
				for(int i = 0;i<tbList.size();i++) {
					System.out.print(tbList.get(i)[0]);
					System.out.print(" | ");
					System.out.print(tbList.get(i)[1]);
				}
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}	
	}//mainS
}
