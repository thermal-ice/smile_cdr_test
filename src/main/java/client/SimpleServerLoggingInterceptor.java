package client;

import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IHttpRequest;
import ca.uhn.fhir.rest.client.api.IHttpResponse;


import java.io.IOException;
import java.util.*;

@Interceptor
public class SimpleServerLoggingInterceptor implements IClientInterceptor {


    //List that keeps track of time taken to send requests to the server
    //First elem is the metadata, everything other elem are patient requests
    private final ArrayList<Long> timearr;


    public SimpleServerLoggingInterceptor(){
        timearr = new ArrayList<>();
    }
    @Override
    public void interceptRequest(IHttpRequest iHttpRequest) {
    }

    @Override
    public void interceptResponse(IHttpResponse iHttpResponse) throws IOException {

        timearr.add(iHttpResponse.getRequestStopWatch().getMillis());


    }

    public long getMetaDataResponseTime() throws NoSuchElementException {
        if(timearr.isEmpty())
            throw new NoSuchElementException();
        return timearr.get(0);
    }

    public List<Long> getTimeArrFull(){
        return timearr;
    }

    public List<Long> getPatientResponseTimes()throws NoSuchElementException{
        if(timearr.size()<=2)
            throw new NoSuchElementException();

        return timearr.subList(1,timearr.size());
    }

    public void deleteAllPatientsFromList() throws NoSuchElementException{
        if(timearr.size() < 2)
            throw new NoSuchElementException();

        timearr.subList(1, timearr.size()).clear();
    }


}
