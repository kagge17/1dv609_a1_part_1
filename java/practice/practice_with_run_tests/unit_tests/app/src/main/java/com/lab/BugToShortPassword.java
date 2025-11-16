package com.lab;

// Buggy 2: Allows passwords that are too short
public class BugToShortPassword implements IPassword{
    protected int passwordHash;

    public BugToShortPassword(String pw) throws Exception {
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
        int hash = 7;
        for (int i = 0; i < input.length(); i++) {
            hash = hash * 31 + input.charAt(i);
        }
        return hash;
    }

    private boolean isToShort(String pw) {
        return pw.length() < 11; // Bug
    }

    private boolean containsNumber(String text) {
        return text.matches(".*\\d.*");
    }

    @Override
    public boolean isPasswordSame(IPassword other) {
        return this.passwordHash == other.getPasswordHash();
    }

    @Override
    public int getPasswordHash() {
        return this.passwordHash;
    }
}
