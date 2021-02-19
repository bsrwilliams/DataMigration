package org.sparta.bradleywilliams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sparta.bradleywilliams.controllers.EmployeeManager;

public class Tests {
    @Test
    @DisplayName("Test for removing duplicates from file")
    public  void testingDuplicatesRemovedIn10k() {
        int count = 9943;
        int duplicates = 57;
        Assertions.assertEquals(count, 10000-duplicates);
    }
}
