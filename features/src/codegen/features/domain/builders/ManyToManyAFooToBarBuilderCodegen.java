package features.domain.builders;

import features.domain.ManyToManyABar;
import features.domain.ManyToManyAFoo;
import features.domain.ManyToManyAFooToBar;
import joist.domain.builders.AbstractBuilder;

@SuppressWarnings("all")
public abstract class ManyToManyAFooToBarBuilderCodegen extends AbstractBuilder<ManyToManyAFooToBar> {

  public ManyToManyAFooToBarBuilderCodegen(ManyToManyAFooToBar instance) {
    super(instance);
  }

  public Long id() {
    return get().getId();
  }

  public ManyToManyAFooToBarBuilder id(Long id) {
    get().setId(id);
    return (ManyToManyAFooToBarBuilder) this;
  }

  public ManyToManyABarBuilder manyToManyABar() {
    if (get().getManyToManyABar() == null) {
      return null;
    }
    return Builders.existing(get().getManyToManyABar());
  }

  public ManyToManyAFooToBarBuilder manyToManyABar(ManyToManyABar manyToManyABar) {
    get().setManyToManyABar(manyToManyABar);
    return (ManyToManyAFooToBarBuilder) this;
  }

  public ManyToManyAFooToBarBuilder with(ManyToManyABar manyToManyABar) {
    return manyToManyABar(manyToManyABar);
  }

  public ManyToManyAFooToBarBuilder manyToManyABar(ManyToManyABarBuilder manyToManyABar) {
    return manyToManyABar(manyToManyABar.get());
  }

  public ManyToManyAFooToBarBuilder with(ManyToManyABarBuilder manyToManyABar) {
    return manyToManyABar(manyToManyABar);
  }

  public ManyToManyAFooBuilder manyToManyAFoo() {
    if (get().getManyToManyAFoo() == null) {
      return null;
    }
    return Builders.existing(get().getManyToManyAFoo());
  }

  public ManyToManyAFooToBarBuilder manyToManyAFoo(ManyToManyAFoo manyToManyAFoo) {
    get().setManyToManyAFoo(manyToManyAFoo);
    return (ManyToManyAFooToBarBuilder) this;
  }

  public ManyToManyAFooToBarBuilder with(ManyToManyAFoo manyToManyAFoo) {
    return manyToManyAFoo(manyToManyAFoo);
  }

  public ManyToManyAFooToBarBuilder manyToManyAFoo(ManyToManyAFooBuilder manyToManyAFoo) {
    return manyToManyAFoo(manyToManyAFoo.get());
  }

  public ManyToManyAFooToBarBuilder with(ManyToManyAFooBuilder manyToManyAFoo) {
    return manyToManyAFoo(manyToManyAFoo);
  }

  public ManyToManyAFooToBar get() {
    return (features.domain.ManyToManyAFooToBar) super.get();
  }

}
