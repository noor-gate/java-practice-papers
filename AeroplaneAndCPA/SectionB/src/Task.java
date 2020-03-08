public class Task {

	public final static Task DUMMY_TASK = new Task("", 0);
	
	private String name;
	private int duration;
	
	public Task(String name, int duration) {
		this.name = name;
		this.duration = duration;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDuration() {
		return duration;
	}
}
