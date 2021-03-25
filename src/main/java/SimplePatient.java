import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IHttpRequest;
import ca.uhn.fhir.rest.client.api.IHttpResponse;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SimplePatient implements Comparable<SimplePatient> {

    private final Date birthDate;
    private final String firstName;
    private final String lastName;

    public SimplePatient(Date birthDate, String firstName, String lastName){
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static List<SimplePatient> getSimplePatientList(Bundle response, FhirContext fhirContext){
        List<SimplePatient> patientList = new ArrayList<>();
        IParser parser = fhirContext.newJsonParser();

        for(Bundle.BundleEntryComponent entryComponent: response.getEntry()){

            String stringToParse = parser.setPrettyPrint(true).encodeResourceToString(entryComponent.getResource());
            Patient parsed = parser.parseResource(Patient.class,stringToParse);

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

//        patientList.forEach(patient -> {
//            System.out.println(patient.toString());
//        });
        return patientList;

    }

    @Override
    public String toString(){
        //Can use stringBuilder instead, but not necessary
        return "The patient " + firstName + " " + lastName+ " was born on " +
                birthDate;
    }



    @Override
    public int compareTo(SimplePatient patient) {
        //Comparing based on first name
        if (firstName == null || patient.getLastName() == null) {
            return 0;
        }
        return firstName.compareTo(patient.getFirstName());
    }

}
