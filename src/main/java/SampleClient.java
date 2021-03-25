import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;


import java.util.*;


public class SampleClient {

    public static double getAverageOfList(List<Long> longList){

        double sum = 0.0;
        for(long elem: longList){
            sum += (double) elem;
        }
        return sum / (double) longList.size();
    }



    public static void main(String[] theArgs) throws InterruptedException {

//         Create a FHIR client
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
//        client.registerInterceptor(new LoggingInterceptor(false));

        SimpleServerLoggingInterceptor interceptor = new SimpleServerLoggingInterceptor();
        client.registerInterceptor(interceptor);


        // Search for Patient resources
//        Bundle response = client
//                .search()
//                .forResource("Patient")
//                .where(Patient.FAMILY.matches().value("SMITH"))
//                .returnBundle(Bundle.class)
//                .execute();


//
//        System.out.println(SimplePatient.getSimplePatientList(response,fhirContext));



        //Intermediate task:
        SimpleFileReader fr = new SimpleFileReader("src/main/Readfiles/last_names.txt");


        QueryPatientsFromServer queryObj = new QueryPatientsFromServer();

        System.out.println("Running first query of lastnames");
        for(String lastName : fr.getLastNamesList()){
            queryObj.queryPatientsWithLastName(client,lastName,true);
        }
        System.out.printf("The average response time is %.2f ms\n", getAverageOfList(interceptor.getPatientResponseTimes()));
        interceptor.deleteAllPatientsFromList();
        queryObj.removeAllResponsesFromList();



        System.out.println("Running second query of lastnames");
        for(String lastName : fr.getLastNamesList()){
            queryObj.queryPatientsWithLastName(client,lastName,true);
        }
        System.out.printf("The average response time is %.2f ms\n", getAverageOfList(interceptor.getPatientResponseTimes()));
        interceptor.deleteAllPatientsFromList();
        queryObj.removeAllResponsesFromList();



        System.out.println("Running third query of lastnames");
        for(String lastName : fr.getLastNamesList()){
            queryObj.queryPatientsWithLastName(client,lastName,false);
        }
        System.out.printf("The average response time is %.2f ms\n", getAverageOfList(interceptor.getPatientResponseTimes()));
        interceptor.deleteAllPatientsFromList();
        queryObj.removeAllResponsesFromList();





    }

}
