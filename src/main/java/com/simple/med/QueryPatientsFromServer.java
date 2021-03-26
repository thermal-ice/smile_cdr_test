package com.simple.med;

import ca.uhn.fhir.rest.api.CacheControlDirective;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class QueryPatientsFromServer {

    private List<Bundle> responsesList;

    public QueryPatientsFromServer(){
        this.responsesList = new ArrayList<>();
    }

    public void queryPatientsWithLastName(IGenericClient client, String lastName, boolean enableCaching){

        Bundle response = client
                    .search()
                    .forResource("Patient")
                    .where(Patient.FAMILY.matches().value(lastName))
                    .returnBundle(Bundle.class)
                    .cacheControl(new CacheControlDirective().setNoCache(!enableCaching))
                    .execute();

        responsesList.add(response);
    }

    public List<Bundle> getResponsesList(){
        return this.responsesList;
    }

    public void removeAllResponsesFromList(){
        responsesList.clear();
    }




}
