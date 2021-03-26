package com.simple.med;

import ca.uhn.fhir.rest.api.CacheControlDirective;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.junit.Test;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import  ca.uhn.fhir.rest.gclient.ICriterion;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class QueryPatientsFromServerTests {

    @Test
    public void testQueryPatientsWithLastName(){
        IGenericClient client = Mockito.mock(IGenericClient.class);
        Bundle response = Mockito.mock(Bundle.class);

        Mockito.when(client
                    .search()
                    .forResource(anyString())
                    .where((ICriterion<?>) any())
                    .returnBundle(any())
                    .cacheControl(any())
                    .execute())
                .thenReturn(response);

        QueryPatientsFromServer query = new QueryPatientsFromServer();

        query.queryPatientsWithLastName(client, "Smith", true);

        assertEquals(2, query.getResponsesList().size());
    }

}
