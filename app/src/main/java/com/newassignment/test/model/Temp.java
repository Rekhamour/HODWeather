package com.newassignment.test.model;

import java.io.Serializable;

public class Temp implements Serializable
    {
        float humidity;
        float min;
        float max;

        public float getHumidity() {
            return humidity;
        }

        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }

        public float getMin() {
            return min;
        }

        public void setMin(float min) {
            this.min = min;
        }

        public float getMax() {
            return max;
        }

        public void setMax(float max) {
            this.max = max;
        }

    }