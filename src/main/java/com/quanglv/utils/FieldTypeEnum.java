package com.quanglv.utils;

/**
 * Map to dynamic field type
 * 1 プルダウン（単数） pulldown_*
 * 2 プルダウン（複数） multiple_pulldown_*
 * 3 チェックボックス checkbox_*
 * 4 ラジオボタン radio_*
 * 5 数値 numeric_*
 * 6 日付 date_*
 * 7 日時 date_time_*
 * 8 時間 time_*
 * 9 テキスト text_*
 * 10 テキスト（複数行） textarea_*
 * 11 ファイル file_*
 * 12 リンク link_*
 * 13 電話番号 phone_number_*
 * 14 住所 address_*
 * 15 メールアドレス email_*
 * 16 演算 calculation_*
 * 17 紐付け relation_*
 * 18 組織選択 select_organization_*
 * 19 ルックアップ lookup_*
 * 20 タブ登録 tab_*
 * 11 見出しHeading_*
 */
public enum FieldTypeEnum {
    FULLTEXT(                     "0", "fulltext_search_", "", "Fulltext search"),
    PULLDOWN(                     "1", "pulldown_", "pulldown_sequence_generator", "Pulldown single"),
    MULTIPLE_PULLDOWN(            "2", "multiple_pulldown_", "multiple_pulldown_sequence_generator", "Multiple pulldown"),
    CHECKBOX(                     "3", "checkbox_", "checkbox_sequence_generator", "Checkbox"),
    RADIO(                        "4", "radio_", "radio_sequence_generator", "Radio"),
    NUMBER(                       "5", "numeric_", "numeric_sequence_generator", "Number"),
    DATE(                         "6", "date_", "date_sequence_generator", "Date"),
    DATETIME(                     "7", "date_time_", "date_time_sequence_generator", "DateTime"),
    TIME(                         "8", "time_", "time_sequence_generator", "Time"),
    TEXT(                         "9", "text_", "text_sequence_generator", "Text"),
    TEXTAREA(                     "10", "textarea_", "textarea_sequence_generator", "TextArea"),
    FILE(                         "11", "file_", "file_sequence_generator", "File"),
    LINK(                         "12", "link_", "link_sequence_generator", "Link"),
    PHONE(                        "13", "phone_number_", "phone_number_sequence_generator", "Phone number"),
    ADDRESS(                      "14", "address_", "address_sequence_generator", "Address"),
    EMAIL(                        "15", "email_", "email_sequence_generator", "Email"),
    CALCULATION(                  "16", "calculation_", "calculation_sequence_generator", "calculation"),
    RELATION(                     "17", "relation_", "relation_sequence_generator", "relation"),
    SELECT_ORGANIZATION(          "18", "select_organization_", "select_organization_sequence_generator", "Select organization"),
    LOOKUP(                       "19", "lookup_", "lookup_sequence_generator", "ルックアップ"),
    TAB(                          "20", "tab_", "tab_sequence_generator", "タブ登録"),
    HEADING(                      "21", "heading_", "heading_sequence_generator", "見出し"),
    BOOL(                         "90", "boolean_", "", "Boolean"),
    OTHER(                        "99", "other_", "", "その他の");

    private String code;
    private String type;
    private String sequence;
    private String name;

    private FieldTypeEnum(String code, String type, String sequence, String name) {
        this.code = code;
        this.type = type;
        this.sequence = sequence;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getSequence() {
        return sequence;
    }

    public String getName() {
        return name;
    }
}
