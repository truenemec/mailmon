package com.bss.mailmon.rest;

import static com.bss.mailmon.service.ServiceConstants.APPLICATION_FORM_URLENCODED_VALUE;
import static com.bss.mailmon.service.ServiceConstants.APPLICATION_JSON_VALUE;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING;
import static java.lang.Boolean.TRUE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;

import com.bss.mailmon.service.exceptions.NoContentException;
import com.bss.mailmon.service.exceptions.ServiceException;
import com.bss.mailmon.service.user.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class AbstractController {

    private Client client;
    private String httpAuth;
    private String url = "http://localhost:8080/mailmon-service";

    public SimpleEntry<String, String> queryParameter(String name, Object value) {
        return new SimpleEntry<String, String>(name, String.valueOf(value));
    }

    private Client getClient() {
        if (client == null) {
            ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(FEATURE_POJO_MAPPING, TRUE);
            client = Client.create(clientConfig);
        }
        return client;
    }

    private <T> T body(ClientResponse response, Class<T> returnType) {
        return response.getEntity(returnType);
    }

    protected <T> T requestGet(Class<T> responseClass, String path) throws ServiceException, NoContentException {
        return requestGet(responseClass, path, APPLICATION_JSON_VALUE, (List<SimpleEntry<String, String>>) null, null);
    }

    protected <T> T requestGet(Class<T> responseClass, String path, Map<String, String> headers)
            throws ServiceException, NoContentException {
        return requestGet(responseClass, path, APPLICATION_JSON_VALUE, (List<SimpleEntry<String, String>>) null,
                headers);
    }

    protected <T> T requestGet(Class<T> responseClass, String path, String accept) throws ServiceException,
            NoContentException {
        return requestGet(responseClass, path, accept, (List<SimpleEntry<String, String>>) null, null);
    }

    protected <T> T requestGet(Class<T> responseClass, String path, List<SimpleEntry<String, String>> queryParams)
            throws ServiceException, NoContentException {
        return requestGet(responseClass, path, APPLICATION_JSON_VALUE, queryParams, null);
    }

    protected <T> T requestGet(Class<T> responseClass, String path, String accept,
            List<SimpleEntry<String, String>> queryParams) throws ServiceException, NoContentException {
        return requestGet(responseClass, path, accept, queryParams, null);
    }

    protected <T> T requestGet(Class<T> responseClass, String path, String accept,
            List<SimpleEntry<String, String>> queryParams, Map<String, String> headers) throws ServiceException,
            NoContentException {
        Client client = getClient();
        WebResource target = client.resource(url);
        if (!isNullOrEmpty(path)) {
            target = target.path(path);
        }

        if (queryParams != null && queryParams.size() > 0) {
            for (SimpleEntry<String, String> entry : queryParams) {
                target = target.queryParam(entry.getKey(), entry.getValue());
            }
        }

        try {
            Builder builder = target.type(APPLICATION_FORM_URLENCODED_VALUE)
                    .accept(MediaType.valueOf(accept), MediaType.TEXT_PLAIN_TYPE).acceptLanguage("ru")
                    .header("Authorization", "Basic " + httpAuth);
            if (headers != null) {
                for (Entry<String, String> item : headers.entrySet()) {
                    builder = builder.header(item.getKey(), item.getValue());
                }
            }
            ClientResponse response = builder.get(ClientResponse.class);
            if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
                throw new NoContentException(body(response, String.class));
            } else if (response.getStatus() != HttpStatus.OK.value()) {
                String message = null;
                if (response.hasEntity()) {
                    message = response.getEntity(String.class);
                }
                throw new ServiceException(message);
            }

            return body(response, responseClass);

        } catch (ClientHandlerException e) {
            throw new ServiceException(e);
        }
    }

    protected void requestPost(Object value, String path) throws ServiceException {
        requestPost(value, path, APPLICATION_JSON, APPLICATION_JSON_TYPE, false);
    }

    protected long requestPost(Object value, String path, boolean needId) throws ServiceException {
        return requestPost(value, path, APPLICATION_JSON, APPLICATION_JSON_TYPE, needId);
    }

    protected long requestPost(Object value, String path, String requestType, boolean needId) throws ServiceException {
        return requestPost(value, path, requestType, APPLICATION_JSON_TYPE, needId);
    }

    protected long requestPost(Object value, String path, MediaType accept, boolean needId) throws ServiceException {
        return requestPost(value, path, APPLICATION_JSON, accept, needId);
    }

    protected long requestPost(Object value, String path, String requestType, MediaType accept, boolean needId)
            throws ServiceException {
        Client client = getClient();
        WebResource target = client.resource(url);

        if (!isNullOrEmpty(path)) {
            target = target.path(path);
        }

        try {
            ClientResponse response = target.type(requestType + ";charset=utf-8")
                    .header("Authorization", "Basic " + httpAuth).accept(accept).acceptLanguage("ru")
                    .post(ClientResponse.class, value);
            if (response.getStatusInfo().getStatusCode() >= 300) {
                String message = null;
                if (response.hasEntity()) {
                    message = response.getEntity(String.class);
                }
                throw new ServiceException(response.getStatusInfo().getStatusCode(), message);
            }
            --------------------
            if (needId) {
                User us = response.getEntity(User.class);
                return us.getId();
                // return response.getEntity(User.class);
            } else {
                return 0;
            }

        } catch (UniformInterfaceException e) {
            throw new ServiceException(e.getResponse().getStatus(), e);
        } catch (ClientHandlerException e) {
            throw new ServiceException(e);
        }
    }

    protected <T> T requestPost(Class<T> responseClass, Object value, String path, String requestType, MediaType accept)
            throws ServiceException {
        Client client = getClient();
        WebResource target = client.resource(url);
        if (!isNullOrEmpty(path)) {
            target = target.path(path);
        }

        try {
            ClientResponse response = target.type(requestType + ";charset=utf-8")
                    .header("Authorization", "Basic " + httpAuth).accept(accept).acceptLanguage("ru")
                    .post(ClientResponse.class, value);
            return body(response, responseClass);

        } catch (UniformInterfaceException e) {
            throw new ServiceException(e.getResponse().getStatus(), e);
        } catch (ClientHandlerException e) {
            throw new ServiceException(e);
        }
    }

    protected void requestPut(String path) throws ServiceException {
        requestPut(null, path);
    }

    protected void requestPut(Object value, String path) throws ServiceException {
        Client client = getClient();
        WebResource target = client.resource(url);
        if (!isNullOrEmpty(path)) {
            target = target.path(path);
        }

        try {
            Builder builder = target.type(APPLICATION_JSON_VALUE).header("Authorization", "Basic " + httpAuth)
                    .accept(MediaType.valueOf(APPLICATION_JSON_VALUE), TEXT_PLAIN_TYPE).acceptLanguage("ru");
            if (value != null) {
                builder.put(value);
            } else {
                builder.put();
            }
        } catch (UniformInterfaceException e) {
            throw new ServiceException(e.getResponse().getStatus(), e);
        } catch (ClientHandlerException e) {
            throw new ServiceException(e);
        }
    }

    protected void requestDelete(String path) throws ServiceException {
        Client client = getClient();
        WebResource target = client.resource(url);

        if (!isNullOrEmpty(path)) {
            target = target.path(path);
        }

        try {
            target.type(APPLICATION_JSON + ";charset=utf-8").header("Authorization", "Basic " + httpAuth)
                    .accept(APPLICATION_JSON_TYPE).acceptLanguage("ru").delete();
        } catch (UniformInterfaceException e) {
            throw new ServiceException(e);
        } catch (ClientHandlerException e) {
            throw new ServiceException(e);
        }
    }
}
