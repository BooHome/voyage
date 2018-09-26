package com.ihere.voyage.emun;

/**
 * @author fengshibo
 * @create 2018-07-06 11:28
 * @desc ${DESCRIPTION}
 **/
public abstract class TestEnum {
    public enum Test1Enum{
        TEST_1_ENUM(1,"TEST1"),
        TEST_2_ENUM(2,"TEST2"),
        TEST_3_ENUM(3,"TEST3"),
        TEST_4_ENUM(4,"TEST4"),
        TEST_5_ENUM(5,"TEST5");
        private int code;
        private String name;

        Test1Enum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static Test1Enum valueOf(int code) {
            for (Test1Enum en : values()) {
                if (en.code == code) {
                    return en;
                }
            }
            return null;
        }
    }
}


