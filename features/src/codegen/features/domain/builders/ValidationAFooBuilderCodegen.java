package features.domain.builders;

import features.domain.ValidationAFoo;
import joist.domain.builders.AbstractBuilder;

@SuppressWarnings("all")
public abstract class ValidationAFooBuilderCodegen extends AbstractBuilder<ValidationAFoo> {

    public ValidationAFooBuilderCodegen(ValidationAFoo instance) {
        super(instance);
    }

    public Integer id() {
        return get().getId();
    }

    public ValidationAFooBuilder id(Integer id) {
        get().setId(id);
        return (ValidationAFooBuilder) this;
    }

    public String name() {
        return get().getName();
    }

    public ValidationAFooBuilder name(String name) {
        get().setName(name);
        return (ValidationAFooBuilder) this;
    }

    public ValidationAFooBuilder with(String name) {
        get().setName(name);
        return (ValidationAFooBuilder) this;
    }

    public ValidationAFoo get() {
        return (features.domain.ValidationAFoo) super.get();
    }

}
