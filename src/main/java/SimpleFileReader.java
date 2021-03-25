
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleFileReader {

    private final List<String> lastNamesList;

    public SimpleFileReader(String pathToNamesFile){

        this.lastNamesList = new ArrayList<>();

        try{
            Path path = Paths.get(pathToNamesFile);
            Scanner scanner = new Scanner(path);
            //Reading each line from the scanner

            while(scanner.hasNextLine()){
                //Adding each line into the list
                String line = scanner.nextLine();
                lastNamesList.add(line);
            }

            scanner.close();

        }catch (Exception e){
            System.out.println("Could not find the file");
            e.printStackTrace();
        }


    }


    public List<String> getLastNamesList(){
        return this.lastNamesList;
    }
}
