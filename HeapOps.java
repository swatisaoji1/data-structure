import java.util.ArrayList;
import java.util.Scanner;

public class HeapOps {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int operations = sc.nextInt();
        ArrayList<Long> myHeap = new ArrayList<Long>();
        myHeap.add(Long.MIN_VALUE); // we will not use 0th index
       
        for(int i = 0; i < operations; i++){
            int op = sc.nextInt();
            long param = 1;
            if(op != 3){
            	param = sc.nextLong();	            	
            }
            process(myHeap, op, param);
            //printHeap(myHeap);// debug
        }
        sc.close();
	}

	private static void printHeap(ArrayList<Long> myHeap) {
		for(int i = 0; i < myHeap.size(); i++) {   
		    System.out.print(myHeap.get(i) + " ");
		}  
		System.out.println();
	}

	private static void process(ArrayList<Long> myHeap, int op, long param) {
		switch(op){
			case 1:
				add(myHeap, param);
				break;
			case 2:
				delete(myHeap, param);
				break;
			case 3:
				printMin(myHeap);
			
			
		}
	}

	private static void printMin(ArrayList<Long> myHeap) {
		// TODO Auto-generated method stub
		System.out.println(myHeap.get(1));
		
	}

	private static void delete(ArrayList<Long> myHeap, long param) {
		// TODO Auto-generated method stub
		int index = myHeap.indexOf(param);
		// if last element -> just remove it
		if(index == myHeap.size()-1){
			myHeap.remove(index);
			return;
		}
		int lastElement = myHeap.size()-1;
		if(index != -1){
			myHeap.set(index, myHeap.get(lastElement));
			myHeap.remove(lastElement);
			if(index > 1 && myHeap.get(index) < myHeap.get(index/2)){
				propagateUp(myHeap, index);
			}else{
				propagateDown(myHeap, index);
			}
		}
		
	}

	private static void add(ArrayList<Long> myHeap, long param) {
		// TODO Auto-generated method stub
		myHeap.add(param);
		int index = myHeap.size()-1;
		propagateUp(myHeap, index);
		
	}

	private static void propagateUp(ArrayList<Long> myHeap, int index) {
		// TODO Auto-generated method stub
		while(index >= 1 && myHeap.get(index) < myHeap.get(index/2)){
			swap(myHeap, index, index/2);
			index = index/2;
		}	
	}

	@SuppressWarnings("unused")
	private static void propagateDown(ArrayList<Long> myHeap, int index) {
		// TODO Auto-generated method stub
		int size = myHeap.size();
		int child = index * 2;
		int smallest;
		boolean swapped = true;
		while(child < size && swapped){
			smallest = child;
			if(child + 1 < size && myHeap.get(child) > myHeap.get(child + 1)){
				smallest = child + 1;
			}
			if(myHeap.get(index) > myHeap.get(smallest)){
				swap(myHeap, index, smallest);
				index = smallest;
				child = index * 2;
			}else{
				swapped = false;
			}
		}
	}
	private static void swap(ArrayList<Long> myHeap, int index, int i) {
		Long temp = myHeap.get(index);
		myHeap.set(index, myHeap.get(i));
		myHeap.set(i, temp);
	}

}
