package features.domain.builders;

import features.domain.ManyToManyABar;
import features.domain.ManyToManyAFoo;
import java.util.ArrayList;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class ManyToManyABarBuilderCodegen extends AbstractBuilder<ManyToManyABar> {

  public ManyToManyABarBuilderCodegen(ManyToManyABar instance) {
    super(instance);
  }

  public Long id() {
    if (UoW.isOpen() && get().getId() == null) {
      UoW.flush();
    }
    return get().getId();
  }

  public ManyToManyABarBuilder id(Long id) {
    get().setId(id);
    return (ManyToManyABarBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public ManyToManyABarBuilder name(String name) {
    get().setName(name);
    return (ManyToManyABarBuilder) this;
  }

  public ManyToManyABarBuilder with(String name) {
    return name(name);
  }

  @Override
  public ManyToManyABarBuilder defaults() {
    if (name() == null) {
      name("name");
    }
    return (ManyToManyABarBuilder) super.defaults();
  }

  public List<ManyToManyAFooBuilder> manyToManyAFoos() {
    List<ManyToManyAFooBuilder> b = new ArrayList<ManyToManyAFooBuilder>();
    for (ManyToManyAFoo e : get().getManyToManyAFoos()) {
      b.add(Builders.existing(e));
    }
    return b;
  }

  public ManyToManyAFooBuilder manyToManyAFoo(int i) {
    return Builders.existing(get().getManyToManyAFoos().get(i));
  }

  public ManyToManyABarBuilder manyToManyAFoo(ManyToManyAFoo manyToManyAFoos) {
    get().addManyToManyAFoo(manyToManyAFoos);
    return (ManyToManyABarBuilder) this;
  }

  public ManyToManyABarBuilder manyToManyAFoo(ManyToManyAFooBuilder manyToManyAFoos) {
    get().addManyToManyAFoo(manyToManyAFoos.get());
    return (ManyToManyABarBuilder) this;
  }

  public ManyToManyABarBuilder with(ManyToManyAFoo manyToManyAFoos) {
    get().addManyToManyAFoo(manyToManyAFoos);
    return (ManyToManyABarBuilder) this;
  }

  public ManyToManyABarBuilder with(ManyToManyAFooBuilder manyToManyAFoos) {
    get().addManyToManyAFoo(manyToManyAFoos.get());
    return (ManyToManyABarBuilder) this;
  }

  public ManyToManyABar get() {
    return (features.domain.ManyToManyABar) super.get();
  }

  @Override
  public ManyToManyABarBuilder ensureSaved() {
    if (UoW.isOpen()) {
      if (get().getChanged().size() == 0) {
        throw new RuntimeException("instance has not been changed yet");
      }
      UoW.flush();
    } else {
      throw new RuntimeException("ensureSaved only works if the UoW is open");
    }
    return (ManyToManyABarBuilder) this;
  }

}
