import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Base64;

public class ImageEncoder {


    public static ArrayList<String> encodeImage(String ImagePath) throws Exception{
        String arr [] = ImagePath.split("\\\\");
        ArrayList<String> r = new ArrayList<String>() ;
        FileInputStream imageStream = new FileInputStream(ImagePath);
        byte[] myImageInBytes= imageStream.readAllBytes();
        String myImageInString = Base64.getEncoder().encodeToString(myImageInBytes);

        r.add(myImageInString); r.add(arr[arr.length-2] + "\\"+arr[arr.length-1]);
        imageStream.close();
        return r;
    }

    public static void decodeImage(ArrayList<String> r,String savingPath) throws Exception{


        byte[] myImage =  Base64.getDecoder().decode(r.get(0));
        FileOutputStream fileOutputStream = new FileOutputStream(savingPath+"\\"+r.get(1));
        fileOutputStream.write(myImage);
        fileOutputStream.close();

    }




}
