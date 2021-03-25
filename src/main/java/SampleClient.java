import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StringType;


import java.io.File;
import java.util.*;
import java.util.Scanner;



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

    private static List<SimplePatient> getSimplePatientList(Bundle response, FhirContext fhirContext){
        List<SimplePatient> patientList = new ArrayList<>();
        IParser parser = fhirContext.newJsonParser();

        for(Bundle.BundleEntryComponent entryComponent: response.getEntry()){

            String stuff = parser.setPrettyPrint(true).encodeResourceToString(entryComponent.getResource());
            Patient parsed = parser.parseResource(Patient.class,stuff);

            String firstName = parsed.getName().get(0).getGiven().get(0).toString();
            String lastName = parsed.getName().get(0).getFamily();
            Date birthDate = parsed.getBirthDate();


            SimplePatient newPatient = new SimplePatient(birthDate,firstName,lastName);

            patientList.add(newPatient);

        }
//        String stuff = parser.setPrettyPrint(true).encodeResourceToString(response.getEntry().get(0).getResource());
//        Patient parsed = parser.parseResource(Patient.class, stuff);
//        System.out.println(parsed.getName().get(0).getFamily());

        patientList.sort(SimplePatient::compareTo);

        patientList.forEach(patient -> {
            System.out.println(patient.toString());
        });
        return patientList;




    }

    public static void main(String[] theArgs) {

//         Create a FHIR client
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        client.registerInterceptor(new LoggingInterceptor(false));

        IParser parser = fhirContext.newJsonParser();
        // Search for Patient resources
        Bundle response = client
                .search()
                .forResource("Patient")
                .where(Patient.FAMILY.matches().value("Smith"))
                .returnBundle(Bundle.class)
                .execute();

//        getSimplePatientList(response,fhirContext);
//        File file = new File(".");
//        for(String fileNames : file.list()) System.out.println(fileNames);

//        try {
//            File newFile = new File( "./last_names.txt");
//            Scanner myReader = new Scanner(newFile);
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                System.out.println(data);
//            }
//            myReader.close();
//        } catch (Exception e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }


    }

}
