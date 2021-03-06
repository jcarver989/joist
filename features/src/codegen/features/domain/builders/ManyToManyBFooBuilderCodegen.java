package features.domain.builders;

import features.domain.ManyToManyBBar;
import features.domain.ManyToManyBFoo;
import java.util.ArrayList;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class ManyToManyBFooBuilderCodegen extends AbstractBuilder<ManyToManyBFoo> {

  public ManyToManyBFooBuilderCodegen(ManyToManyBFoo instance) {
    super(instance);
  }

  public Long id() {
    if (UoW.isOpen() && get().getId() == null) {
      UoW.flush();
    }
    return get().getId();
  }

  public ManyToManyBFooBuilder id(Long id) {
    get().setId(id);
    return (ManyToManyBFooBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public ManyToManyBFooBuilder name(String name) {
    get().setName(name);
    return (ManyToManyBFooBuilder) this;
  }

  public ManyToManyBFooBuilder with(String name) {
    return name(name);
  }

  @Override
  public ManyToManyBFooBuilder defaults() {
    if (name() == null) {
      name("name");
    }
    return (ManyToManyBFooBuilder) super.defaults();
  }

  public List<ManyToManyBBarBuilder> owneds() {
    List<ManyToManyBBarBuilder> b = new ArrayList<ManyToManyBBarBuilder>();
    for (ManyToManyBBar e : get().getOwneds()) {
      b.add(Builders.existing(e));
    }
    return b;
  }

  public ManyToManyBBarBuilder owned(int i) {
    return Builders.existing(get().getOwneds().get(i));
  }

  public ManyToManyBFooBuilder owned(ManyToManyBBar owneds) {
    get().addOwned(owneds);
    return (ManyToManyBFooBuilder) this;
  }

  public ManyToManyBFooBuilder owned(ManyToManyBBarBuilder owneds) {
    get().addOwned(owneds.get());
    return (ManyToManyBFooBuilder) this;
  }

  public ManyToManyBFooBuilder with(ManyToManyBBar owneds) {
    get().addOwned(owneds);
    return (ManyToManyBFooBuilder) this;
  }

  public ManyToManyBFooBuilder with(ManyToManyBBarBuilder owneds) {
    get().addOwned(owneds.get());
    return (ManyToManyBFooBuilder) this;
  }

  public ManyToManyBFoo get() {
    return (features.domain.ManyToManyBFoo) super.get();
  }

  @Override
  public ManyToManyBFooBuilder ensureSaved() {
    if (UoW.isOpen()) {
      if (get().getChanged().size() == 0) {
        throw new RuntimeException("instance has not been changed yet");
      }
      UoW.flush();
    } else {
      throw new RuntimeException("ensureSaved only works if the UoW is open");
    }
    return (ManyToManyBFooBuilder) this;
  }

}
