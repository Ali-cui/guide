package com.dhu.guide.entities;

import java.util.Arrays;

/**
 * @Author: Ali.cui
 * @Date: 2019/12/31 16:00
 */
public class Audio {
    private String addressname;
    private byte[] audio;

    public Audio(String addressname, byte[] byteFile) {
        this.addressname=addressname;
        this.audio=byteFile;
    }

    public String getAddressname() {
        return addressname;
    }

    public void setAddressname(String addressname) {
        this.addressname = addressname;
    }

    public byte[] getAudiobinary() {
        return audio;
    }

    public void setAudiobinary(byte[] audiobinary) {
        this.audio = audiobinary;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "addressname='" + addressname + '\'' +
                ", audiobinary=" + Arrays.toString(audio) +
                '}';
    }
}
