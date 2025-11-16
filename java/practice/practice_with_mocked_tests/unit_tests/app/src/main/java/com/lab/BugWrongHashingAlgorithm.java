package com.lab;

public class BugWrongHashingAlgorithm implements IPassword{
    protected int passwordHash;

    public BugWrongHashingAlgorithm(String pw) throws Exception {
        String trimmedPW = pw.trim(); // Remove whitespace
        if (isToShort(trimmedPW)) {
            throw new Exception("To short password");
        }
        if (containsNumber(trimmedPW) == false) {
            throw new Exception("Does not contain a number");
        }
        this.passwordHash = simpleHash(trimmedPW);
    }

    private int simpleHash(String input) {
        int hash = 0;
        for (int i = 0; i < input.length(); i++) {
            hash += input.charAt(i);
        }
        return hash % 5;
    }

    private boolean isToShort(String pw) {
        return pw.length() < 12;
    }

    private boolean containsNumber(String text) {
        return text.matches(".*\\d.*");
    }

    @Override
    public int getPasswordHash() {
        return this.passwordHash;
    }

    @Override
    public boolean isPasswordSame(IPassword other) {
        return this.passwordHash == other.getPasswordHash();
    }
}
