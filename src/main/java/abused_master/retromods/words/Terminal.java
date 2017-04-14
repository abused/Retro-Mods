package abused_master.retromods.words;

import abused_master.retromods.api.IGenerator;

import java.math.BigInteger;
import java.util.Map;
import java.util.Random;

public class Terminal implements IGenerator {

    private final String value;

    public Terminal(String value) {
        this.value = value;
    }

    @Override
    public String generate(Random random, Map<String, String> params) {
        return value;
    }

    @Override
    public BigInteger count() {
        return BigInteger.ONE;
    }

}