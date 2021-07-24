package com.quanglv.config.resttemplate;

import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.List;

public final class Constants {

    private Constants() {

    }

    public static final String SERVICE_NAME = "creator";
    public static final String SLASH = "/";
    public static final String EMPTY = "";

    public static final String CODE_LANG_US = "en";
    public static final String LOCALE_US = "en_US";
    public static final String LOCALE_VI = "vi_VN";

    //Domain
    public static final String TEST_HOST = "localhost:8081";
    public static final String PUBLIC_HOST = "localhost:8082";
    public static final String RESOURCE_HOST = "localhost:8083";
    public static final String URL_FRONT_END = "localhost:8081/";
    public static final String AUTH_HOST = "localhost:9999/";
    public static final String RESOURCE_SERVER_HOST = "192.168.1.142:8083/";
    public static final String AUTH_SERVICE_LOGOUT = "http://localhost:9999/auth/rest/logout";

    public static final HttpMethod httpMethod_GET = HttpMethod.GET;
    public static final HttpMethod httpMethod_POST = HttpMethod.POST;

    public static final String AVATAR_FOLDER = "avatar";
    public static final String CHANNEL_FOLDER = "channel";
    public static final String EXAM_IMAGE_FOLDER = "exam/";

    public static final Integer CHANNEL_TYPE_ATTACH = 5;
    public static final String ROW_ID = "rowId";
    public static final String ERROR_ITEM = "item";
    public static final String ERROR_CODE = "errorCode";
    public static final Integer DEFAULT_OFFSET = 0;
    public static final Integer DEFAULT_LIMIT = 5;
    public static final String EMPTY_EDITOR = "<p><br></p>";
    public static final String SPACE_EDITOR = "&nbsp;";
    public static final String EMPTY_PARAGRAPH = "<p></p>";
    public static final Integer FILE_TYPE = 1;

    // service connection error
    public static final String SERVICE_CONNECTION_ERROR = "SERVICE_CONNECTION_ERROR";

    // Database connection error
    public static final String DATABASE_CONNECTION_ERROR = "DATABASE_CONNECTION_ERROR";

    public static final String ERROR = "error";

    public static final String ERR_000 = "ERR_000";

    public static final String ERR_001 = "ERR_001";
    public static final String ERR_002 = "ERR_002";

    // topic exits Channel
    public static final String ERR_003 = "ERR_003";

    // duplicate
    public static final String ERR_004 = "ERR_004";

    public static final String ERR_005 = "ERR_005";

    // channels is Default
    public static final String ERR_0010 = "ERR_0010";
    // don't lock channel Default
    public static final String ERR_0011 = "ERR_0011";
    // don't set channel Default with channel lock
    public static final String ERR_0012 = "ERR_0012";
    // don't publist channel with channel lock
    public static final String ERR_0013 = "ERR_0013";
    // warning: The Topic you want to delete contains the subtopics/ exam
    public static final String ERR_0014 = "ERR_0014";
    // warning: The title of the test in the same topic cannot match
    public static final String ERR_0015 = "ERR_0015";
    // error : The topic name is overlapping
    public static final String ERR_0016 = "ERR_0016";
    // warning: Default channel is not set up
    public static final String ERR_0017 = "ERR_0017";
    // warning: No subject to publish yet
    public static final String ERR_0018 = "ERR_0018";
    // Can not remove! Data does not exist
    public static final String ERR_0019 = "ERR_0019";

    public static final String ERR_0020 = "ERR_0020";
    //check duplicate name member groups
    public static final String ERR_0021 = "ERR_0021";
    //check duplicate email
    public static final String ERR_0022 = "ERR_0022";
    // constraint  violation
    public static final String ERR_CONSTRAINT = "ERR_CONSTRAINT";
    public static final String ERR_023 = "ERR_023";
    public static final String ERR_024 = "ERR_024";

    public static final class AnswerLabel {
        private AnswerLabel() {}
        public static final List<String> labels = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H");
    }

    public static final class Authenticate {
        private Authenticate() {
        }
        public static final String USER_ID = "userId";
    }

    public static final String TABLE_NAME_USER_INFOS = "userInfos";

    public static final class Pattern {
        private Pattern() {}
        public static final String REGEX_SPLIT_IMPORT = ",";
    }

    public static final class ImportMember {
        private ImportMember() {}
        public static final String FIELD_IMPORT_MEMBER = "name,email,phoneNumber";
        public static final String HEADER_EXPORT_MEMBER_ERROR_VI = "STT,Họ tên,Email,Số điện thoại,Vị trí lỗi,Nội dung lỗi";
        public static final String HEADER_EXPORT_MEMBER_ERROR_EN= "Order,Fullname,Email,Phone Number,Error position,Error content";
    }

    public static final class StatusSave {
        private StatusSave() {}
        public static final Integer SUCCESS = 1;
        public static final Integer FAIL = 0;
        public static final Integer IN_COMPLETE = 1;
        public static final Integer COMPLETE = 2;
    }

    public static final class FieldChannel {
        private FieldChannel() {}
        public static final Integer EDUCATION = 1;
        public static final Integer CAREER = 2;
        public static final Integer FOREIGN_LANGUAGE = 3;
    }

    public static final class StatusChannel {
        private StatusChannel() {}
        public static final Integer PUBLIC = 1;
        public static final Integer PRIVATE = 2;
    }
}
