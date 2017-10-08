package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private FileUtils() {}

    public static void write(String text, String path, boolean append){
        try(FileWriter writer = new FileWriter(path, append))
        {
            writer.write(text);
            if (append)
                writer.append('\n');
            writer.flush();
            writer.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static List<String> load(String path){
        List<String> ids = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                ids.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public static String readLine(String path){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            return br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}