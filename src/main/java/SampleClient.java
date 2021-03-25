import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.CacheControlDirective;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;


import java.io.File;
import java.util.*;


/*
Setting no caching

Bundle response = client
   .search()
   .forResource(Patient.class)
   .returnBundle(Bundle.class)
   .cacheControl(new CacheControlDirective().setNoCache(true)) // <-- add a directive
   .execute();
 */
public class SampleClient {



    public static void main(String[] theArgs) {

//         Create a FHIR client
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        client.registerInterceptor(new LoggingInterceptor(false));

//        SimpleServerLoggingInterceptor interceptor = new SimpleServerLoggingInterceptor();

//        client.registerInterceptor(interceptor);


        IParser parser = fhirContext.newJsonParser();
        // Search for Patient resources
//        Bundle response = client
//                .search()
//                .forResource("Patient")
//                .where(Patient.FAMILY.matches().value("Smith"))
//                .returnBundle(Bundle.class)
//                .cacheControl(new CacheControlDirective().setNoCache(true))
//                .execute();
        Bundle response = client
                .search()
                .forResource("Patient")
                .where(Patient.FAMILY.matches().value("SMITH"))
                .returnBundle(Bundle.class)
                .execute();
        Bundle response2 = client
                .search()
                .forResource("Patient")
                .where(Patient.FAMILY.matches().value("Brown"))
                .returnBundle(Bundle.class)
                .execute();
        Bundle response3 = client
                .search()
                .forResource("Patient")
                .where(Patient.FAMILY.matches().value("Johnson"))
                .returnBundle(Bundle.class)
                .execute();
//
//        System.out.println(interceptor.getTimeArr());

//        System.out.println(interceptor.getTimeArr());

        SimpleFileReader fr = new SimpleFileReader("src/main/Readfiles/last_names.txt");
        System.out.println(fr.getLastNamesList());

//        SimplePatient.getSimplePatientList(response,fhirContext);
//        getSimplePatientList(response,fhirContext);
//        File file = new File(".");
//        for(String fileNames : file.list()) System.out.println(fileNames);



    }

}
