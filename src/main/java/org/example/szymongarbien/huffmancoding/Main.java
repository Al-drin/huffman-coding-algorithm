package org.example.szymongarbien.huffmancoding;

import org.example.szymongarbien.huffmancoding.domain.HuffMessage;
import org.example.szymongarbien.huffmancoding.service.EncodingService;

public class Main {
    public static void main(String[] args) {
        EncodingService service = new EncodingService();

        //String text = "TO BE OR NOT TO BE";
        String text = "A, wie pan, moim zdaniem to nie ma tak, że dobrze, albo że niedobrze. Gdybym miał " +
                "powiedzieć, co cenię w życiu najbardziej, powiedziałbym, że ludzi. Ludzi, którzy podali mi " +
                "pomocną dłoń, kiedy sobie nie radziłem, kiedy byłem sam, i co ciekawe, to właśnie przypadkowe " +
                "spotkania wpływają na nasze życie. Chodzi o to, że kiedy wyznaje się pewne wartości, nawet " +
                "pozornie uniwersalne, bywa, że nie znajduje się zrozumienia, które by tak rzec, które pomaga się " +
                "nam rozwijać. Ja miałem szczęście, by tak rzec, ponieważ je znalazłem, i dziękuję życiu! Dziękuję " +
                "mu; życie to śpiew, życie to taniec, życie to miłość! Wielu ludzi pyta mnie o to samo: ale jak ty " +
                "to robisz, skąd czerpiesz tę radość? A ja odpowiadam, że to proste! To umiłowanie życia. To " +
                "właśnie ono sprawia, że dzisiaj na przykład buduję maszyny, a jutro – kto wie? Dlaczego by nie – " +
                "oddam się pracy społecznej i będę, ot, choćby, sadzić... doć— m-marchew...";

        System.out.println("Original message: " + text);

        HuffMessage codedMessage = service.encode(text);

        System.out.println(codedMessage);

        String decodedMessage = service.decode(codedMessage);

        System.out.println("Decoded message: " + decodedMessage);

    }
}