package com.java.Immutability;

import java.util.Date;

/**
 * An immutable wrapper class that encapsulates a field value.
 * <p>
 * This class is immutable because:
 * <ul>
 *   <li>It is declared {@code final}, so it cannot be subclassed.</li>
 *   <li>The field is declared {@code private final}, so it can be assigned only once.</li>
 *   <li>Defensive copies are made for mutable types during construction and retrieval.</li>
 * </ul>
 *
 * <p>Example usage:
 * <pre>
 *     ImmutableClass immutable = ImmutableClass.getInstance(new Date());
 *     Date dateCopy = immutable.getField();
 * </pre>
 *
 * <p>If the field is a mutable object (e.g., {@link java.util.Date}),
 * this class will create a defensive copy to preserve immutability.
 */
public final class ImmutableClass {

    /**
     * The encapsulated field.
     * This is never exposed directly to maintain immutability.
     */
    private final Date field;

    /**
     * Private constructor to enforce factory method usage.
     * <p>
     * Performs a defensive copy if the provided object is mutable.
     *
     * @param field the value to store; must not be {@code null}.
     */
    private ImmutableClass(Date field) {
        if (field == null) {
            throw new IllegalArgumentException("field cannot be null");
        }
        this.field = new Date(field.getTime()); // Defensive copy
    }

    /**
     * Static factory method to create a new immutable instance.
     *
     * @param field the value to store; must not be {@code null}.
     * @return a new {@code ImmutableClass} instance containing the provided value.
     */
    public static ImmutableClass getInstance(Date field) {
        return new ImmutableClass(field);
    }

    /**
     * Returns the stored field value as a defensive copy to prevent modification
     * of the internal state.
     *
     * @return a copy of the stored {@link Date} object.
     */
    public Date getField() {
        return new Date(field.getTime()); // Defensive copy
    }
}
