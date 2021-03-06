package features.domain.builders;

import features.domain.OneToOneBBar;
import features.domain.OneToOneBFoo;
import java.util.ArrayList;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class OneToOneBFooBuilderCodegen extends AbstractBuilder<OneToOneBFoo> {

  public OneToOneBFooBuilderCodegen(OneToOneBFoo instance) {
    super(instance);
  }

  public Long id() {
    if (UoW.isOpen() && get().getId() == null) {
      UoW.flush();
    }
    return get().getId();
  }

  public OneToOneBFooBuilder id(Long id) {
    get().setId(id);
    return (OneToOneBFooBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public OneToOneBFooBuilder name(String name) {
    get().setName(name);
    return (OneToOneBFooBuilder) this;
  }

  public OneToOneBFooBuilder with(String name) {
    return name(name);
  }

  @Override
  public OneToOneBFooBuilder defaults() {
    if (name() == null) {
      name("name");
    }
    return (OneToOneBFooBuilder) super.defaults();
  }

  public List<OneToOneBBarBuilder> oneToOneBBars() {
    List<OneToOneBBarBuilder> b = new ArrayList<OneToOneBBarBuilder>();
    for (OneToOneBBar e : get().getOneToOneBBars()) {
      b.add(Builders.existing(e));
    }
    return b;
  }

  public OneToOneBBarBuilder oneToOneBBar(int i) {
    return Builders.existing(get().getOneToOneBBars().get(i));
  }

  public OneToOneBFoo get() {
    return (features.domain.OneToOneBFoo) super.get();
  }

  @Override
  public OneToOneBFooBuilder ensureSaved() {
    if (UoW.isOpen()) {
      if (get().getChanged().size() == 0) {
        throw new RuntimeException("instance has not been changed yet");
      }
      UoW.flush();
    } else {
      throw new RuntimeException("ensureSaved only works if the UoW is open");
    }
    return (OneToOneBFooBuilder) this;
  }

}
