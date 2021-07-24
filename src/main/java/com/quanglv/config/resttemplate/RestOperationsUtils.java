package com.quanglv.config.resttemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestOperations;

@Component
@RequiredArgsConstructor
public class RestOperationsUtils {

    @Autowired
    private RestOperations restOperations;

    /**
     * string url
     * @param hostName
     * @param apiName
     * @return StringBuilder
     */
    private String url(String hostName, String apiName){
        StringBuilder s = new StringBuilder();
        s.append("http://");
        s.append(hostName);
        s.append("/");
        s.append(apiName);
        return s.toString();
    }

    /**
     * executeCallApi : execute call api
     * @param apiName
     * @param httpMethod
     * @param request
     * @param responseType
     * @param <T>
     * @return <T>
     */
    public <T> T executeCallApi(String apiName, HttpMethod httpMethod, Object request,
                                Class<T> responseType, String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> entity = new HttpEntity<>(request, headers);
            // RestTemplate

            // Call API
            ResponseEntity<T> response = restOperations.exchange(Constants.URL_FRONT_END + apiName,
                    httpMethod, entity, responseType);

            // return response
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            throw e;
        }
    }

    public <T> T executeCallApi(String hostName, String apiName, HttpMethod httpMethod, Object request,
                                Class<T> responseType, String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            if(token != null && !token.isEmpty()) headers.set("Authorization", "Bearer " + token);

            HttpEntity<?> entity = new HttpEntity<>(request, headers);
            // RestTemplate

            // Call API
            ResponseEntity<T> response = restOperations.exchange(url(hostName, apiName), httpMethod, entity, responseType);

            // return response
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            throw e;
        }
    }

    public <T> T executeCallApiFormUrlencoder(String hostName, String apiName, HttpMethod httpMethod, MultiValueMap<String, Object> request,
                                              Class<T> responseType, String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            if(token != null && !token.isEmpty()) headers.add("Authorization", "Bearer " + token);

            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(request, headers);
            // RestTemplate

            // Call API
            ResponseEntity<T> response = restOperations.exchange(url(hostName, apiName), httpMethod, entity, responseType);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            throw e;
        }
    }

    /**
     * executeCallPublicApi
     * @param apiName
     * @param httpMethod
     * @param request
     * @param responseType
     * @param <T>
     * @return T
     */
    public <T> T executeCallPublicApi(String hostName, String apiName, HttpMethod httpMethod, Object request,
                                      Class<T> responseType) {
        return executeCallApi(hostName, apiName, httpMethod, request, responseType, null);
    }
}
