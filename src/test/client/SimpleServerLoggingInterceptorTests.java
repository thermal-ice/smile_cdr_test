package client;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class SimpleServerLoggingInterceptorTests {
    SimpleServerLoggingInterceptor loggingInterceptor;

    @Before
    public void setUp(){
        loggingInterceptor = new SimpleServerLoggingInterceptor();
    }

    @Test(expected = NoSuchElementException.class)
    public void invalidGetMetaDataResponseTime() {
        loggingInterceptor.getMetaDataResponseTime();
    }

    @Test(expected = NoSuchElementException.class)
    public void invalidGetPatientResponseTimes() {
        loggingInterceptor.getPatientResponseTimes();
    }

    @Test(expected = NoSuchElementException.class)
    public void invalidDeleteAllPatientsFromList() {
        loggingInterceptor.deleteAllPatientsFromList();
    }


}
