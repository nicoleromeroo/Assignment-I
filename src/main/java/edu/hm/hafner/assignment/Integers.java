package edu.hm.hafner.assignment;

import java.util.Arrays;

/**
 * An immutable object-oriented array of integer values.
 *
 * @author Ullrich Hafner
 */
class Integers {
    private final int[] values;

    /**
     * Creates a new array with the specified values.
     *
     * @param values
     *         the values of this array
     */
    Integers(final int... values) {
        this.values = Arrays.copyOf(values, values.length);
    }

    /**
     * Returns whether this array contains no elements.
     * <pre>{@code
     * new Integers().isEmpty()  => true
     * new Integers(1).isEmpty() => false
     * }</pre>
     *
     * @return {@code true} if this array contains no elements
     */
    boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of elements in this array.
     * <pre>{@code
     * new Integers().size()  => 0
     * new Integers(1).size() => 1
     * }</pre>
     *
     * @return number of elements
     */
    int size() {
        return values.length;
    }

    /**
     * Returns the values of this array.
     * <pre>{@code
     * new Integers().getValues()     => []
     * new Integers(1).getValues()    => [1]
     * new Integers(1, 2).getValues() => [1, 2]
     * }</pre>
     *
     * @return the values of this array
     */
    int[] getValues() {
        return Arrays.copyOf(values, size());
    }

    /**
     * Appends the elements of the other array after the last element of this array.
     * <pre>{@code
     * new Integers().appendAfter(new Integers())     => []
     * new Integers(1).appendAfter(new Integers(2))   => [1, 2]
     * }</pre>
     *
     * @param other
     *         the other elements to add
     *
     * @return a new array of the form {@code [this.values, other.values]}
     */
    Integers appendAfter(final Integers other) {
        int[] changed = Arrays.copyOf(values, size() + other.size());
        System.arraycopy(other.values, 0, changed, size(), other.size());

        return new Integers(changed);
    }

    /**
     * Inserts the elements of the other array before the first element of this array.
     * <pre>{@code
     * new Integers().insertBefore(new Integers())     => []
     * new Integers(1).insertBefore(new Integers(2))   => [2, 1]
     * }</pre>
     *
     * @param other
     *         the other elements to add
     *
     * @return a new array of the form {@code [other.values, this.values]}
     */
    Integers insertBefore(final Integers other) {
        return other.appendAfter(this);
    }

    /**
     * Returns whether this array contains the same elements (in the same order) as the other array.
     * <pre>{@code
     * new Integers().isEqualTo(new Integers())         => true
     * new Integers(1, 2).isEqualTo(new Integers(2, 1)) => false
     * }</pre>
     *
     * @param other
     *         the other array
     *
     * @return {@code true} if this array is equal to the other array, {@code false} otherwise
     */
    boolean isEqualTo(final Integers other) {
        return Arrays.equals(values, other.values);
    }

    /**
     * Returns whether this array contains duplicate elements.
     * <pre>{@code
     * new Integers().hasDuplicates()     => false
     * new Integers(1, 2).hasDuplicates() => false
     * new Integers(1, 1).hasDuplicates() => true
     * }</pre>
     *
     * @return {@code true} if this array contains duplicate elements, {@code false} otherwise
     */
    boolean hasDuplicates() {
        return findDuplicates().length > 0;
    }

    /**
     * Removes all duplicate elements from this array.
     * <pre>{@code
     * new Integers().removeDuplicates()        => new Integers()
     * new Integers(1, 2).removeDuplicates()    => new Integers(1, 2)
     * new Integers(2, 1, 2).removeDuplicates() => new Integers(2, 1)
     * }</pre>
     *
     * @return the new array without duplicate values - the order of the elements remains unchanged, and the first
     *         occurrence of a duplicate value will be retained at the original position
     */
    Integers removeDuplicates() {
        int[] duplicates = findDuplicates();

        int[] clean = new int[size()];
        boolean[] first = new boolean[clean.length];
        int cIndex = 0;
        for (int i = 0; i < size(); i++) {
            if (!isDuplicate(values[i], duplicates, first)) {
                clean[cIndex++] = values[i];
            }

        }
        return new Integers(Arrays.copyOf(clean, cIndex));
    }

