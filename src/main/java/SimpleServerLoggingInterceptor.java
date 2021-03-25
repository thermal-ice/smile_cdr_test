import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IHttpRequest;
import ca.uhn.fhir.rest.client.api.IHttpResponse;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import ca.uhn.fhir.util.StopWatch;
import org.hl7.fhir.r4.model.DateTimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.*;

@Interceptor
public class SimpleServerLoggingInterceptor implements IClientInterceptor {

//    private final Logger myLog;

    //List that keeps track of time taken to send requests to the server
    //First elem is the metadata, everything other elem are patient requests
    private final ArrayList<Long> timearr;


    public SimpleServerLoggingInterceptor(){
//        this.myLog = LoggerFactory.getLogger(LoggingInterceptor.class);
        timearr = new ArrayList<>();
    }
    @Override
    public void interceptRequest(IHttpRequest iHttpRequest) {
    }

    @Override
    public void interceptResponse(IHttpResponse iHttpResponse) throws IOException {

        timearr.add(iHttpResponse.getRequestStopWatch().getMillis());

//        this.myLog.info("Time in milliseconds" + String.valueOf(iHttpResponse.getRequestStopWatch().getMillis()));

//        StopWatch stopWatch = new StopWatch(DateTimeType.now());
//        stopWatch.
//        iHttpResponse

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


}
