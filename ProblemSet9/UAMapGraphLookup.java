import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class UAMapGraphLookup {
	
	public static final Vertex nil = new Vertex();
	
	public static void main(String[] args) throws IOException{
		
		int src = 0;
		int dest = 0;
		BufferedReader br = new BufferedReader(new FileReader(args[2]));
		
		String line = "";
		
		while((line = br.readLine()) != null) {
			
			
			String[] split = line.split(" ");
			src = Integer.parseInt(split[0]);
			dest = Integer.parseInt(split[1]);
			
			lookup(src,dest, args[0], args[1]);
			
			
		}
	}
	
	public static void lookup(int src, int dest, String weights, String pred) throws IOException {
		
		BufferedReader br1 = new BufferedReader(new FileReader(weights));
		BufferedReader br2 = new BufferedReader(new FileReader(pred));
		
		String line = "";
		int weight = 0;
		int count = 0;
		
		while((line = br1.readLine()) != null) {
			
			if(!line.equals("--------------------------------")) {
				String[] split = line.split(" ");
				if(Integer.parseInt(split[0]) == src && Integer.parseInt(split[1]) == dest) {
					weight = Integer.parseInt(split[2]);
					count++;
					break;
				}
			}
			count++;
		}
		
		if(weight == 0) {
			System.out.println("PATH UNDEFINED");
		} else {
			ArrayList<Vertex> vertices = new ArrayList<>();
			for(int i = 1; i < 4; i++) {
				vertices.add(new Vertex(i));
			}
	
			
			int index = (count - count % 3) + 1;
			count = 1;
			while((line = br2.readLine()) != null) {
				
				if(count >= index && count < index + 3) {
					
					String[] split = line.split(" ");
					
					if(Integer.parseInt(split[1]) != 0) {
						vertices.get(Integer.parseInt(split[1])-1).next = vertices.get(Integer.parseInt(split[0])-1);
					}
				}
				count++;
			}
			
			System.out.print("SOURCE: " + src + "\tDEST: " + dest + "\t\tWEIGHT: " + weight + "\tPath: ");
			Vertex v = vertices.get(src-1);
			count = 0;
			while(v.next != null && count < Math.abs(src-dest)) {//a janky fix but it works
				System.out.print(v.index + " => ");
				v = v.next;
				count++;
			}
			System.out.println(v.index);
		}
	}
	
	public static class Vertex {
		
		public Vertex(int index) {
			this.index = index;
		}
		
		public Vertex() {
			
		}
		

		Vertex pi;
		Vertex next;
		int index;
	}

}
