import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IHttpRequest;
import ca.uhn.fhir.rest.client.api.IHttpResponse;
import ca.uhn.fhir.util.StopWatch;
import org.hl7.fhir.r4.model.DateTimeType;

import java.io.IOException;
import java.util.*;

@Interceptor
public class SimpleServerLoggingInterceptor implements IClientInterceptor {
    @Override
    public void interceptRequest(IHttpRequest iHttpRequest) {

    }

    @Override
    public void interceptResponse(IHttpResponse iHttpResponse) throws IOException {

//        StopWatch stopWatch = new StopWatch(DateTimeType.now());
//        stopWatch.
//        iHttpResponse

    }


}
