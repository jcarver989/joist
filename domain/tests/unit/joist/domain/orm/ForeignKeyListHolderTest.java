package joist.domain.orm;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ForeignKeyListHolderTest extends TestCase {

    public void testLoadOutsideUoWWithNoIdIsOkay() {
        DummyDomainObject parent = new DummyDomainObject();
        ForeignKeyListHolder<DummyDomainObject, DummyDomainObject> h = //
        new ForeignKeyListHolder<DummyDomainObject, DummyDomainObject>(parent, null, null);
        // Worked because there is no id set
        Assert.assertEquals(0, h.get().size());
    }

    public void testLoadOutsideUoWFails() {
        DummyDomainObject parent = new DummyDomainObject();
        ForeignKeyListHolder<DummyDomainObject, DummyDomainObject> h = //
        new ForeignKeyListHolder<DummyDomainObject, DummyDomainObject>(parent, null, null);
        parent.setId(1);
        try {
            h.get();
            Assert.fail();
        } catch (RuntimeException e) {
            Assert.assertEquals("The UoW is currently closed", e.getMessage());
        }
    }

}