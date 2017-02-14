/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurssidan http://www.csc.kth.se/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

public class Main {

  public static List<String> readWordList(BufferedReader input) throws IOException {
    LinkedList<String> list = new LinkedList<String>();
    while (true) {
      String s = input.readLine(); //read the input till # occurs
      if (s.equals("#"))
        break;
      list.add(s);
    }
    return list;
  }

  public static void main(String args[]) throws IOException {
    //    long t1 = System.currentTimeMillis();
    List<String> processedWords = new ArrayList<String>();
    List<int [][]> wordsMatrixList = new ArrayList<int[][]>();
    HashMap hm = new HashMap();

    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));//InputStreamReader reads bytes and decodes them into characters using a specified charset.
    // Säkrast att specificera att UTF-8 ska användas, för vissa system har annan
    // standardinställning för teckenkodningen.
	//The safest way to specify that UTF-8 is used, for some systems have different default character encoding.
    List<String> wordList = readWordList(stdin);
    String word;
    while ((word = stdin.readLine()) != null) {
      ClosestWords closestWords = new ClosestWords(word, wordList); //(Misspelledword, word from dictionary)
      System.out.print(word + " (" + closestWords.getMinDistance() + ")");
      for (String w : closestWords.getClosestWords())
        System.out.print(" " + w);
      processedWords.add(word);

      int tempMatrix[][] = new int[word.length()][word.length()];
      tempMatrix = closestWords.wordMatrixList.get(0);
      System.out.println("Matrix exclemtnr " + tempMatrix[0][1]);
      hm.put(word, tempMatrix);

      System.out.println("The size of processed words " + processedWords.size());
      for (String givenWords : processedWords) {
        System.out.println("The word is  " + hm.get(givenWords));
      }
      System.out.println(hm.get("Masks"));

     //wordsMatrixList.add(closestWords.wordsMatrixList); //TODO: Hash mapping has to be done !5



    }
    System.out.println("The size of processed words " + processedWords.size());
    for (String givenWords : processedWords) {
      System.out.println("The word is  " + hm.get(givenWords));
    }
    System.out.println();


    //    long tottime = (System.currentTimeMillis() - t1);
    //    System.out.println("CPU time: " + tottime + " ms");

  }
}
