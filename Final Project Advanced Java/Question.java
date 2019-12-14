
public class Question
{
	private String text1;
	private String text2;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;

	public Question(String text1, String text2, String option1, String option2, String option3, String option4) {
		setText1(text1);
		setText2(text2);
		setOption1(option1);
		setOption2(option2);
		setOption3(option3);
		setOption4(option4);
	}

	public void setText1(String text1) { 
		this.text1 = text1;
		}

	public void setText2(String text2) { 
		this.text2 = text2; 
		}

	public void setOption1(String option1) { 
		this.option1 = option1; 
		}

	public void setOption2(String option2) {
		this.option2 = option2; 
		}

	public void setOption3(String option3) { 
		this.option3 = option3; 
		}

	public void setOption4(String option4) { 
		this.option4 = option4; 
		}

	public void setAnswer(String answer) { 
		this.answer = answer;
		}

	public String getText1() {
		return this.text1; 
		}

	public String getText2() { 
		return this.text2;
		}

	public String getOption1() { 
		return this.option1;
		}

	public String getOption2() {
		return this.option2; 
		}

	public String getOption3() {
		return this.option3;
		}

	public String getOption4() {
		return this.option4; 
		}

	public String getAnswer() { 
		return this.answer; 
		}

	public String toString() { 
		return String.valueOf(this.text1) + " + " + this.text2;
		}
}