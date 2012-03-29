package features.domain.builders;

import features.domain.ParentBChildFoo;
import features.domain.ParentBParent;
import joist.domain.builders.AbstractBuilder;

@SuppressWarnings("all")
public abstract class ParentBChildFooBuilderCodegen extends AbstractBuilder<ParentBChildFoo> {

  public ParentBChildFooBuilderCodegen(ParentBChildFoo instance) {
    super(instance);
  }

  public Long id() {
    return get().getId();
  }

  public ParentBChildFooBuilder id(Long id) {
    get().setId(id);
    return (ParentBChildFooBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public ParentBChildFooBuilder name(String name) {
    get().setName(name);
    return (ParentBChildFooBuilder) this;
  }

  public ParentBChildFooBuilder with(String name) {
    return name(name);
  }

  public ParentBParentBuilder parentBParent() {
    if (get().getParentBParent() == null) {
      return null;
    }
    return Builders.existing(get().getParentBParent());
  }

  public ParentBChildFooBuilder parentBParent(ParentBParent parentBParent) {
    get().setParentBParent(parentBParent);
    return (ParentBChildFooBuilder) this;
  }

  public ParentBChildFooBuilder with(ParentBParent parentBParent) {
    return parentBParent(parentBParent);
  }

  public ParentBChildFooBuilder parentBParent(ParentBParentBuilder parentBParent) {
    return parentBParent(parentBParent.get());
  }

  public ParentBChildFooBuilder with(ParentBParentBuilder parentBParent) {
    return parentBParent(parentBParent);
  }

  public ParentBChildFoo get() {
    return (features.domain.ParentBChildFoo) super.get();
  }

}
