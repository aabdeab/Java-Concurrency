# Java Atomic Classes – Quick Reference

Java's `java.util.concurrent.atomic` package provides **lock-free**, **thread-safe** classes for performing atomic operations on single variables.  
They are highly useful in **multi-threaded environments** where synchronization is needed without the overhead of explicit locks.

---

## 📜 Why Use Atomic Classes?

- **Atomicity**: Operations are completed entirely or not at all (no partial updates).
- **Lock-Free Performance**: Uses low-level CPU instructions (CAS – Compare And Swap).
- **Reduced Contention**: Avoids blocking threads like `synchronized` does.
- **Visibility**: Guarantees that updates are visible across threads.

---

## 📦 Essential Atomic Classes

### 1. **AtomicInteger**
- **Type**: `int`
- **Use Case**: Counters, indexes, ID generators in multi-threaded apps.
- **Key Methods**:
    - `incrementAndGet()`
    - `decrementAndGet()`
    - `addAndGet(int delta)`
    - `compareAndSet(expected, update)`

---

### 2. **AtomicLong**
- **Type**: `long`
- **Use Case**: High-precision counters or timestamps.
- **Key Methods**: Same as `AtomicInteger` but for `long`.

---

### 3. **AtomicBoolean**
- **Type**: `boolean`
- **Use Case**: Flags in concurrent scenarios (e.g., start/stop signals).
- **Key Methods**:
    - `getAndSet(boolean newValue)`
    - `compareAndSet(expected, update)`

---

### 4. **AtomicReference<T>**
- **Type**: Any object reference
- **Use Case**: Atomic updates to complex objects without locks.
- **Key Methods**:
    - `get()`
    - `set(T newValue)`
    - `compareAndSet(expectedRef, newRef)`

---

### 5. **AtomicStampedReference<T>**
- **Type**: Object reference + stamp (int)
- **Use Case**: Solving the **ABA problem** in CAS operations.
- **Key Methods**:
    - `getStamp()`
    - `compareAndSet(expectedRef, newRef, expectedStamp, newStamp)`

---

### 6. **AtomicMarkableReference<T>**
- **Type**: Object reference + boolean mark
- **Use Case**: Track both a reference and a state flag atomically.
- **Example**: Lock-free linked lists marking deleted nodes.

---

### 7. **AtomicIntegerArray**
- **Type**: `int[]`
- **Use Case**: Thread-safe operations on integer arrays.
- **Key Methods**:
    - `get(int index)`
    - `set(int index, int value)`
    - `compareAndSet(int index, expected, update)`

---

### 8. **AtomicLongArray**
- **Type**: `long[]`
- **Use Case**: Long-based counters across multiple threads.
- **Key Methods**: Same as `AtomicIntegerArray`.

---

### 9. **AtomicReferenceArray<T>**
- **Type**: Object reference array
- **Use Case**: Thread-safe object array updates without locks.

---

## ⚠️ When to Prefer Atomics Over `synchronized`?

- ✅ For **simple variable updates** in high-concurrency situations.
- ✅ When lock contention is high and performance is critical.
- ❌ Not ideal for **compound operations** involving multiple variables (use locks instead).

---

## 📝 Example – Atomic Counter

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet();
    }

    public int get() {
        return counter.get();
    }
}
