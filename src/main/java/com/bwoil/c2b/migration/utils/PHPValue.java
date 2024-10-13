package com.bwoil.c2b.migration.utils;

import java.util.Map;

/**
 * Since i have to declare the return type of a function, and i have no idea ahead of time what
 * value is being unserialized, this helper class is a hack so that i can return any variable type
 * as PHPValue and deal with it later.
 *
 * @author wuzf
 * @ClassName: PHPValue
 * @date 2017年8月8日 上午11:57:26
 */
public class PHPValue {
    /**
     * The raw object given to us by unserialize()
     */
    private Object obj = null;

    /**
     * The toString() value of the Object passed into PHPValue
     */
    public String value = "";

    /**
     * Is the PHPValue one of the following?
     */
    public boolean isNull = false;
    public boolean isInteger = false;
    public boolean isDouble = false;
    public boolean isString = false;
    public boolean isHashMap = false;

    /**
     * Pass in an object, and PHPValue: 1. Attempts to try and figure out what it is 2. Stringifies
     * it 3. Sets a boolean flag to let everyone know what we think it is.
     */
    public PHPValue(Object o) {
        obj = o;

        if (obj == null) {
            isNull = true;
            value = "";
        } else if (obj.getClass().toString().equals("class java.lang.Integer")) {
            isInteger = true;
            value = ((Integer) obj).toString();
        } else if (obj.getClass().toString().equals("class java.lang.Double")) {
            isDouble = true;
            value = ((Double) obj).toString();
        } else if (obj.getClass().toString().equals("class java.util.HashMap")) {
            isHashMap = true;
            value = obj.toString();
        } else if (obj.getClass().toString().equals("class java.lang.String")) {
            isString = true;
            value = obj.toString();
        } else {
            value = obj.toString();
        }

        if (obj != null) {
            // System.out.println("PHPValue: '" + obj.getClass() + "' = " +
            // value);
        }

    }

    /**
     * Convert PHPValue into a double
     *
     * @return double
     */
    public double toDouble() {
        if (isDouble) {
            return Double.parseDouble(value);
        } else {
            return 0.0;
        }
    }

    /**
     * Convert PHPValue into an integer
     *
     * @return int
     */
    public int toInteger() {
        if (isInteger) {
            return Integer.parseInt(value);
        } else {
            return 0;
        }
    }

    /**
     * PHPValue's string-ified value
     *
     * @return String
     */
    @Override
    public String toString() {
        if (!isNull) {
            return value;
        } else {
            return "";
        }
    }

    /**
     * Return the raw object
     *
     * @return double
     */
    public Object toObject() {
        return obj;
    }

    /**
     * Return the raw object
     *
     * @return double
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> toMapObject() {
        return (Map<String, Object>) obj;
    }
}