import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;



public class FileHandler
{
  private Queue<Question> questions;
  
  public Queue<Question> getQuestions() {
    if (this.questions == null) {
      this.questions = new LinkedList<>();
    }
    return this.questions;
  }

  
  public FileHandler(String filePath) throws FileNotFoundException { 
	  Exception exception1 = null, exception2 = null; 
	  }
}
