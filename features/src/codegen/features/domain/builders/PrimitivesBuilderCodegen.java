package features.domain.builders;

import features.domain.Primitives;
import joist.domain.builders.AbstractBuilder;

@SuppressWarnings("all")
public abstract class PrimitivesBuilderCodegen extends AbstractBuilder<Primitives> {

    public PrimitivesBuilderCodegen(Primitives instance) {
        super(instance);
    }

    public Boolean flag() {
        return get().getFlag();
    }

    public PrimitivesBuilder flag(Boolean flag) {
        get().setFlag(flag);
        return (PrimitivesBuilder) this;
    }

    public PrimitivesBuilder with(Boolean flag) {
        get().setFlag(flag);
        return (PrimitivesBuilder) this;
    }

    public Integer id() {
        return get().getId();
    }

    public PrimitivesBuilder id(Integer id) {
        get().setId(id);
        return (PrimitivesBuilder) this;
    }

    public String name() {
        return get().getName();
    }

    public PrimitivesBuilder name(String name) {
        get().setName(name);
        return (PrimitivesBuilder) this;
    }

    public PrimitivesBuilder with(String name) {
        get().setName(name);
        return (PrimitivesBuilder) this;
    }

    public Primitives get() {
        return (features.domain.Primitives) super.get();
    }

}