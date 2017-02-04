/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurssidan http://www.csc.kth.se/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class ClosestWords {
  LinkedList<String> closestWords = null;

  int closestDistance = -1;
  int matrixPreChecker = -1;
  int count=1; 
  int res,i,j;
  int addLetter;
  int deleteLetter;	
  int result;
  List<int [][]> wordMatrixList = new ArrayList<int[][]>();
  List<String> misspelledWords = new ArrayList<String>();

  int partDist(String w1, String w2, int w1len, int w2len) {
      int M[][]= new int [w1len+1][w2len+1]; //To include null string.
	 // if(w1len==0)
		 for(i=0;i<=w2len;i++)
		  {
			  M[0][i]=i;
			  
		  }  
	  //if w2Len=o
	  //if(w2len==0)
	  
		 for(i=0;i<=w1len;i++)
		  {
			  M[i][0]=i;
		  }  
	  
		for(i=1;i<=w1len;i++)
		{
			for(j=1;j<=w2len;j++)
			{
				//substitution if the letter is not same and if voth the letter are same we do nothing
				if(w1.charAt(i - 1) == w2.charAt(j - 1))
				{
				    res = M[i-1][j-1];
				    M[i][j]=res;
				}
				else
				{
				    res=M[i-1][j-1] + 1;
    				//addletter
    				addLetter=M[i-1][j] + 1;
    				result = Math.min(res,addLetter);
    					
    				deleteLetter = M[i][j-1] + 1;
    				res=Math.min(result,deleteLetter);
    				
    				M[i][j]=res;
				}
				
				
			}

		}
		wordMatrixList.add(M);
        misspelledWords.add(w1);
		return res;
  }

  //int Distance(String w1, String w2) {//misspelled word and dictionary word Calculate the minimum distance
	  
	 
    //return partDist(w1, w2, w1.length(), w2.length()); //partDist(ma,maska,2,5)
     
  //}

  public ClosestWords(String w, List<String> wordList) {//input word and list of words passed as parameter
    int dist = 0;
    //if(matrixPreChecker != -1) {
        //TODO: String compare by character level!
      //  for (String s : misspelledWords) {
        //    for (int i = 0; i<w.length(); i++) {

          //  }

       // }


   // }
    for (String s : wordList) {
        int size = Math.abs(s.length() - w.length());
        if(dist >= size || dist==0)
        {
          dist = partDist(w, s,w.length(),s.length());
          //System.out.println("d(" + w + "," + s + ")=" + dist);
          if (dist < closestDistance || closestDistance == -1) {
            closestDistance = dist;
            closestWords = new LinkedList<String>();
            closestWords.add(s);
          }
          else if (dist == closestDistance)
            closestWords.add(s);
        }
    }
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}
