package com.lab;

// Buggy 5: Does not throw exception if password lacks number
public class BugMissingNumberCheck implements IPassword {
    protected int passwordHash;

    public BugMissingNumberCheck(String pw) throws Exception {
        String trimmedPW = pw.trim();
        if (isToShort(trimmedPW)) {
            throw new Exception("To short password");
        }
        // Bug: missing number check
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
        return pw.length() < 12;
    }
    
    public int getPasswordHash() {
        return this.passwordHash;
    }
    
    public boolean isPasswordSame(IPassword other) {
        return this.passwordHash == other.getPasswordHash();
    }
}
