//package com.quanglv.config.tms;
//
//import jp.co.softbrain.esales.config.Constants;
//import jp.co.softbrain.esales.config.RestDomainProperties;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//
///**
// * Transaction management service API
// */
//public class TmsApi {
//
//    @Autowired
//    private RestDomainProperties restDomainProperties;
//
//    private Logger log = LoggerFactory.getLogger(TmsApi.class);
//
//    private static final String PROTOCOL = "http://";
//    private static final String CONTEXT_PATH = "/tms/api";
//
//    /**
//     *
//     * @param body HTTPResponse body
//     * @return json object result
//     * @throws Exception
//     */
//    private JSONObject getResponse(String body) throws Exception {
//
//        String urlConnection = PROTOCOL + restDomainProperties.getTmsHost() + CONTEXT_PATH;
//
//        URL url = new URL(urlConnection);
//
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Accept", "application/json");
//        conn.setRequestProperty("Content-Type", "application/json");
//        conn.setDoOutput(true);
//        conn.setDoInput(true);
//
//        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
//        wr.write(body);
//        wr.flush();
//
//        if (conn.getResponseCode() != 200) {
//            log.error("TMS API Service Error");
//            return getServiceError("TMS API Service Error");
//        }
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//
//        StringBuilder output = new StringBuilder();
//        String outputLine = "";
//        while ((outputLine = br.readLine()) != null) {
//            output.append(outputLine);
//        }
//        conn.disconnect();
//        return new JSONObject(output.toString());
//    }
//
//    /**
//     * Get service error
//     * @param msg error message
//     * @return json object result
//     */
//    private JSONObject getServiceError(String msg) {
//        JSONObject obj = new JSONObject();
//        obj.put(Constants.TMS.CODE, Constants.TMS.CODE_FAILED);
//        obj.put(Constants.TMS.MSG, msg);
//        return obj;
//    }
//
//    /**
//     * Register prepared statement
//     * @param pssName prepared statement name
//     * @param pssTemplate prepared statement template
//     * @return json object result
//     * @throws Exception
//     */
//    private JSONObject registerPS(String[] pssName, String[] pssTemplate) throws Exception {
//        JSONObject body = new JSONObject();
//        body.put(Constants.TMS.ACTION, Constants.TMS.ACTION_REGISTER_PS);
//        JSONArray array = new JSONArray();
//        for (int i = 0; i < pssName.length; i++) {
//            if (pssName[i] == null)
//                break;
//            JSONObject obj = new JSONObject();
//            obj.put(Constants.TMS.PS_NAME, pssName[i]);
//            obj.put(Constants.TMS.PS_TEMPLATE, pssTemplate[i]);
//            array.put(obj);
//        }
//        body.put(Constants.TMS.PS_LIST, array);
//        return getResponse(body.toString());
//    }
//
//    /**
//     * Start transaction API with set timeout
//     * @param timeout time out - millisecond
//     * @return json object result
//     */
//    public final JSONObject startTransaction(long timeout) {
//        try {
//            JSONObject body = new JSONObject();
//            body.put(Constants.TMS.ACTION, Constants.TMS.ACTION_START_TRANSACTION);
//            body.put(Constants.TMS.TIMEOUT, timeout);
//            return getResponse(body.toString());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return getServiceError(e.getMessage());
//        }
//    }
//
//    /**
//     * Start transaction API with timeout default - that to set from TMS
//     * @return json object result
//     */
//    public final JSONObject startTransaction() {
//        try {
//            JSONObject body = new JSONObject();
//            body.put(Constants.TMS.ACTION, Constants.TMS.ACTION_START_TRANSACTION);
//            return getResponse(body.toString());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return getServiceError(e.getMessage());
//        }
//    }
//
//    /**
//     * Execute SQL
//     * @param transID transaction id
//     * @param pssName prepared statement name
//     * @param pssArgs prepared statement argument
//     * @return json object result
//     * @throws Exception
//     */
//    private final JSONObject execute(String transID, String[] pssName, JSONArray[] pssArgs) throws Exception {
//        JSONObject body = new JSONObject();
//        body.put(Constants.TMS.ACTION, Constants.TMS.ACTION_EXECUTE);
//        body.put(Constants.TMS.TRANS_ID, transID);
//
//        JSONArray array = new JSONArray();
//        for (int i = 0; i < pssName.length; i++) {
//            if (pssName[i] == null)
//                break;
//            JSONObject jObj = new JSONObject();
//            jObj.put(Constants.TMS.PS_NAME, pssName[i]);
//            jObj.put(Constants.TMS.PS_ARGS, pssArgs[i]);
//            array.put(jObj);
//        }
//        body.put(Constants.TMS.QUERIES, array);
//
//        return getResponse(body.toString());
//    }
//
//    /**
//     * Select API
//     * @param transID transaction id
//     * @param query select query
//     * @param objects param object (key-value)
//     * @return json object result
//     */
//    public final JSONObject select(String transID, String query, Object... objects) {
//        try {
//            String[] pssName = new String[32];
//            String[] pssTemplate = new String[32];
//            JSONArray[] pssArgs = new JSONArray[32];
//
//            Converter.convertObjectToSelectQuery(query, objects, pssName, pssTemplate, pssArgs);
//            JSONObject resp = execute(transID, pssName, pssArgs);
//
//            if (Constants.TMS.CODE_INCOMPLETE.equals(resp.get(Constants.TMS.CODE))) {
//
//                JSONObject registerResp = registerPS(pssName, pssTemplate);
//                if(Constants.TMS.CODE_SUCCESS.equals(registerResp.optString(Constants.TMS.CODE))) {
//                    return execute(transID, pssName, pssArgs);
//                } else {
//                    return resp;
//                }
//            } else {
//                return resp;
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return getServiceError(e.getMessage());
//        }
//    }
//
//    /**
//     * Execute native SQL
//     * @param transID transaction id
//     * @param query select query
//     * @param objects param object (key-value)
//     * @return json object result
//     */
//    public final JSONObject execute(String transID, String query, Object... objects) {
//        try {
//            String[] pssName = new String[32];
//            String[] pssTemplate = new String[32];
//            JSONArray[] pssArgs = new JSONArray[32];
//
//            Converter.convertObjectToExecuteQuery(query, objects, pssName, pssTemplate, pssArgs);
//            JSONObject resp = execute(transID, pssName, pssArgs);
//
//            if (Constants.TMS.CODE_INCOMPLETE.equals(resp.get(Constants.TMS.CODE))) {
//
//                JSONObject registerResp = registerPS(pssName, pssTemplate);
//                if(Constants.TMS.CODE_SUCCESS.equals(registerResp.optString(Constants.TMS.CODE))) {
//                    return execute(transID, pssName, pssArgs);
//                } else {
//                    return resp;
//                }
//            } else {
//                return resp;
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return getServiceError(e.getMessage());
//        }
//    }
//
//
//
//    /**
//     * Insert API
//     * @param transID transaction id
//     * @param tenantID tenant name
//     * @param data object to insert
//     * @return json object result
//     */
//    public final JSONObject insert(String transID, String tenantID, Object data) {
//        try {
//            String[] pssName = new String[32];
//            String[] pssTemplate = new String[32];
//            JSONArray[] pssArgs = new JSONArray[32];
//
//            Converter.convertObjectToInsertQuery(tenantID, data, pssName, pssTemplate, pssArgs);
//            JSONObject resp = execute(transID, pssName, pssArgs);
//
//            if (Constants.TMS.CODE_INCOMPLETE.equals(resp.get(Constants.TMS.CODE))) {
//
//                JSONObject registerResp = registerPS(pssName, pssTemplate);
//                if(Constants.TMS.CODE_SUCCESS.equals(registerResp.optString(Constants.TMS.CODE))) {
//                    return execute(transID, pssName, pssArgs);
//                } else {
//                    return resp;
//                }
//            } else {
//                return resp;
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return getServiceError(e.getMessage());
//        }
//    }
//
//    /**
//     * Update API
//     * @param transID transaction id
//     * @param tenantID tenant name
//     * @param data object to insert
//     * @return json object result
//     */
//    public final JSONObject update(String transID, String tenantID, Object data) {
//        try {
//            String[] pssName = new String[32];
//            String[] pssTemplate = new String[32];
//            JSONArray[] pssArgs = new JSONArray[32];
//
//            Converter.convertObjectToUpdateQuery(tenantID, data, pssName, pssTemplate, pssArgs);
//            JSONObject resp = execute(transID, pssName, pssArgs);
//
//            if (Constants.TMS.CODE_INCOMPLETE.equals(resp.get(Constants.TMS.CODE))) {
//                JSONObject registerResp = registerPS(pssName, pssTemplate);
//                if(Constants.TMS.CODE_SUCCESS.equals(registerResp.optString(Constants.TMS.CODE))) {
//                    return execute(transID, pssName, pssArgs);
//                } else {
//                    return resp;
//                }
//            } else {
//                return resp;
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return getServiceError(e.getMessage());
//        }
//    }
//
//    /**
//     * Delete API
//     * @param transID transaction id
//     * @param tenantID tenant name
//     * @param data object to insert
//     * @return json object result
//     */
//    public final JSONObject delete(String transID, String tenantID, Object data) {
//        try {
//            String[] pssName = new String[32];
//            String[] pssTemplate = new String[32];
//            JSONArray[] pssArgs = new JSONArray[32];
//
//            Converter.convertObjectToDeleteQuery(tenantID, data, pssName, pssTemplate, pssArgs);
//            JSONObject resp = execute(transID, pssName, pssArgs);
//
//            if (Constants.TMS.CODE_INCOMPLETE.equals(resp.get(Constants.TMS.CODE))) {
//                JSONObject registerResp = registerPS(pssName, pssTemplate);
//                if(Constants.TMS.CODE_SUCCESS.equals(registerResp.optString(Constants.TMS.CODE))) {
//                    return execute(transID, pssName, pssArgs);
//                } else {
//                    return resp;
//                }
//            } else {
//                return resp;
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return getServiceError(e.getMessage());
//        }
//    }
//
//    /**
//     * End transaction API
//     * @param transID transaction id
//     * @return json object result
//     */
//    public final JSONObject endTransaction(String transID) {
//        try {
//            JSONObject body = new JSONObject();
//            body.put(Constants.TMS.ACTION, Constants.TMS.ACTION_END_TRANSACTION);
//            body.put(Constants.TMS.TRANS_ID, transID);
//            return getResponse(body.toString());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return getServiceError(e.getMessage());
//        }
//    }
//
//    /**
//     * End transaction API
//     * @param transID transaction id
//     * @return json object result
//     */
//    public final JSONObject rollbackTransaction(String transID) {
//        try {
//            JSONObject body = new JSONObject();
//            body.put(Constants.TMS.ACTION, Constants.TMS.ACTION_ROLLBACK_TRANSACTION);
//            body.put(Constants.TMS.TRANS_ID, transID);
//            return getResponse(body.toString());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return getServiceError(e.getMessage());
//        }
//    }
//
//}
