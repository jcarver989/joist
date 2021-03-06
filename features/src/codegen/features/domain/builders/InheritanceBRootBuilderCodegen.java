package features.domain.builders;

import features.domain.InheritanceBRoot;
import features.domain.InheritanceBRootChild;
import java.util.ArrayList;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class InheritanceBRootBuilderCodegen extends AbstractBuilder<InheritanceBRoot> {

  public InheritanceBRootBuilderCodegen(InheritanceBRoot instance) {
    super(instance);
  }

  public Long id() {
    if (UoW.isOpen() && get().getId() == null) {
      UoW.flush();
    }
    return get().getId();
  }

  public InheritanceBRootBuilder id(Long id) {
    get().setId(id);
    return (InheritanceBRootBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public InheritanceBRootBuilder name(String name) {
    get().setName(name);
    return (InheritanceBRootBuilder) this;
  }

  public InheritanceBRootBuilder with(String name) {
    return name(name);
  }

  @Override
  public InheritanceBRootBuilder defaults() {
    if (name() == null) {
      name("name");
    }
    return (InheritanceBRootBuilder) super.defaults();
  }

  public List<InheritanceBRootChildBuilder> inheritanceBRootChilds() {
    List<InheritanceBRootChildBuilder> b = new ArrayList<InheritanceBRootChildBuilder>();
    for (InheritanceBRootChild e : get().getInheritanceBRootChilds()) {
      b.add(Builders.existing(e));
    }
    return b;
  }

  public InheritanceBRootChildBuilder inheritanceBRootChild(int i) {
    return Builders.existing(get().getInheritanceBRootChilds().get(i));
  }

  public InheritanceBRoot get() {
    return (features.domain.InheritanceBRoot) super.get();
  }

  @Override
  public InheritanceBRootBuilder ensureSaved() {
    if (UoW.isOpen()) {
      if (get().getChanged().size() == 0) {
        throw new RuntimeException("instance has not been changed yet");
      }
      UoW.flush();
    } else {
      throw new RuntimeException("ensureSaved only works if the UoW is open");
    }
    return (InheritanceBRootBuilder) this;
  }

}
