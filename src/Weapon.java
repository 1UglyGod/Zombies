import java.util.ArrayList;

public class Weapon extends ArrayList<Bullet>{
	private int capacity = 10;
	
	@Override
	public boolean add(Bullet b){
		if(this.size() < capacity){
			return super.add(b);
		}else{
			return false;
		}
	}
	
	public void removeAmmo(){
		if(capacity > 0){
			capacity--;
		}
	}
	
	public int showCapacity(){
		return capacity;
	}
	
	public void addAmmo(int level){
		capacity += (level * 10);
	}
}
