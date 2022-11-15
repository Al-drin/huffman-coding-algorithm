package org.example.szymongarbien.huffmancoding.domain;

import java.util.Map;

public class HuffMessage {
    Map<Character, String> codePage;
    String msg;

    public HuffMessage(Map<Character, String> codePage, String msg) {
        this.codePage = codePage;
        this.msg = msg;
    }

    public Map<Character, String> getCodePage() {
        return codePage;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "HuffMessage{" +
                "codePage=" + codePage +
                ", msg='" + msg + '\'' +
                '}';
    }
}
