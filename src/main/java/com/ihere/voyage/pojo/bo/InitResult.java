package com.ihere.voyage.pojo.bo;

/**
 * @author: fengshibo
 * @date: 2018/9/27 10:25
 * @description:
 */
public class InitResult{

        private int[][] ints;
        private String intsStr;
        private String fileName;

        public int[][] getInts() {
            return ints;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public void setInts(int[][] ints) {
            this.ints = ints;
        }

        public String getIntsStr() {
            return intsStr;
        }

        public void setIntsStr(String intsStr) {
            this.intsStr = intsStr;
        }
}
