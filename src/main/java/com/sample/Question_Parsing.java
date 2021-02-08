package com.sample;

import com.sample.utils.CSV;
import com.sample.utils.Utils;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.logging.log4j.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Question_Parsing {


    public static String question_parsing(String input_question) throws IOException {
//        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
        //Check the list of formation/group/member name
        String formation_basic = "/Users/alice/Downloads/LiquorStoreServlet-master/src/main/java/com/sample/data/Test_Abox.csv";
        String well_info = "/Users/alice/Downloads/LiquorStoreServlet-master/src/main/java/com/sample/data/well-location.csv";
        List<String[]> lines = CSV.read(formation_basic, ",/t,");
        List<String[]> wells = CSV.read(well_info, ",");

        String result="";

        //no need query to answer this question
        if(input_question.contains("stratigraphy")){
            result = "Stratigraphy is a geology study involved the study of the rock layer(strata). It includes three main subfields, lithostratigraphy, biostratigraphy and chronostratigraphy.";
        }
        else{
            String well_num="";
            String subject="";

            for (String[] well: wells ){
                String num=well[0];
                if(input_question.contains(num)){
                    well_num=num;
                    break;
                }
            }

            for (String[] line : lines) {
                String formation_name = line[3];
                formation_name= Utils.cleanURI(formation_name);
                if(input_question.contains(formation_name.split("_")[0])){
                    subject=formation_name;
                    break;
                }

            }
            String Query_String="";
            try{
                Query_String=Query_Selection.query_selection(input_question,subject, well_num);
            } catch (Exception e) {
                result="We can't understand your question";
            }

            if(!Query_String.equals("")){
                //Initialize the connection for querying the graph
                InputStream in = new FileInputStream(new File("/Users/alice/Downloads/LiquorStoreServlet-master/src/main/java/com/sample/data/Abox_output.rdf"));

                // Create an empty in‑memory model and populate it from the graph
                Model model = ModelFactory.createMemModelMaker().createModel("model");
                model.read(in,null); // null base URI, since model URIs are absolute
                in.close();



                //execute the query
                Query query = QueryFactory.create(Query_String);

                // Execute the query and obtain results
                QueryExecution qe = QueryExecutionFactory.create(query, model);
                ResultSet results = qe.execSelect();

                result = ResultSetFormatter.asText(results).split(" | ",3)[2].replace("=","").replace("-","").replace("-","").replace("\"","").replace("|","").replace("\"","");

                qe.close(); // Important ‑ free up resources used running the query

            }else{
                result="We can't understand your question";
            }



        }
        return result;


    }
}