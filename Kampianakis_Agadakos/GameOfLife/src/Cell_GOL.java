import java.util.Enumeration;



public class Cell_GOL {
	
	private boolean curr_state;
	
	private boolean next_state;
	
	public boolean isCurr_state() {
		return curr_state;
	}

	public void setCurr_state(boolean currState) {
		curr_state = currState;
	}

	public boolean isNext_state() {
		return next_state;
	}

	public void setNext_state(boolean nextState) {
		next_state = nextState;
	}

	
	
	
	
	public Cell_GOL(boolean curr_state){
		
		this.curr_state = curr_state;
		this.next_state = false;
		
	}

	public void born(){
		
		next_state = true;
		
	}
	
	public void die(){
		
		this.next_state = false;
		
	}
	
	public void survive(){
		
		this.next_state = true;
		
	}
	
	public void apply(){
		
		this.curr_state = this.next_state;
		
	}
	
}
