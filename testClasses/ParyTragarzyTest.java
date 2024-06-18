package testClasses;

import FenceCrafting.ParyTragarzy;
import GraphFundamentals.GraphFundamentals.*;

import java.util.ArrayList;
import java.util.List;

public class ParyTragarzyTest {
    public static void main(String[] args) {
        testTragarze1();
        testTragarze2();
        testTragarze3();
        testTragarze4();
    }

    public static void testTragarze1() {
        Tragarz tragarz1 = new Tragarz(true);
        Tragarz tragarz2 = new Tragarz(false);
        Tragarz tragarz3 = new Tragarz(true);
        Tragarz tragarz4 = new Tragarz(false);
        Tragarz tragarz5 = new Tragarz(false);
        Tragarz tragarz6 = new Tragarz(true);

        tragarz1.dodajNieLubi(tragarz2);
        tragarz1.dodajNieLubi(tragarz4);
        tragarz5.dodajNieLubi(tragarz1);
        tragarz3.dodajNieLubi(tragarz4);


        List<Tragarz> tragarze1 = new ArrayList<>();
        tragarze1.add(tragarz1);
        tragarze1.add(tragarz2);
        tragarze1.add(tragarz3);
        tragarze1.add(tragarz4);
        tragarze1.add(tragarz5);
        tragarze1.add(tragarz6);

        ParyTragarzy paraTragarzy = new ParyTragarzy(tragarze1);

        paraTragarzy.wypiszPary();
    }

    public static void testTragarze2() {
        Tragarz tragarz7 = new Tragarz(true);
        Tragarz tragarz8 = new Tragarz(true);
        Tragarz tragarz9 = new Tragarz(true);
        Tragarz tragarz10 = new Tragarz(false);
        Tragarz tragarz11 = new Tragarz(false);
        Tragarz tragarz12 = new Tragarz(true);
        Tragarz tragarz13 = new Tragarz(false);
        Tragarz tragarz14 = new Tragarz(false);

        tragarz9.dodajNieLubi(tragarz10);
        tragarz9.dodajNieLubi(tragarz11);
        tragarz9.dodajNieLubi(tragarz14);
        tragarz8.dodajNieLubi(tragarz10);
        tragarz8.dodajNieLubi(tragarz13);
        tragarz12.dodajNieLubi(tragarz10);
        tragarz12.dodajNieLubi(tragarz13);


        List<Tragarz> tragarze2 = new ArrayList<>();
        tragarze2.add(tragarz7);
        tragarze2.add(tragarz8);
        tragarze2.add(tragarz9);
        tragarze2.add(tragarz10);
        tragarze2.add(tragarz11);
        tragarze2.add(tragarz12);
        tragarze2.add(tragarz13);
        tragarze2.add(tragarz14);

        ParyTragarzy paraTragarzy = new ParyTragarzy(tragarze2);

        paraTragarzy.wypiszPary();
    }

    public static void testTragarze3() {
        Tragarz tragarz15 = new Tragarz(true);
        Tragarz tragarz16 = new Tragarz(true);
        Tragarz tragarz17 = new Tragarz(true);
        Tragarz tragarz18 = new Tragarz(false);
        Tragarz tragarz19 = new Tragarz(false);
        Tragarz tragarz20 = new Tragarz(false);

        tragarz15.dodajNieLubi(tragarz18);
        tragarz15.dodajNieLubi(tragarz19);
        tragarz16.dodajNieLubi(tragarz19);
        tragarz16.dodajNieLubi(tragarz20);
        tragarz17.dodajNieLubi(tragarz18);
        tragarz17.dodajNieLubi(tragarz20);


        List<Tragarz> tragarze3 = new ArrayList<>();
        tragarze3.add(tragarz15);
        tragarze3.add(tragarz16);
        tragarze3.add(tragarz17);
        tragarze3.add(tragarz18);
        tragarze3.add(tragarz19);
        tragarze3.add(tragarz20);

        ParyTragarzy paraTragarzy = new ParyTragarzy(tragarze3);

        paraTragarzy.wypiszPary();
    }

    public static void testTragarze4() {
        Tragarz tragarz21 = new Tragarz(true);
        Tragarz tragarz22 = new Tragarz(true);
        Tragarz tragarz23 = new Tragarz(true);
        Tragarz tragarz24 = new Tragarz(false);
        Tragarz tragarz25 = new Tragarz(false);
        Tragarz tragarz26 = new Tragarz(false);
        Tragarz tragarz27 = new Tragarz(true);
        Tragarz tragarz28 = new Tragarz(true);
        Tragarz tragarz29 = new Tragarz(true);
        Tragarz tragarz30 = new Tragarz(false);
        Tragarz tragarz31 = new Tragarz(false);
        Tragarz tragarz32 = new Tragarz(false);
        Tragarz tragarz33 = new Tragarz(true);
        Tragarz tragarz34 = new Tragarz(true);
        Tragarz tragarz35 = new Tragarz(true);


        tragarz28.dodajNieLubi(tragarz24);
        tragarz28.dodajNieLubi(tragarz25);
        tragarz28.dodajNieLubi(tragarz26);
        tragarz28.dodajNieLubi(tragarz30);
        tragarz28.dodajNieLubi(tragarz32);


        List<Tragarz> tragarze4 = new ArrayList<>();
        tragarze4.add(tragarz21);
        tragarze4.add(tragarz22);
        tragarze4.add(tragarz23);
        tragarze4.add(tragarz24);
        tragarze4.add(tragarz25);
        tragarze4.add(tragarz26);
        tragarze4.add(tragarz27);
        tragarze4.add(tragarz28);
        tragarze4.add(tragarz29);
        tragarze4.add(tragarz30);
        tragarze4.add(tragarz31);
        tragarze4.add(tragarz32);
        tragarze4.add(tragarz33);
        tragarze4.add(tragarz34);
        tragarze4.add(tragarz35);

        ParyTragarzy paraTragarzy = new ParyTragarzy(tragarze4);

        paraTragarzy.wypiszPary();
    }
}