    @SuppressWarnings("PMD.UseVarargs")
    private boolean isDuplicate(final int value, final int[] duplicates, final boolean[] first) {
        for (int i = 0; i < duplicates.length; i++) {
            if (duplicates[i] == value) {
                if (first[i]) {
                    return true;
                }
                else {
                    first[i] = true;

                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Finds all duplicate elements in this array.
     *
     * @return the duplicate element values
     */
    int[] findDuplicates() {
        int[] sorted = getValues();
        Arrays.sort(sorted);
        int[] duplicates = getValues();
        int dIndex = 0;
        for (int i = 0; i < sorted.length - 1; i++) {
            if (sorted[i] == sorted[i + 1]) {
                duplicates[dIndex] = sorted[i];
                if (dIndex == 0) {
                    dIndex++;
                }
                else {
                    if (duplicates[dIndex - 1] != sorted[i]) {
                        dIndex++;
                    }
                }

            }
        }
        return Arrays.copyOf(duplicates, dIndex);
    }

    /**
     * Returns whether this array contains the specified value.
     * <pre>{@code
     * new Integers().contains(x)        => false
     * new Integers(1, 2).contains(1)    => true
     * }</pre>
     *
     * @param value
     *         the value to search for
     *
     * @return {@code true} if this array contains the specified value, {@code false} otherwise
     */
    boolean contains(final int value) {
        for (int j : values) {
            if (j == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether this array contains all elements of the other array. If the other array is empty, then {@code
     * true} is always returned.
     * <pre>{@code
     * new Integers().contains(new Integers())          => true
     * new Integers(1).contains(new Integers())         => true
     * new Integers(1, 2).contains(new Integers(1, 2))  => true
     * new Integers(1, 2).contains(new Integers(1))     => true
     * new Integers(1, 2).contains(new Integers(1, 3))  => false
     * new Integers(1, 2).contains(new Integers(3))     => false
     * }</pre>
     *
     * @param other
     *         the other array
     *
     * @return {@code true} if this array contains all elements of the other array, {@code false} otherwise
     */
    boolean contains(final Integers other) {
        for (int j : other.getValues()) {
            if (!contains(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns whether this array contains at least one element of the other array.
     * <pre>{@code
     * new Integers().intersects(new Integers())          => false
     * new Integers(1).intersects(new Integers())         => false
     * new Integers(1, 2).intersects(new Integers(1, 2))  => true
     * new Integers(1, 2).intersects(new Integers(1))     => true
     * new Integers(1, 2).intersects(new Integers(1, 3))  => true
     * new Integers(1, 2).intersects(new Integers(3))     => false
     * }</pre>
     *
     * @param other
     *         the other array
     *
     * @return {@code true} if this array contains at least one element of the other array, {@code false} otherwise
     */
    boolean intersects(final Integers other) {
        for (int j : other.getValues()) {
            if (contains(j)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Splits this array at the given position. If the position is valid, two arrays will be returned. The first one
     * contains the elements with index {@code [0, ... , position - 1]}, the second one the elements with index {@code
     * [position, ..., size - 1]}.
     * <pre>{@code
     * new Integers().split(x)             => [new Integers(), new Integers()]
     * new Integers(1, 2, 3).split(x < 0)  => [new Integers(), new Integers(1, 2, 3)]
     * new Integers(1, 2, 3).split(0)      => [new Integers(), new Integers(1, 2, 3)]
     * new Integers(1, 2, 3).split(1)      => [new Integers(1), new Integers(2, 3)]
     * new Integers(1, 2, 3).split(2)      => [new Integers(1, 2), new Integers(3)]
     * new Integers(1, 2, 3).split(3)      => [new Integers(1, 2, 3), new Integers()]
     * new Integers(1, 2, 3).split(x > 3)  => [new Integers(1, 2, 3), new Integers()]
     * }</pre>
     *
     * @param position
     *         the position to split
     *
     * @return {@code true} the split arrays
     */
    Integers[] split(final int position) {
        Integers[] split = new Integers[2];

        int left = Math.min(Math.max(0, position), size());
        int[] first = Arrays.copyOf(values, left);
        int[] second = new int[size() - left];
        System.arraycopy(values, left, second, 0, second.length);

        split[0] = new Integers(first);
        split[1] = new Integers(second);

        return split;
    }
}
