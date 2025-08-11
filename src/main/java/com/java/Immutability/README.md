# 📌 Immutability in Concurrent Systems

## 1. Introduction
In concurrent and distributed systems, **immutability** is a powerful design principle that helps prevent bugs caused by shared state.  
An *immutable object* is one whose state **cannot change** after it is created.

By making state unchangeable, we eliminate entire classes of concurrency issues such as race conditions, inconsistent data, and complicated locking mechanisms.

---

## 2. The Problem: Shared Mutable State
When multiple threads access and modify the same data, problems arise:

```java
// Example: Shared mutable state
class SharedCounter {
    int value = 0;
}

SharedCounter counter = new SharedCounter();

// Thread A
counter.value++; // Read → Increment → Write

// Thread B
counter.value--; // Read → Decrement → Write
