package features.domain;

import features.domain.queries.ParentBChildBarQueries;
import joist.domain.AbstractChanged;
import joist.domain.AbstractDomainObject;
import joist.domain.Changed;
import joist.domain.Shim;
import joist.domain.orm.ForeignKeyHolder;
import joist.domain.uow.UoW;
import joist.domain.validation.rules.MaxLength;
import joist.domain.validation.rules.NotEmpty;
import joist.domain.validation.rules.NotNull;

@SuppressWarnings("all")
public abstract class ParentBChildBarCodegen extends AbstractDomainObject {

  public static final ParentBChildBarQueries queries;
  private Long id = null;
  private String name = null;
  private Long version = null;
  private final ForeignKeyHolder<ParentBChildBar, ParentBParent> parentBParent = new ForeignKeyHolder<ParentBChildBar, ParentBParent>(ParentBChildBar.class, ParentBParent.class, Aliases.parentBParent(), Aliases.parentBChildBar().parentBParent);
  protected Changed changed;

  static {
    Aliases.parentBChildBar();
    queries = new ParentBChildBarQueries();
  }

  protected ParentBChildBarCodegen() {
    this.addExtraRules();
  }

  private void addExtraRules() {
    this.addRule(new NotNull<ParentBChildBar>(Shims.name));
    this.addRule(new MaxLength<ParentBChildBar>(Shims.name, 100));
    this.addRule(new NotEmpty<ParentBChildBar>(Shims.name));
    this.addRule(new NotNull<ParentBChildBar>(Shims.parentBParentId));
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.getChanged().record("id", this.id, id);
    this.id = id;
    if (UoW.isOpen()) {
      UoW.getIdentityMap().store(this);
    }
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.getChanged().record("name", this.name, name);
    this.name = name;
  }

  protected void defaultName(String name) {
    this.name = name;
  }

  public Long getVersion() {
    return this.version;
  }

  public ParentBParent getParentBParent() {
    return this.parentBParent.get();
  }

  public void setParentBParent(ParentBParent parentBParent) {
    if (this.parentBParent.get() != null) {
      this.parentBParent.get().removeParentBChildBarWithoutPercolation((ParentBChildBar) this);
    }
    this.setParentBParentWithoutPercolation(parentBParent);
    if (this.parentBParent.get() != null) {
      this.parentBParent.get().addParentBChildBarWithoutPercolation((ParentBChildBar) this);
    }
  }

  protected void setParentBParentWithoutPercolation(ParentBParent parentBParent) {
    this.getChanged().record("parentBParent", this.parentBParent.get(), parentBParent);
    this.parentBParent.set(parentBParent);
  }

  public ParentBChildBarChanged getChanged() {
    if (this.changed == null) {
      this.changed = new ParentBChildBarChanged((ParentBChildBar) this);
    }
    return (ParentBChildBarChanged) this.changed;
  }

  @Override
  public void clearAssociations() {
    super.clearAssociations();
    this.setParentBParent(null);
  }

  static class Shims {
    protected static final Shim<ParentBChildBar, Long> id = new Shim<ParentBChildBar, Long>() {
      public void set(ParentBChildBar instance, Long id) {
        ((ParentBChildBarCodegen) instance).id = id;
      }
      public Long get(ParentBChildBar instance) {
        return ((ParentBChildBarCodegen) instance).id;
      }
      public String getName() {
        return "id";
      }
    };
    protected static final Shim<ParentBChildBar, String> name = new Shim<ParentBChildBar, String>() {
      public void set(ParentBChildBar instance, String name) {
        ((ParentBChildBarCodegen) instance).name = name;
      }
      public String get(ParentBChildBar instance) {
        return ((ParentBChildBarCodegen) instance).name;
      }
      public String getName() {
        return "name";
      }
    };
    protected static final Shim<ParentBChildBar, Long> version = new Shim<ParentBChildBar, Long>() {
      public void set(ParentBChildBar instance, Long version) {
        ((ParentBChildBarCodegen) instance).version = version;
      }
      public Long get(ParentBChildBar instance) {
        return ((ParentBChildBarCodegen) instance).version;
      }
      public String getName() {
        return "version";
      }
    };
    protected static final Shim<ParentBChildBar, Long> parentBParentId = new Shim<ParentBChildBar, Long>() {
      public void set(ParentBChildBar instance, Long parentBParentId) {
        ((ParentBChildBarCodegen) instance).parentBParent.setId(parentBParentId);
      }
      public Long get(ParentBChildBar instance) {
        return ((ParentBChildBarCodegen) instance).parentBParent.getId();
      }
      public String getName() {
        return "parentBParent";
      }
    };
  }

  public static class ParentBChildBarChanged extends AbstractChanged {
    public ParentBChildBarChanged(ParentBChildBar instance) {
      super(instance);
    }
    public boolean hasId() {
      return this.contains("id");
    }
    public Long getOriginalId() {
      return (Long) this.getOriginal("id");
    }
    public boolean hasName() {
      return this.contains("name");
    }
    public String getOriginalName() {
      return (java.lang.String) this.getOriginal("name");
    }
    public boolean hasVersion() {
      return this.contains("version");
    }
    public Long getOriginalVersion() {
      return (Long) this.getOriginal("version");
    }
    public boolean hasParentBParent() {
      return this.contains("parentBParent");
    }
    public ParentBParent getOriginalParentBParent() {
      return (ParentBParent) this.getOriginal("parentBParent");
    }
  }

}
