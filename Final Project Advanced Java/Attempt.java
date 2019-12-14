public class Attempt
{
	private String choice;
	private long start;
	private long end;
	private Question question;
	private int score;

	public void setQuestion(Question question) { 
		this.question = question; 
		}

	public Question getQuestion() {
		return this.question; 
		}

	public String getChoice() {
		return this.choice;
		}

	public void setChoice(String choice) {
		this.choice = choice;
		}

	public void setStart(long start) {
		this.start = start;
		}

	public void setEnd(long end) {
		this.end = end;
		}

	public long getStart() { 
		return this.start; 
		}

	public long getEnd() { 
		return this.end;
		}

	public int getScore() { 
		return this.score; 
	}

	public void setScore(int score) { 
		this.score = score; 
		}
}

