package com.quanglv.constant;

public interface AppConstant {
    interface O2Constants {
        final String CLIEN_ID = "client_id";
      final String CLIENT_SECRET = "client_secret";
//        final String CLIENT_SECRET = "$2a$04$e/c1/RfsWuThaWFCrcCuJeoyvwCV0URN/6Pn9ZFlrtIWaU/vj/BfG";
        final String GRANT_TYPE_PASSWORD = "password";
        final String AUTHORIZATION_CODE = "authorization_code";
        final String REFRESH_TOKEN = "refresh_token";
        final String IMPLICIT = "implicit";
        final String SCOPE_READ = "read";
        final String SCOPE_WRITE = "write";
        final String TRUST = "openid";
        final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
        final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;
    }

    interface ResourceServer {
        final String RESOURCE_ID = "resource_id";
    }

    interface STATUS{
        final Long ACTIVE_STATUS = 1L;
        final Long INACTIVE_STATUS = 0L;
    }
}
