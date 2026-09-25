package org.company.novysh.validator;

public enum FormatType {
    USER_TIMESTAMP_QA("USER_TIMESTAMP_QA", "user_\\d{10}_qa", 18);

    private final String key;
    private final String template;
    private final int expectedLength;

    FormatType(String key, String template, int expectedLength) {
        this.key = key;
        this.template = template;
        this.expectedLength = expectedLength;
    }

    public String getKey() {
        return key;
    }

    public String getTemplate() {
        return template;
    }

    public int getExpectedLength() {
        return expectedLength;
    }

    public boolean isValid(String input) {
        if (input == null) {
            return false;
        }

        return input.matches(template) && input.length() == expectedLength;
    }

    @Override
    public String toString() {
        return "FormatType {key='%s', template='%s', expectedLength=%d}"
                .formatted(key, template, expectedLength);
    }
}
