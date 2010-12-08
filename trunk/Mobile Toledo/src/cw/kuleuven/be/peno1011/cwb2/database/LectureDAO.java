package cw.kuleuven.be.peno1011.cwb2.database;

public class LectureDAO {
	private static LectureDAO lectureDAO;
	
	private LectureDAO() { }
	
	public LectureDAO getInstance() {
		if(lectureDAO==null){
			lectureDAO = new LectureDAO();
		}
		return lectureDAO;
	}
	
	
	
}
