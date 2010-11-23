	package cw.kuleuven.be.peno1011.cwb2.database;

	import cw.kuleuven.be.peno1011.cwb2.controller.MainController;
	import cw.kuleuven.be.peno1011.cwb2.model.Question;
	import cw.kuleuven.be.peno1011.cwb2.model.User;
	import cw.kuleuven.be.peno1011.cwb2.model.Lecture;

	public class QuestionDAO {
		
		private static QuestionDAO QuestionDAO;
		
		// Singleton
		private QuestionDAO(){
			
		}
		
		public static QuestionDAO getInstance() {
			if (QuestionDAO == null){
				QuestionDAO = new QuestionDAO();
			}
			return QuestionDAO;
		}
		
		//TO DO: opslaan i/database
		public boolean insert(String message, Lecture lecture) {
			User questioner  = MainController.getUser();
			Question newQuestion = new Question(questioner, lecture, message);
			boolean inserted=false;
			return inserted;
		}
		//TO DO
		public boolean delete(Question question) {
			boolean deleted=false;
			return deleted;
		}
	}


