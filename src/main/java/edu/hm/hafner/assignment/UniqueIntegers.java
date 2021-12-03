package edu.hm.hafner.assignment;

import java.util.Arrays;

/**
 * An immutable object-oriented set of integer values.
 *
 * @author Ullrich Hafner
 */
public class UniqueIntegers {
    private final int[] values;

    /**
     * Creates a new set of integers with the specified values.
     *
     * @param values
     *         the values of this set of integers
     */
    public UniqueIntegers(final int... values) {
        this.values = new Integers(values).removeDuplicates().getValues();

        Arrays.sort(this.values);
    }

    /**
     * Returns whether this set contains no elements.
     * <pre>{@code
     * new UniqueIntegers().isEmpty()  => true
     * new UniqueIntegers(1).isEmpty() => false
     * }</pre>
     *
     * @return {@code true} if this set contains no elements
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of elements in this set.
     * <pre>{@code
     * new UniqueIntegers().size()  => 0
     * new UniqueIntegers(1).size() => 1
     * }</pre>
     *
     * @return number of elements
     */
    public int size() {
        return values.length;
    }

    /**
     * Returns the values of this set. The elements are returned in no particular order.
     * <pre>{@code
     * new UniqueIntegers().getValues()     => []
     * new UniqueIntegers(1).getValues()    => [1]
     * new UniqueIntegers(1, 2).getValues() => [1, 2]
     * new UniqueIntegers(1, 2, 2, 1).getValues() => [1, 2]
     * }</pre>
     *
     * @return the values of this set
     */
    public int[] getValues() {
        return Arrays.copyOf(values, size());
    }

    /**
     * Appends the elements of the other set to this set.
     * <pre>{@code
     * new UniqueIntegers().append(new UniqueIntegers())     => []
     * new UniqueIntegers(1).append(new UniqueIntegers(2))   => [1, 2]
     * new UniqueIntegers(1, 2).append(new UniqueIntegers(2))   => [1, 2]
     * }</pre>
     *
     * @param other
     *         the other elements to add
     *
     * @return a new set of the form {@code [this.values, other.values]}
     */
    public UniqueIntegers append(final UniqueIntegers other) {
        int[] changed = Arrays.copyOf(values, size() + other.size());
        System.arraycopy(other.values, 0, changed, size(), other.size());

        return new UniqueIntegers(changed);
    }

    /**
     * Appends the elements to this set.
     * <pre>{@code
     * new UniqueIntegers().append()       => []
     * new UniqueIntegers(1).append(2)     => [1, 2]
     * new UniqueIntegers(1, 2).append(2)  => [1, 2]
     * }</pre>
     *
     * @param other
     *         the additional elements to add
     *
     * @return a new set of the form {@code [this.values, other.values]}
     */
    public UniqueIntegers append(final int... other) {
        int[] changed = Arrays.copyOf(values, size() + other.length);
        System.arraycopy(other, 0, changed, size(), other.length);

        return new UniqueIntegers(changed);
    }

    /**
     * Removes the elements from this set.
     * <pre>{@code
     * new UniqueIntegers().remove()       => []
     * new UniqueIntegers(1).remove(2)     => [1]
     * new UniqueIntegers(1, 2).remove(2)  => [1]
     * }</pre>
     *
     * @param other
     *         the additional elements to add
     *
     * @return a new set of the form {@code [this.values, other.values]}
     */
    public UniqueIntegers remove(final int... other) {
        UniqueIntegers changed = this;
        for (int value : other) {
            changed = remove(changed, value);
        }
        return changed;
    }

    private UniqueIntegers remove(final UniqueIntegers from, final int value) {
        if (from.contains(value)) {
            int[] changed = new int[from.values.length - 1];
            int pos = 0;
            for (int existing : from.values) {
                if (existing != value) {
                    changed[pos++] = existing;
                }
            }
            return new UniqueIntegers(changed);
        }
        return from;
    }

    /**
     * Returns whether this set contains the same elements (in the same order) as the other set.
     * <pre>{@code
     * new UniqueIntegers().isEqualTo(new UniqueIntegers())            => true
     * new UniqueIntegers(1, 2).isEqualTo(new UniqueIntegers(2, 1))    => true
     * new UniqueIntegers(1, 2, 3).isEqualTo(new UniqueIntegers(2, 1)) => false
     * }</pre>
     *
     * @param other
     *         the other set
     *
     * @return {@code true} if this set is equal to the other set, {@code false} otherwise
     */
    public boolean isEqualTo(final UniqueIntegers other) {
        return Arrays.equals(values, other.values);
    }

    /**
     * Returns whether this set contains the specified value.
     * <pre>{@code
     * new UniqueIntegers().contains(x)        => false
     * new UniqueIntegers(1, 2).contains(1)    => true
     * }</pre>
     *
     * @param value
     *         the value to search for
     *
     * @return {@code true} if this set contains the specified value, {@code false} otherwise
     */
    public  boolean contains(final int value) {
        for (int j : values) {
            if (j == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether this set contains all elements of the other set. If the other set is empty, then {@code
     * true} is always returned.
     * <pre>{@code
     * new UniqueIntegers().contains(new UniqueIntegers())             => true
     * new UniqueIntegers(1).contains(new UniqueIntegers())            => true
     * new UniqueIntegers(1, 2).contains(new UniqueIntegers(1, 2))     => true
     * new UniqueIntegers(1, 2).contains(new UniqueIntegers(1))        => true
     * new UniqueIntegers(1, 2).contains(new UniqueIntegers(1, 2, 3))  => false
     * new UniqueIntegers(1, 2).contains(new UniqueIntegers(1, 3))     => false
     * new UniqueIntegers(1, 2).contains(new UniqueIntegers(3))        => false
     * }</pre>
     *
     * @param other
     *         the other set
     *
     * @return {@code true} if this set contains all elements of the other set, {@code false} otherwise
     */
    public boolean contains(final UniqueIntegers other) {
        for (int j : other.getValues()) {
            if (!contains(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns whether this set contains at least one element of the other set.
     * <pre>{@code
     * new UniqueIntegers().intersects(new UniqueIntegers())          => false
     * new UniqueIntegers(1).intersects(new UniqueIntegers())         => false
     * new UniqueIntegers(1, 2).intersects(new UniqueIntegers(1, 2))  => true
     * new UniqueIntegers(1, 2).intersects(new UniqueIntegers(1))     => true
     * new UniqueIntegers(1, 2).intersects(new UniqueIntegers(1, 3))  => true
     * new UniqueIntegers(1, 2).intersects(new UniqueIntegers(3))     => false
     * }</pre>
     *
     * @param other
     *         the other set
     *
     * @return {@code true} if this set contains at least one element of the other set, {@code false} otherwise
     */
    public boolean intersects(final UniqueIntegers other) {
        for (int j : other.getValues()) {
            if (contains(j)) {
                return true;
            }
        }
        return false;
    }
}
