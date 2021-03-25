import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IHttpRequest;
import ca.uhn.fhir.rest.client.api.IHttpResponse;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;

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
