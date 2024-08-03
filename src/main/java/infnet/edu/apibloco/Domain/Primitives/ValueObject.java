package infnet.edu.apibloco.Domain.Primitives;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ValueObject {
    
    public abstract Stream<Object> getAtomicValues();

    public boolean equals(ValueObject other) {
        return other != null && valuesAreEqual(other);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ValueObject other && valuesAreEqual(other);
    }

    private boolean valuesAreEqual(ValueObject other) {
        return getAtomicValues().collect(Collectors.toList())
            .equals(other.getAtomicValues().collect(Collectors.toList()));
    }
}

