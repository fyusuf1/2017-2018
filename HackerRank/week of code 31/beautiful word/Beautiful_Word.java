import java.util.Scanner;

public class Beautiful_Word {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in); 
		String vowels = "aeiouy";
		char[] word = in.next().toCharArray();
		boolean bn = true;
		for(int x = 0; x < word.length-1; x++){
			if(vowels.indexOf(word[x]+"") > 0){
				if(vowels.contains(word[x+1]+"") ){
					bn = false;
				}
			}
			else if(word[x] == word[x+1]){
				bn = false;
			}
		}
		if(bn){
			System.out.println("Yes");
		}
		else{
			System.out.println("No");
		}
		in.close();
	}

}
