package com.lab;

// Buggy 4: Does not throw exception for short passwords
public class BugMissingPasswordLengthCheck implements IPassword {
    protected int passwordHash;

    public BugMissingPasswordLengthCheck(String pw) throws Exception {
        String trimmedPW = pw.trim();
        // Bug: missing short password check
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
    
    private boolean containsNumber(String text) {
        return text.matches(".*\\d.*");
    }
    
    public int getPasswordHash() {
        return this.passwordHash;
    }
    
    public boolean isPasswordSame(IPassword other) {
        return this.passwordHash == other.getPasswordHash();
    }
}