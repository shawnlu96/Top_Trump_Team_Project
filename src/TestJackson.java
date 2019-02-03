import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import commandline.TTController;
import commandline.TTModel;
import commandline.TTView;


public class TestJackson {
    public static void main(String[] args) throws ParseException, IOException{
        TTModel model = new TTModel();
        TTView view = new TTView(model);
        TTController controller = new TTController(model,view);

        ObjectMapper mapper = new ObjectMapper();

        //model to json
        String json1 = mapper.writeValueAsString(model);
//        String json2 = mapper.writeValueAsString(view);
        String json3 = mapper.writeValueAsString(controller);
        System.out.println(json1);
        System.out.println(json2);
        System.out.println(json3);
    }
}
