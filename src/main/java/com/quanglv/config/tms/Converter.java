//package com.quanglv.config.tms;
//
//import org.apache.commons.lang3.reflect.FieldUtils;
//import org.json.JSONArray;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.persistence.Column;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//import java.util.Iterator;
//
///**
// * Transaction management service converter
// */
//public class Converter {
//
//    private static final String DOUBLE_QUOTE = "\"";
//    private static final Logger log = LoggerFactory.getLogger(Converter.class);
//    private static final String ERR_MSG_TENANT_NOT_FOUND = "ERROR: Tenant not found!!!";
//
//    private Converter() {
//
//    }
//
//    /**
//     * Get table name of entity
//     *
//     * @param object entity object
//     * @return table name
//     */
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    private static String getClassName(Object object) {
//
//        Class objClass = object.getClass();
//
//        try {
//
//            Table annotation = (Table) objClass.getAnnotation(Table.class);
//            return annotation.name();
//
//        } catch (Exception ex) {
//
//            log.error(String.format("Could not get table name: %s", ex.getMessage()));
//        }
//
//        return objClass.getSimpleName();
//    }
//
//    /**
//     * Get column name of field
//     *
//     * @param field entity field
//     * @return column name
//     */
//    private static String getFieldName(Field field) {
//
//        if (Modifier.isStatic(field.getModifiers())) {
//            return null;
//        }
//
//        field.setAccessible(true);
//
//        try {
//
//            Column column = field.getAnnotation(Column.class);
//            return column.name();
//
//        } catch (Exception ex) {
//
//            log.error(String.format("Could not get column name: %s", ex.getMessage()));
//        }
//
//        //if field has not annotation @Column do not insert to query
//        return null;
//    }
//
//    /**
//     * check column is sequence
//     *
//     * @param field entity field
//     * @return true if sequence field
//     */
//    private static String getSequence(Field field) {
//        try {
//
//            SequenceGenerator seq = field.getAnnotation(SequenceGenerator.class);
//            if (seq != null)
//                return seq.name();
//
//        } catch (Exception ex) {
//
//            log.error(String.format("Could not get sequence name: %s", ex.getMessage()));
//        }
//
//        return null;
//    }
//
//    /**
//     * check column is json or jsonb type
//     * @param field entity field
//     * @return true: jsonb column
//     */
//    private static String typeJson(Field field) {
//        try {
//            Column column = field.getAnnotation(Column.class);
//            if("json".equals(column.columnDefinition()))
//                return "J";
//            else if("jsonb".equals(column.columnDefinition()))
//                return "B";
//        } catch (Exception ex) {
//
//            log.error(String.format("Exception when check jsonb column: %s", ex.getMessage()));
//        }
//
//        return null;
//    }
//
//    /**
//     * Check column is identity
//     *
//     * @param field entity field
//     * @return true: identity column
//     */
//    private static boolean isIdentity(Field field) {
//
//        try {
//
//            Id column = field.getAnnotation(Id.class);
//            return (column != null);
//
//        } catch (Exception ex) {
//
//            log.error(String.format("Exception when check identity column: %s", ex.getMessage()));
//        }
//
//        return false;
//    }
//
//    /**
//     * Convert Entity Object to insert query and arguments
//     *
//     * @param tenantID tenant name
//     * @param data entity to insert
//     * @param pssName prepared statement name
//     * @param pssTemplate prepared statement template
//     * @param pssArgs prepared statement argument
//     * @throws Exception
//     */
//    public static final void convertObjectToInsertQuery(String tenantID, Object data, String[] pssName,
//                                                        String[] pssTemplate, JSONArray[] pssArgs) throws Exception {
//
//        if (tenantID == null) {
//            log.error(ERR_MSG_TENANT_NOT_FOUND);
//            return;
//        }
//
//        String tableName = getClassName(data);
//        tableName = tenantID + "." + tableName;
//        JSONArray pssArg = new JSONArray();
//        StringBuilder builderP1 = new StringBuilder();
//        StringBuilder builderP2 = new StringBuilder();
//        StringBuilder builderP3 = new StringBuilder();
//
//        builderP1.append("INSERT INTO " + tableName + "(");
//        builderP2.append("VALUES (");
//
//        Field[] fields = FieldUtils.getAllFields(data.getClass());
//
//        int count = 0;
//        int countSequence = 0;
//
//        for (Field f : fields) {
//            String name = getFieldName(f);
//            if(name == null)
//                continue;
//
//            Object value = f.get(data);
//
//            //check column is sequence
//            boolean isSeq = false;
//            String seqName = getSequence(f);
//            if(seqName != null) {
//                isSeq = true;
//                value = "SELECT id FROM " + tenantID + "." + seqName;
//                builderP3.append(" RETURNING  " + name);
//                countSequence++;
//            }
//
//            //if value field is null not insert
//            if(value == null)
//                continue;
//
//            if (count > 0) {
//                builderP1.append(",");
//                builderP2.append(",");
//            }
//
//            String typeJson = typeJson(f); //column is json or jsonb
//
//            builderP1.append(DOUBLE_QUOTE + name + DOUBLE_QUOTE);
//            //if column is sequence ==> param is ?S
//            //if column type is json or jsonb ==> param is ?J or ?B
//            if(isSeq)
//                builderP2.append("?S");
//            else if (typeJson != null)
//                builderP2.append("?" + typeJson);
//            else
//                builderP2.append("?");
//            pssArg.put(value);
//            count++;
//        }
//        builderP1.append(") ");
//        builderP2.append(")");
//        //Have not sequence, return *
//        if (countSequence == 0 ) {
//            builderP3.append(" RETURNING *");
//        }
//        String pssQuery = builderP1.toString() + builderP2.toString() + builderP3.toString();
//
//        int pssCount = 0;
//
//        pssName[pssCount] = "I" + tableName + "-" + count + "-" + pssQuery.hashCode();
//        pssTemplate[pssCount] = pssQuery;
//        pssArgs[pssCount] = pssArg;
//    }
//
//
//    /**
//     * Convert Entity Object to update query and arguments
//     *
//     * @param tenantID tenant name
//     * @param data entity to insert
//     * @param pssName prepared statement name
//     * @param pssTemplate prepared statement template
//     * @param pssArgs prepared statement argument
//     * @throws Exception
//     */
//    public static final void convertObjectToUpdateQuery(String tenantID, Object data, String[] pssName,
//                                                        String[] pssTemplate, JSONArray[] pssArgs) throws Exception {
//
//        if (tenantID == null) {
//            log.error(ERR_MSG_TENANT_NOT_FOUND);
//            return;
//        }
//
//        String tableName = getClassName(data);
//        tableName = tenantID + "." + tableName;
//
//
//        JSONArray pssArg = new JSONArray();
//        JSONArray pssArgWhere = new JSONArray();
//        StringBuilder builder = new StringBuilder();
//        StringBuilder builderWhere = new StringBuilder();
//        StringBuilder builderReturn = new StringBuilder();
//
//        builder.append("UPDATE " + tableName + " SET ");
//
//        Field[] fields = FieldUtils.getAllFields(data.getClass());
//
//        int count = 0;
//        int countId = 0;
//
//        for (Field f : fields) {
//            String name = getFieldName(f);
//            if (name == null)
//                continue;
//
//            Object value = f.get(data);
//
//            boolean isId = isIdentity(f);
//            if (isId) {
//                if (countId > 0) {
//                    builderWhere.append(" AND ");
//                }
//
//                builderWhere.append(DOUBLE_QUOTE + name + DOUBLE_QUOTE);
//                builderWhere.append("=");
//                builderWhere.append("?");
//                pssArgWhere.put(value);
//
//                if (countId == 0) { // First ID
//                    builderReturn.append(" RETURNING " + name);
//                } else {
//                    builderReturn.append(" , " + name);
//                }
//
//                countId++;
//
//            } else {
//                if (count > 0) {
//                    builder.append(",");
//                }
//
//                String typeJson = typeJson(f);
//
//                builder.append(DOUBLE_QUOTE + name + DOUBLE_QUOTE);
//                builder.append("=");
//
//                //if value field is null
//                if (value == null) {
//                    builder.append("?N");
//                    value = "NULL";
//                } else {
//                    //if column type is json or jsonb ==> param is ?J or ?B
//                    builder.append(typeJson != null ? "?" + typeJson : "?");
//                }
//
//                pssArg.put(value);
//                count++;
//            }
//        }
//        String pssQuery = builder.toString() + " WHERE " + builderWhere.toString() + builderReturn.toString();
//        Iterator ite = pssArgWhere.iterator();
//        while (ite.hasNext())
//            pssArg.put(ite.next());
//
//        int pssCount = 0;
//
//        pssName[pssCount] = "U" + tableName + "-" + count + "-" + pssQuery.hashCode();
//        pssTemplate[pssCount] = pssQuery;
//        pssArgs[pssCount] = pssArg;
//    }
//
//
//    /**
//     * Convert Entity Object to delete query and arguments
//     *
//     * @param tenantID tenant name
//     * @param data entity to insert
//     * @param pssName prepared statement name
//     * @param pssTemplate prepared statement template
//     * @param pssArgs prepared statement argument
//     * @throws Exception
//     */
//    public static final void convertObjectToDeleteQuery(String tenantID, Object data, String[] pssName,
//                                                        String[] pssTemplate, JSONArray[] pssArgs) throws Exception {
//
//        if (tenantID == null) {
//            log.error(ERR_MSG_TENANT_NOT_FOUND);
//            return;
//        }
//
//        String tableName = getClassName(data);
//        tableName = tenantID + "." + tableName;
//
//        JSONArray pssArg = new JSONArray();
//        StringBuilder builder = new StringBuilder();
//
//        StringBuilder builderReturn = new StringBuilder();
//
//        builder.append("DELETE FROM " + tableName + " WHERE ");
//
//        Field[] fields = FieldUtils.getAllFields(data.getClass());
//
//        int count = 0;
//        for (Field f : fields) {
//            String name = getFieldName(f);
//            if(name == null)
//                continue;
//
//            Object value = f.get(data);
//            //if value field is null not insert
//            if(value == null)
//                continue;
//
//            //check if column is not identity continue to next column
//            boolean isId = isIdentity(f);
//            if(!isId)
//                continue;
//
//            if (count > 0) {
//                builder.append(" AND ");
//            }
//
//            builder.append(DOUBLE_QUOTE + name + DOUBLE_QUOTE);
//            builder.append("=");
//            builder.append("?");
//            pssArg.put(value);
//
//            //Delete by composite key then returning first key is OK
//            if (count == 0) {
//                builderReturn.append(" RETURNING " + name);
//            }
//
//            count++;
//
//        }
//        String pssQuery = builder.toString() + builderReturn.toString();
//
//        int pssCount = 0;
//
//        pssName[pssCount] = "D" + tableName + "-" + pssQuery.hashCode();
//        pssTemplate[pssCount] = pssQuery;
//        pssArgs[pssCount] = pssArg;
//    }
//
//    /**
//     * Convert select query
//     * @param query select query
//     * @param objects param object (key-value)
//     * @param pssName prepared statement name
//     * @param pssTemplate prepared statement template
//     * @param pssArgs prepared statement argument
//     */
//    public static void convertObjectToSelectQuery(String query, Object[] objects, String[] pssName,
//                                                  String[] pssTemplate, JSONArray[] pssArgs) {
//
//        JSONArray pssArg = new JSONArray();
//        StringBuilder sb = new StringBuilder();
//
//        for(Object obj : objects)
//            pssArg.put(obj);
//
//        int pssCount = 0;
//
//        pssName[pssCount] = "S" + query.hashCode();
//
//        //Build query to convert result to json string
//        sb.append("SELECT array_to_json(array_agg(row_to_json(t))) FROM (");
//        sb.append(query);
//        sb.append(") t ");
//        pssTemplate[pssCount] = sb.toString();
//
//        pssArgs[pssCount] = pssArg;
//
//    }
//
//
//    /**
//     * Convert sql to execute
//     * @param query query to execute
//     * @param objects param object (key-value)
//     * @param pssName prepared statement name
//     * @param pssTemplate prepared statement template
//     * @param pssArgs prepared statement argument
//     */
//    public static void convertObjectToExecuteQuery(String query, Object[] objects, String[] pssName,
//                                                  String[] pssTemplate, JSONArray[] pssArgs) {
//
//        JSONArray pssArg = new JSONArray();
//        StringBuilder sb = new StringBuilder();
//
//        for(Object obj : objects)
//            pssArg.put(obj);
//
//        int pssCount = 0;
//
//        pssName[pssCount] = "E" + query.hashCode();
//
//        //Build query
//        sb.append(query);
//        sb.append(" RETURNING *");
//        pssTemplate[pssCount] = sb.toString();
//
//        pssArgs[pssCount] = pssArg;
//
//    }
//
//}
