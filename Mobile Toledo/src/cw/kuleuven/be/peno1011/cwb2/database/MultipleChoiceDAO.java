package cw.kuleuven.be.peno1011.cwb2.database;

public class MultipleChoiceDAO {

	private static MultipleChoiceDAO multipleChoiceDAO;
	
	private MultipleChoiceDAO() {}
	
	public MultipleChoiceDAO getInstance() {
		if(multipleChoiceDAO==null){
			multipleChoiceDAO = new MultipleChoiceDAO();
		}
		return multipleChoiceDAO;
	}
	
	
}
